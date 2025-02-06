package controller;

import dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping
	public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto){
		if(vehicleDto.getPlate() == null){
			return new ResponseEntity<>("Account ID is required", HttpStatus.BAD_REQUEST);
		}
		vehicleService.save(vehicleDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
