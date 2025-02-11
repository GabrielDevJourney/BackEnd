package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.entity.RentEntity;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import apis_exercise.SpringBootApiExercise.mapper.RentMapper;
import apis_exercise.SpringBootApiExercise.repository.RentRepository;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class RentService {
	private final RentRepository rentRepository;
	private final RentMapper rentMapper;
	private final VehicleRepository vehicleRepository;

	public RentService(RentRepository rentRepository, RentMapper rentMapper, VehicleRepository vehicleRepository) {
		this.rentRepository = rentRepository;
		this.rentMapper = rentMapper;
		this.vehicleRepository = vehicleRepository;
	}

	public void createRenting(RentRequestDto rentRequestDto){
		VehicleEntity vehicle =
				vehicleRepository.findById(rentRequestDto.getVehicleId()).orElseThrow(() -> new RuntimeException(
						"Vehicle not found"));

		int startKilometers = vehicle.getCurrentKilometers();

		RentEntity entity = rentMapper.toEntityRequest(rentRequestDto);

		entity.setStartKilometers(startKilometers);

		rentRepository.save(entity);
	}
}
