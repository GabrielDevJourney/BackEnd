package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.vehicle.VehicleDto;
import apis_exercise.SpringBootApiExercise.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<Void> createVehicle(@Valid @RequestBody VehicleDto vehicleDto, BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			return ResponseEntity.badRequest().build();
		}

		vehicleService.createVehicle(vehicleDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/search/plate/{plate}")
	public ResponseEntity<VehicleDto> getVehicleByPlate(@PathVariable String plate) {
		VehicleDto vehicle = vehicleService.findByPlate(plate);
		return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/plate")
	public ResponseEntity<String> getPlateById(@PathVariable Long id) {
		VehicleDto vehicle = vehicleService.findById(id).orElseThrow(() -> new RuntimeException("No vehicle found!"));
		String plate = vehicle.getPlate();
		return plate != null ? ResponseEntity.ok(plate) : ResponseEntity.notFound().build();
	}
}