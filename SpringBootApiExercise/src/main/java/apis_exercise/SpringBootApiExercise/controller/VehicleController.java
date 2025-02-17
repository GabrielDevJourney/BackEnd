package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDeactivatePlateDto;
import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import apis_exercise.SpringBootApiExercise.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<Void> createVehicle(@RequestBody VehicleDto vehicleDto) {
		vehicleService.createVehicle(vehicleDto);
		return ResponseEntity.ok().build();
	}

	//todo get a car info by its plate
}