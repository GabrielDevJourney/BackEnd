package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

public class AccountAlreadyActiveException extends ValidationException {
	public AccountAlreadyActiveException(Long id) {
		super(String.format("Account with ID %d is already active", id),"Account already active!");
	}
}
