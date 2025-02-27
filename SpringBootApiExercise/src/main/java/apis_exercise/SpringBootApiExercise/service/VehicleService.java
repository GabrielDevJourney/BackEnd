package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import apis_exercise.SpringBootApiExercise.entity.VehicleEntity;
import apis_exercise.SpringBootApiExercise.enums.VehicleStatus;
import apis_exercise.SpringBootApiExercise.exception.vehicleException.*;
import apis_exercise.SpringBootApiExercise.mapper.VehicleMapper;
import apis_exercise.SpringBootApiExercise.repository.VehicleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

	public void updateVehicleStatus(Long vehicleId, VehicleStatus newStatus) {
		VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new VehicleNotFoundException(vehicleId));

		switch (newStatus) {
			case MAINTENANCE:
				setVehicleStatusToMaintenance(vehicle);
				break;
			case DISABLE:
				setVehicleStatusToDisable(vehicle);
				break;
			case AVAILABLE:
				setVehicleStatusToAvailable(vehicle);
				break;
			case RENTED:
				setVehicleStatusToRented(vehicle);
				break;
			default:
				throw new VehicleInvalidStatusUpdateException(vehicleId,vehicle.getStatus(), newStatus);
		}

		vehicleRepository.save(vehicle);
	}


	//* HELPERS METHODS

	public void setVehicleStatusToRented(VehicleEntity vehicle){
		if(vehicle.getStatus() != VehicleStatus.AVAILABLE){
			throw new VehicleStatusAvailableToRentedException(vehicle.getId());
		}
		vehicle.setStatus(VehicleStatus.RENTED);
	}

	public void setVehicleStatusToMaintenance(VehicleEntity vehicle){
		if(vehicle.getStatus().equals(VehicleStatus.DISABLE) ){
			throw new VehicleStatusDisableToMaintenanceException(vehicle.getId());
		}
		vehicle.setStatus(VehicleStatus.MAINTENANCE);
		vehicle.setMaintenanceEndDate(LocalDate.now().plusDays(2));
	}

	public void setVehicleStatusToDisable(VehicleEntity vehicle){
		if(vehicle.getStatus().equals(VehicleStatus.RENTED)){
			throw new VehicleStatusRentedToDisableException(vehicle.getId());
		}
		vehicle.setStatus(VehicleStatus.DISABLE);
	}
	public void setVehicleStatusToAvailable(VehicleEntity vehicle){
		if(vehicle.getStatus().equals(VehicleStatus.RENTED)){
			throw new VehicleInvalidStatusUpdateException(vehicle.getId(),vehicle.getStatus(), VehicleStatus.AVAILABLE);
		}
		vehicle.setStatus(VehicleStatus.AVAILABLE);
	}

	//check every day for vehicles that can be updated to available
	@Scheduled(fixedRate = 86400000)
	public void updateMaintenanceVehicles() {
		List<VehicleEntity> vehiclesInMaintenance = vehicleRepository.findAllByStatus(VehicleStatus.MAINTENANCE);
		LocalDate today = LocalDate.now();

		for (VehicleEntity vehicle : vehiclesInMaintenance) {
			if (vehicle.getMaintenanceEndDate().isBefore(today) || vehicle.getMaintenanceEndDate().isEqual(today)) {
				vehicle.setStatus(VehicleStatus.AVAILABLE);
				vehicle.setMaintenanceEndDate(null);
				vehicleRepository.save(vehicle);
			}
		}
	}

	public void checkAndScheduleMaintenanceIfNeeded(VehicleEntity vehicle, int startKilometers, int endKilometers) {
		int distanceTraveled = endKilometers - startKilometers;

		if (distanceTraveled >= vehicle.getMaintenanceKilometers()) {
			setVehicleStatusToMaintenance(vehicle);
		} else {
			completeRental(vehicle,endKilometers);
		}

		vehicleRepository.save(vehicle);
	}

	public void completeRental(VehicleEntity vehicle, int endKilometers) {

		vehicle.setCurrentKilometers(endKilometers);
		vehicle.setStatus(VehicleStatus.AVAILABLE);
	}

	private void checkYearOfManufacture(int vehicleYear){
		int maxYear = Year.now().getValue();
		int minYear = maxYear - 20;
		if(vehicleYear > maxYear || vehicleYear < minYear){
			throw new VehicleNotValidYearOfManufacture(vehicleYear,minYear, maxYear);
		}
	}
}