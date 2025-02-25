package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.rent.RentalRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentalResponseDto;
import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import apis_exercise.SpringBootApiExercise.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	private final RentalService rentalService;

	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@PostMapping
	public ResponseEntity<Void> createRenting(@Valid @RequestBody RentalRequestDto rentalRequestDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		rentalService.createRenting(rentalRequestDto);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/return/{id}/{endKilometers}")
	public ResponseEntity<Void> endRenting(@PathVariable Long id, @PathVariable int endKilometers){
		rentalService.endRenting(id,endKilometers);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity<RentalResponseDto> getRentingInfo(@PathVariable Long id){
		RentalResponseDto rentalResponseDto = rentalService.getRentingInfo(id);
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/vehicle/{id}")
	public ResponseEntity<RentalResponseDto> getRentingInfoByVehicleId(@PathVariable Long id){
		RentalResponseDto rentalResponseDto = rentalService.getRentingInfoByVehicleId(id);
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/account/{id}")
	public ResponseEntity<RentalResponseDto> getRentingInfoByAccountId(@PathVariable Long id){
		RentalResponseDto rentalResponseDto = rentalService.getRentingInfoByAccountId(id);
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/vehicle/{id}/{status}")
	public ResponseEntity<RentalResponseDto> getRentingInfoByVehicleIdAndStatus(@PathVariable Long id,
	                                                                            @PathVariable RentalStatus status){
		RentalResponseDto rentalResponseDto = rentalService.getRentingInfoByVehicleIdAndStatus(id,status);
		if(rentalResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/account/{id}/{status}")
	public ResponseEntity<RentalResponseDto> getRentingInfoByAccountIdAndStatus(@PathVariable Long id,
	                                                                            @PathVariable RentalStatus status){
		RentalResponseDto rentalResponseDto = rentalService.getRentingInfoByAccountIdAndStatus(id,status);
		if(rentalResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/account/all/{id}")
	public ResponseEntity<List<RentalResponseDto>> getRentalsOfAccount(@PathVariable Long id){
		List<RentalResponseDto> rentalResponseDto = rentalService.getAllRentalsForAccount(id);
		if(rentalResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/vehicle/all/{id}")
	public ResponseEntity<List<RentalResponseDto>> getRentalsOfVehicle(@PathVariable Long id){
		List<RentalResponseDto> rentalResponseDto = rentalService.getAllRentalsForVehicle(id);
		if(rentalResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentalResponseDto);
	}
	@GetMapping("/all/{status}")
	public ResponseEntity<List<RentalResponseDto>> getRentalsOfStatus(@PathVariable RentalStatus status){
		List<RentalResponseDto> rentalResponseDto = rentalService.getAllRentalsOfStatus(status);
		if(rentalResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentalResponseDto);
	}



}
