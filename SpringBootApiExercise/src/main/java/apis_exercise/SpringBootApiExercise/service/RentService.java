package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentResponseDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public RentResponseDto getRentingInfo(Long id){
		RentEntity rent = rentRepository.findById(id).orElseThrow(() -> new RuntimeException("No rental " +
				"with this id found"));
		return rentMapper.toDtoResponse(rent);
	}
	public RentResponseDto getRentingInfoByVehicleId(Long id){
		RentEntity rent = rentRepository.findByVehicleEntity_Id(id);
		return rentMapper.toDtoResponse(rent);
	}
	public RentResponseDto getRentingInfoByAccountId(Long id){
		RentEntity rent = rentRepository.findByAccountEntity_Id(id);
		return rentMapper.toDtoResponse(rent);
	}
	public RentResponseDto getRentingInfoByVehicleIdAndStatus(Long id, RentalStatus status){
		RentEntity rent = rentRepository.findByVehicleEntity_IdAndStatus(id,status);
		if(rent.getStatus() != status){
			return null;
		}
		return rentMapper.toDtoResponse(rent);
	}
	public RentResponseDto getRentingInfoByAccountIdAndStatus(Long id, RentalStatus status){
		RentEntity rent = rentRepository.findByAccountEntity_IdAndStatus(id,status);
		if(rent.getStatus() != status){
			return null;
		}
		return rentMapper.toDtoResponse(rent);
	}
	public List<RentResponseDto> getAllRentalsForAccount(Long id){
		List<RentEntity> rentals = rentRepository.findAllByAccountEntity_Id(id);
		List<RentResponseDto> rentalsDtos = new ArrayList<>();
		for(RentEntity entity : rentals){
			rentalsDtos.add(rentMapper.toDtoResponse(entity));
		}
		return rentalsDtos;
	}
	public List<RentResponseDto> getAllRentalsForVehicle(Long id){
		List<RentEntity> rentals = rentRepository.findAllByVehicleEntity_Id(id);
		List<RentResponseDto> rentalsDtos = new ArrayList<>();
		for(RentEntity entity : rentals){
			rentalsDtos.add(rentMapper.toDtoResponse(entity));
		}
		return rentalsDtos;
	}
	public List<RentResponseDto> getAllRentalsOfStatus(RentalStatus status){
		List<RentEntity> rentals = rentRepository.findAllByStatus(status);
		List<RentResponseDto> rentalsDtos = new ArrayList<>();
		for(RentEntity entity : rentals){
			rentalsDtos.add(rentMapper.toDtoResponse(entity));
		}
		return rentalsDtos;
	}


}
