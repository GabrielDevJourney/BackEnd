package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class AccountAlreadyDeactivatedException extends ValidationException {
	public AccountAlreadyDeactivatedException(Long id) {
		super(String.format("Account with ID %d is already deactivated", id), "Account already deactivated!");
	}
}
