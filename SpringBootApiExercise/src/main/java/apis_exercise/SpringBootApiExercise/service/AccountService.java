package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.account.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.account.FirstLastNameDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.mapper.AccountMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;

	public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
	}

	public void save(AccountDto accountDto) {
		accountRepository.save(accountMapper.toEntity(accountDto));
	}


	private boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	public void createAccount(AccountDto accountDto) {
		if (existsByEmail(accountDto.getEmail())) {
			throw new RuntimeException("Email already exists!");
		}
		save(accountDto);
	}

	public void activateAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		if (account.isActive()) {
			throw new RuntimeException("Account already active!");
		}

		account.setActive(true);
		accountRepository.save(account);
	}

	public void deactivateAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		if (!account.isActive()) {
			throw new RuntimeException("Account already deactivated!");
		}

		account.setActive(false);
		accountRepository.save(account);
	}

	public void deleteAccount(Long id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
		accountRepository.delete(account);
	}

	public void updateFirstNameAndLastName(Long id, AccountDto accountDto) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

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
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		account.setFirstName(accountDto.getFirstName());
		account.setLastName(accountDto.getLastName());
		account.setEmail(accountDto.getEmail());

		accountRepository.save(account);

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
}