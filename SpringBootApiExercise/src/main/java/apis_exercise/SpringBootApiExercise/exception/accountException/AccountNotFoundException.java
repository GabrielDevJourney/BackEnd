package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ResourceNotFoundException;

public class AccountNotFoundException extends ResourceNotFoundException {
	public AccountNotFoundException(Long id) {
		super("Account not found with id: " + id);
	}
}
