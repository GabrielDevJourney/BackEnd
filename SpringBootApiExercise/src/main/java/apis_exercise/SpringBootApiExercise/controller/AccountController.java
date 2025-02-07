package apis_exercise.SpringBootApiExercise.controller;

import apis_exercise.SpringBootApiExercise.dto.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.FirstLastNameDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto){
		try{
			accountService.createAccount(accountDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	// PathVariable used to say the specific account to deactivate in the REST URL path
	@PutMapping("/{id}/activate")
	public ResponseEntity<?> activateAccount(@PathVariable int id){
		try{
			accountService.activateAccount(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	// PathVariable used to say the specific account to deactivate in the REST URL path
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivateAccount(@PathVariable int id){
		try{
			accountService.deactivateAccount(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable int id){
		try{
			accountService.deleteAccount(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	// PathVariable used to say the specific account being updated in the REST URL path, but also request what info
	// is there to be updated
	@PutMapping("/{id}/names")
	public ResponseEntity<?> updateFirstNameAndLastName(@PathVariable int id,@RequestBody AccountDto accountDto){
		try{
			accountService.updateFirstNameAndLastName(id,accountDto);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateFullAccountDetails(@PathVariable int id, @RequestBody AccountDto accountDto){
		try{
			accountService.updateFullAccountDetails(id,accountDto);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/deactivated")
	public ResponseEntity<?> getDeactivatedAccounts() {
		try {
			List<AccountDto> accounts = accountService.getDeactivatedAccounts();
			return new ResponseEntity<>(accounts, HttpStatus.OK);  // Added accounts to response
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/deactivated/names")
	public ResponseEntity<?> getFirstNameAndLastNameAccountsThatAreDeactivated() {
		try {
			List<FirstLastNameDto> accounts =
					accountService.getFirstNameAndLastNameAccountsThatAreDeactivated();
			return new ResponseEntity<>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

