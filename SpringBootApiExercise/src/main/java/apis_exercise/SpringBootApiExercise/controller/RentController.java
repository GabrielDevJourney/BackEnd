package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.rent.RentRequestDto;
import apis_exercise.SpringBootApiExercise.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
