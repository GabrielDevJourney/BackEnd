package apis_exercise.SpringBootApiExercise.exception.accountException;

import apis_exercise.SpringBootApiExercise.exception.ValidationException;

import javax.swing.*;

public class AccountInvalidAgeException extends ValidationException {
	public AccountInvalidAgeException(Long id ) {
		super("Invalid age for ID: " + id, "Please enter a valid age from 18 - 99 years!");
	}
}
