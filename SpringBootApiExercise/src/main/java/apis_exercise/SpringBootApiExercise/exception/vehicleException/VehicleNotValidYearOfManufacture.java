package apis_exercise.SpringBootApiExercise.exception.vehicleException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class VehicleNotValidYearOfManufacture extends ValidationException {
	public VehicleNotValidYearOfManufacture(int vehicleYear,int minYear, int maxYear) {
		super(vehicleYear + " is not a valid year", "Please insert a valid year between " + minYear + " and " + maxYear);
	}
}
