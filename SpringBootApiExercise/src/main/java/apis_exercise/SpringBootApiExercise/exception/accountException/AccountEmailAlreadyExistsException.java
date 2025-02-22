package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class AccountEmailAlreadyExistsException extends ValidationException {
	public AccountEmailAlreadyExistsException(String email) {
		super("Email already exists: " + email,"Invalid email!");
	}
}