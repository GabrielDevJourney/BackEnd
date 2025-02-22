package apis_exercise.SpringBootApiExercise.exception.rentalException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class RentalInvalidRentingDatesException extends ValidationException {
	public RentalInvalidRentingDatesException(String message) {
		super(message,"Invalid dates for this renting");
	}
}
