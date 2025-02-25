package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import apis_exercise.SpringBootApiExercise.exception.vehicleException.VehicleNotValidYearOfManufacture;
import apis_exercise.SpringBootApiExercise.mapper.VehicleMapper;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;

	public VehicleService(VehicleRepository vehicleRepository,
	                      VehicleMapper vehicleMapper) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleMapper = vehicleMapper;
	}

	public void save(VehicleDto vehicleDto) {
		vehicleRepository.save(vehicleMapper.toEntity(vehicleDto));
	}

	public Optional<VehicleDto> findById(Long id) {
		return vehicleRepository.findById(id)
				.map(vehicleMapper::toDto);
	}

	public List<VehicleDto> findAll() {
		return vehicleMapper.toDtoList(vehicleRepository.findAll());
	}

	public void createVehicle(VehicleDto vehicleDto) {
		if (vehicleRepository.existsByPlate(vehicleDto.getPlate())) {
			throw new RuntimeException("Plate already exists!");
		}
		checkYearOfManufacture(vehicleDto.getYearManufacture());

		save(vehicleDto);
	}

	public VehicleDto findByPlate(String plate) {
		return vehicleRepository.findByPlate(plate)
				.map(vehicleMapper::toDto)
				.orElse(null);
	}


	//* HELPERS METHODS

	private void checkYearOfManufacture(int vehicleYear){
		int maxYear = Year.now().getValue();
		int minYear = maxYear - 20;
		if(vehicleYear > maxYear || vehicleYear < minYear){
			throw new VehicleNotValidYearOfManufacture(vehicleYear,minYear, maxYear);
		}
	}
}