package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.VehicleDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import apis_exercise.SpringBootApiExercise.mapper.VehicleMapper;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
	private final VehicleRepository vehicleRepository;
	private final AccountRepository accountRepository;
	private final VehicleMapper vehicleMapper;

	public VehicleService(VehicleRepository vehicleRepository,
	                      AccountRepository accountRepository,
	                      VehicleMapper vehicleMapper) {
		this.vehicleRepository = vehicleRepository;
		this.accountRepository = accountRepository;
		this.vehicleMapper = vehicleMapper;
	}

	public Optional<VehicleDto> findById(Integer id) {
		return vehicleRepository.findById(id)
				.map(vehicleMapper::toDto);
	}

	public List<VehicleDto> findAll() {
		return vehicleMapper.toDtoList(vehicleRepository.findAll());
	}

	public void save(VehicleDto vehicleDto) {
		vehicleRepository.save(vehicleMapper.toEntity(vehicleDto));
	}

	public void createVehicle(VehicleDto vehicleDto) {
		if (vehicleRepository.existsByPlate(vehicleDto.getPlate())) {
			throw new RuntimeException("Plate already exists!");
		}
		save(vehicleDto);
	}

	public void associateVehicleToAccount(int vehicleId, int accountId) {
		VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new RuntimeException("Vehicle not found!"));

		AccountEntity account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		vehicle.setAccountEntity(account);
		vehicleRepository.save(vehicle);
	}

	public void activateVehicle(int id) {
		VehicleEntity vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found!"));

		if (vehicle.isActive()) {
			throw new RuntimeException("Vehicle already active!");
		}

		vehicle.setActive(true);
		vehicleRepository.save(vehicle);
	}

	public void deactivateVehicle(int id) {
		VehicleEntity vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found!"));

		if (!vehicle.isActive()) {
			throw new RuntimeException("Vehicle already deactivated!");
		}

		vehicle.setActive(false);
		vehicleRepository.save(vehicle);
	}

	public List<VehicleDto> getDeactivatedAccountsWithActiveVehicles() {
		return vehicleMapper.toDtoList(
				vehicleRepository.findByActiveIsTrueAndAccountEntityActiveIsFalse());
	}

	public List<String> getLicensePlateOfVehiclesThatAreActiveAndBelongsToDeactivatedAccount() {
		List<VehicleDto> vehicles =
				vehicleMapper.toDtoList(vehicleRepository.findByActiveIsTrueAndAccountEntityActiveIsFalse());
		return vehicles.stream()
				.map(VehicleDto::getPlate)
				.toList();
	}
}