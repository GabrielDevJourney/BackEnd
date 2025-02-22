package apis_exercise.SpringBootApiExercise.exception.vehicleException;

import apis_exercise.SpringBootApiExercise.exception.ResourceNotFoundException;

public class VehicleNotFoundException extends ResourceNotFoundException {
	public VehicleNotFoundException(Long id) {
		super("Vehicle not found with id: " + id);
	}
}