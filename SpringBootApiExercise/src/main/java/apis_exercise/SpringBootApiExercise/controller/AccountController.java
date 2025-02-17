package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.account.FirstLastNameDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
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
		return ResponseEntity.status(201).build(); // 201 Created
	}

	@PutMapping("/{id}/activate")
	public ResponseEntity<Void> activateAccount(@PathVariable Long id) {
		accountService.activateAccount(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Void> deactivateAccount(@PathVariable Long id) {
		accountService.deactivateAccount(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/names")
	public ResponseEntity<Void> updateFirstNameAndLastName(@PathVariable Long id, @RequestBody AccountDto accountDto) {
		accountService.updateFirstNameAndLastName(id, accountDto);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> updateFullAccountDetails(@PathVariable Long id, @RequestBody AccountDto accountDto) {
		accountService.updateFullAccountDetails(id, accountDto);
		return ResponseEntity.ok().build();
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
}