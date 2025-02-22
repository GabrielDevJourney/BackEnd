package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.account.FirstLastNameDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.exception.accountException.*;
import apis_exercise.SpringBootApiExercise.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;

	public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
	}

	//IN CLASS HELPER METHODS
	private void save(AccountDto accountDto) {
		accountRepository.save(accountMapper.toEntity(accountDto));
	}

	private boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	//REST ENDPOINTS
	public void createAccount(AccountDto accountDto) {
		String accountEmail = accountDto.getEmail();
		if (existsByEmail(accountEmail)) {
			throw new AccountEmailAlreadyExistsException(accountEmail);
		}
		save(accountDto);
	}

	public void activateAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		if (account.isActive()) {
			throw new AccountAlreadyActiveException(id);
		}

		account.setActive(true);
		accountRepository.save(account);
	}

	public void deactivateAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		if (!account.isActive()) {
			throw new AccountAlreadyDeactivatedException(id);
		}

		account.setActive(false);
		accountRepository.save(account);
	}

	public void deleteAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
		accountRepository.delete(account);
	}

	public void updateFirstNameAndLastName(Long id, AccountDto accountDto) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		if(accountDto.getFirstName() != null && !accountDto.getFirstName().isEmpty()){
			account.setFirstName(accountDto.getFirstName());
		}
		if(accountDto.getLastName() != null && !accountDto.getLastName().isEmpty()){
			account.setLastName(accountDto.getLastName());
		}

		accountRepository.save(account);
	}

	public void updateFullAccountDetails(Long id, AccountDto accountDto) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		account.setFirstName(accountDto.getFirstName());
		account.setLastName(accountDto.getLastName());
		account.setEmail(accountDto.getEmail());

		accountRepository.save(account);

	}

	public void updateAccountAge(Long id, Integer age){
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
		if(age < 18 || age > 99){
			throw new AccountInvalidAgeException(id);
		}
		account.setAge(age);
		accountRepository.save(account);
	}

	public void updateAccountEmail(Long id, String email) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		if (accountRepository.existsByEmail(email)) {
			throw new AccountEmailAlreadyExistsException(email);
		}

		account.setEmail(email);
		accountRepository.save(account);
	}
	public void updateAccountPhoneNumber(Long id, String phoneNumber) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));

		if (!phoneNumber.matches("^(91|92|93|96)\\d{7}$")) {
			throw new AccountInvalidNumberException(phoneNumber);
		}
		account.setPhoneNumber(phoneNumber);
		accountRepository.save(account);
	}


	public AccountDto getAccountById(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
		return accountMapper.toDto(account);
	}

	public List<AccountDto> getDeactivatedAccounts() {
		return accountMapper.toDtoList(accountRepository.findByActiveIsFalse());
	}

	public List<FirstLastNameDto> getFirstNameAndLastNameAccountsThatAreDeactivated() {
		List<AccountEntity> deactivatedAccounts = accountRepository.findByActiveIsFalseOrderByFirstNameAscLastNameAsc();
		return deactivatedAccounts.stream()
				.map(accountMapper::toFirstLastNameDto)
				.toList();
	}

	public List<AccountDto> getAllAccounts() {
		List<AccountEntity> accounts = accountRepository.findAll();
		return accountMapper.toDtoList(accounts);
	}
}