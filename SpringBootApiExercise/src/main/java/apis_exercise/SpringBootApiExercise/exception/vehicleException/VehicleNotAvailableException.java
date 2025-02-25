package apis_exercise.SpringBootApiExercise.exception.vehicleException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class VehicleNotAvailableException extends ValidationException {
  public VehicleNotAvailableException(Long id) {
    super("Vehicle not available with id: " + id,"Vehicle is not available!");
  }
}