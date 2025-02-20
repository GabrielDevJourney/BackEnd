package apis_exercise.SpringBootApiExercise.aspect;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountAspect {
	private static final Logger logger = LoggerFactory.getLogger(AccountAspect.class);

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.createAccount(..)) && args" +
			"(accountDto)")
	public void logBeforeCreateAccount(AccountDto accountDto) {
		logger.info("Creating account for {} with Email: {}.",
				accountDto.getFirstName(), accountDto.getEmail());
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.createAccount(..))")
	public void logAfterCreateAccount() {
		logger.info("Account created with success!");
	}
	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.activateAccount(..)) && args" +
			"(id)")
	public void logBeforeActivateAccount(Long id) {
		logger.info("Activating account with ID: {}",id);
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.activateAccount(..))")
	public void logAfterActivateAccount() {
		logger.info("Account activated with success!");
	}

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.deactivateAccount(..)) && args" +
			"(id)")
	public void logBeforeDeactivateAccount(Long id) {
		logger.info("Deactivating account with ID: {}.",id);
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.deactivateAccount(..))")
	public void logAfterDeactivateAccount() {
		logger.info("Account deactivated with success!");
	}

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.deleteAccount(..)) && args" +
			"(id)")
	public void logBeforeDeleteAccount(Long id) {
		logger.info("Deleting account with ID: {}.",id);
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.deleteAccount(..))")
	public void logAfterDeleteAccount() {
		logger.info("Account deleted with success!");
	}

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.updateFirstNameAndLastName(..)) && args" +
			"(id, accountDto)")
	public void logBeforeUpdateFirstNameAndLastName(Long id, AccountDto accountDto) {
		logger.info("Updating names for account with ID: {}, New First Name: {}, New Last Name {}.",id,
				accountDto.getFirstName(), accountDto.getLastName());
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.updateFirstNameAndLastName(..))")
	public void logAfterUpdateFirstNameAndLastName() {
		logger.info("Account name updated with success!");
	}

	@Before("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.updateFullAccountDetails(..)) && args" +
			"(id, accountDto)")
	public void logBeforeUpdateFullAccountDetails(Long id, AccountDto accountDto) {
		logger.info("Updating details for account with ID: {}, New First Name: {}, New Last Name {} and Email: {}.",id,
				accountDto.getFirstName(), accountDto.getLastName(), accountDto.getEmail());
	}

	@After("execution(* apis_exercise.SpringBootApiExercise.service.AccountService.updateFullAccountDetails(..))")
	public void logAfterUpdateFullAccountDetails() {
		logger.info("Account details updated with success!");
	}
}
