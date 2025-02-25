package apis_exercise.SpringBootApiExercise.exception.rentalException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class RentalEndKilometersException extends ValidationException {
	public RentalEndKilometersException(int start, int end) {
		super("End kilometers (" + end + ") must be greater than start kilometers (" + start + ")","End kilometers " +
				"must be greater then start kilometers");
	}
}
