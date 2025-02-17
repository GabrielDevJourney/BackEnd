package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import apis_exercise.SpringBootApiExercise.mapper.RentMapper;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;
import apis_exercise.SpringBootApiExercise.repository.RentRepository;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentService {
	private final RentRepository rentRepository;
	private final RentMapper rentMapper;
	private final VehicleRepository vehicleRepository;
	private final AccountRepository accountRepository;

	public RentService(RentRepository rentRepository, RentMapper rentMapper, VehicleRepository vehicleRepository,
	                   AccountRepository accountRepository) {
		this.rentRepository = rentRepository;
		this.rentMapper = rentMapper;
		this.vehicleRepository = vehicleRepository;
		this.accountRepository =accountRepository;
	}

	public void createRenting(RentRequestDto rentRequestDto){
		VehicleEntity vehicle =
				vehicleRepository.findById(rentRequestDto.getVehicleId()).orElseThrow(() -> new RuntimeException(
						"Vehicle not found"));
		AccountEntity account =
				accountRepository.findById(rentRequestDto.getAccountId()).orElseThrow(() -> new RuntimeException(
				"Account not found"));

		RentEntity rentalEntity = rentMapper.toEntityRequest(rentRequestDto);
		rentRepository.save(rentalEntity);
	}

	public void endRenting(Long id, int endKilometers){
		RentEntity rent = rentRepository.findByIdAndStatus(id, RentalStatus.ACTIVE);

		rent.setEndKilometers(endKilometers);
		rent.setDateReturn(LocalDate.now());
		rent.setStatus(RentalStatus.COMPLETED);

		VehicleEntity vehicle = rent.getVehicleEntity();
		vehicle.setCurrentKilometers(endKilometers);

		vehicleRepository.save(vehicle);
		rentRepository.save(rent);
	}
}
