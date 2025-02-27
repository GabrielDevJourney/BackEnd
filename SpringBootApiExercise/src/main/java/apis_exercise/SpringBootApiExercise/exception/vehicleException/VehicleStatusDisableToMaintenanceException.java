package apis_exercise.SpringBootApiExercise.exception.vehicleException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class VehicleStatusDisableToMaintenanceException extends ValidationException {
	public VehicleStatusDisableToMaintenanceException(Long id) {
		super("Trying to set maintenance to rented or disable for vehicle with ID: " + id, "This vehicle can't be " +
				"send to maintenance!");
	}
}
