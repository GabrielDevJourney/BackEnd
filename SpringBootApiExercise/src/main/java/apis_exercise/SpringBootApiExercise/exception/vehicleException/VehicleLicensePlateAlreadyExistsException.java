package apis_exercise.SpringBootApiExercise.exception.vehicleException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class VehicleLicensePlateAlreadyExistsException extends ValidationException {
	public VehicleLicensePlateAlreadyExistsException(String plate) {
		super("License plate already exists: " + plate,"Invalid plate!");
	}
}