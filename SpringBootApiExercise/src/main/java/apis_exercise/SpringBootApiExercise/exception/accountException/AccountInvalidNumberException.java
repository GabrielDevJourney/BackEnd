package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class AccountInvalidNumberException extends ValidationException {
	public AccountInvalidNumberException(String phoneNumber) {
		super(phoneNumber + " is an invalid format. Ensure 91,92,93,96!","Phone number with wrong format ensure " +
				"91/92/93/96xxxxxxx");
	}
}
