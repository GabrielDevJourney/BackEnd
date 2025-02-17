package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.dto.rent.RentResponseDto;
import apis_exercise.SpringBootApiExercise.enums.RentalStatus;
import apis_exercise.SpringBootApiExercise.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentController {
	private final RentService rentService;

	public RentController(RentService rentService) {
		this.rentService = rentService;
	}

	@PostMapping
	public ResponseEntity<Void> createRenting(@RequestBody RentRequestDto rentRequestDto) {
		rentService.createRenting(rentRequestDto);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/return/{id}/{endKilometers}")
	public ResponseEntity<Void> endRenting(@PathVariable Long id, @PathVariable int endKilometers){
		rentService.endRenting(id,endKilometers);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity<RentResponseDto> getRentingInfo(@PathVariable Long id){
		RentResponseDto rentResponseDto = rentService.getRentingInfo(id);
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/vehicle/{id}")
	public ResponseEntity<RentResponseDto> getRentingInfoByVehicleId(@PathVariable Long id){
		RentResponseDto rentResponseDto = rentService.getRentingInfoByVehicleId(id);
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/account/{id}")
	public ResponseEntity<RentResponseDto> getRentingInfoByAccountId(@PathVariable Long id){
		RentResponseDto rentResponseDto = rentService.getRentingInfoByAccountId(id);
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/vehicle/{id}/{status}")
	public ResponseEntity<RentResponseDto> getRentingInfoByVehicleIdAndStatus(@PathVariable Long id,
	                                                                          @PathVariable RentalStatus status){
		RentResponseDto rentResponseDto = rentService.getRentingInfoByVehicleIdAndStatus(id,status);
		if(rentResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/account/{id}/{status}")
	public ResponseEntity<RentResponseDto> getRentingInfoByAccountIdAndStatus(@PathVariable Long id,
	                                                                          @PathVariable RentalStatus status){
		RentResponseDto rentResponseDto = rentService.getRentingInfoByAccountIdAndStatus(id,status);
		if(rentResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/account/all/{id}")
	public ResponseEntity<List<RentResponseDto>> getRentalsOfAccount(@PathVariable Long id){
		List<RentResponseDto> rentResponseDto = rentService.getAllRentalsForAccount(id);
		if(rentResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/vehicle/all/{id}")
	public ResponseEntity<List<RentResponseDto>> getRentalsOfVehicle(@PathVariable Long id){
		List<RentResponseDto> rentResponseDto = rentService.getAllRentalsForVehicle(id);
		if(rentResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentResponseDto);
	}
	@GetMapping("/all/{status}")
	public ResponseEntity<List<RentResponseDto>> getRentalsOfStatus(@PathVariable RentalStatus status){
		List<RentResponseDto> rentResponseDto = rentService.getAllRentalsOfStatus(status);
		if(rentResponseDto == null){
			return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rentResponseDto);
	}



}
