package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.VehicleDto;
import apis_exercise.SpringBootApiExercise.service.VehicleService;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto) {
		try {
			vehicleService.createVehicle(vehicleDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@PutMapping("/{id}/activate")
	public ResponseEntity<?> activateVehicle(@PathVariable int id) {
		try {
			vehicleService.activateVehicle(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateVehicle(@PathVariable int id) {
		try {
			vehicleService.deactivateVehicle(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}