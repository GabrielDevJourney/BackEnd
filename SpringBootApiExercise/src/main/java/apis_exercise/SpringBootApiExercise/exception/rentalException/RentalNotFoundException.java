package apis_exercise.SpringBootApiExercise.exception.rentalException;

import apis_exercise.SpringBootApiExercise.exception.ResourceNotFoundException;

public class RentalNotFoundException extends ResourceNotFoundException {
	public RentalNotFoundException(Long id) {
		super("Rental not found with id: " + id);
	}
}
