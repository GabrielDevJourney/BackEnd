package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.VehicleDeactivatePlateDto;
import apis_exercise.SpringBootApiExercise.dto.VehicleDto;
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


	@PutMapping("/{id}/activate")
	public ResponseEntity<Void> activateVehicle(@PathVariable Long id) {
		vehicleService.activateVehicle(id);
		return ResponseEntity.ok().build();
	}


	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateVehicle(@PathVariable Long id) {

		vehicleService.deactivateVehicle(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/deactivated-plates")
	public ResponseEntity<List<VehicleDeactivatePlateDto>> getDeactivatedPlates(){
		List<VehicleDeactivatePlateDto> deactivatePlate = vehicleService.getDeactivatePlate();
		//since i want plates pass them in the ok response
		return ResponseEntity.ok(deactivatePlate);
	}
}