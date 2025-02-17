package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import apis_exercise.SpringBootApiExercise.mapper.VehicleMapper;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;

	public VehicleService(VehicleRepository vehicleRepository,
	                      AccountRepository accountRepository,
	                      VehicleMapper vehicleMapper) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleMapper = vehicleMapper;
	}

	public Optional<VehicleDto> findById(Long id) {
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

	public VehicleDto findByPlate(String plate) {
		return vehicleRepository.findByPlate(plate)
				.map(vehicleMapper::toDto)
				.orElse(null);
	}


}