package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.account.FirstLastNameDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import apis_exercise.SpringBootApiExercise.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<Void> createAccount(@RequestBody AccountDto accountDto) {
		accountService.createAccount(accountDto);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/activate")
	@Transactional
	public ResponseEntity<Void> activateAccount(@PathVariable Long id) {
		accountService.activateAccount(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/deactivate")
	@Transactional
	public ResponseEntity<Void> deactivateAccount(@PathVariable Long id) {
		accountService.deactivateAccount(id);
		return ResponseEntity.ok().build();
	}

	@Transactional
	@PatchMapping("/{id}/names")
	public ResponseEntity<Void> updateFirstNameAndLastName(@PathVariable Long id, @RequestBody FirstLastNameDto firstLastNameDto) {
		accountService.updateFirstNameAndLastName(id, firstLastNameDto);
		return ResponseEntity.ok().build();
	}
	@Transactional
	@PatchMapping("/{id}/email")
	public ResponseEntity<Void> updateAccountEmail(@PathVariable Long id,
	                                               @RequestBody @Email String email) {
		accountService.updateAccountEmail(id, email);
		return ResponseEntity.ok().build();
	}
	@Transactional
	@PatchMapping("/{id}/age")
	public ResponseEntity<Void> updateAccountAge(@PathVariable Long id,
	                                             @RequestBody @Range(min = 18, max = 99) Integer age) {
		accountService.updateAccountAge(id, age);
		return ResponseEntity.ok().build();
	}

	@Transactional
	@PatchMapping("/{id}/phoneNumber")
	public ResponseEntity<Void> updateAccountPhoneNumber(@PathVariable Long id,
	                                             @Valid @RequestBody String phoneNumber) {
		accountService.updateAccountPhoneNumber(id,phoneNumber);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> updateFullAccountDetails(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto,
			BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			return ResponseEntity.badRequest().build();
		}

		accountService.updateFullAccountDetails(id, accountDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto account = accountService.getAccountById(id);
		return ResponseEntity.ok(account);
	}

	@GetMapping("/deactivated")
	public ResponseEntity<List<AccountDto>> getDeactivatedAccounts() {
		List<AccountDto> accounts = accountService.getDeactivatedAccounts();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/deactivated/names")
	public ResponseEntity<List<FirstLastNameDto>> getFirstNameAndLastNameAccountsThatAreDeactivated() {
		List<FirstLastNameDto> accounts = accountService.getFirstNameAndLastNameAccountsThatAreDeactivated();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
}