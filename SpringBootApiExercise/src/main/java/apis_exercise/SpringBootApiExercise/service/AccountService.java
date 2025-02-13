package apis_exercise.SpringBootApiExercise.service;

import apis_exercise.SpringBootApiExercise.dto.AccountDto;
import apis_exercise.SpringBootApiExercise.dto.FirstLastNameDto;
import apis_exercise.SpringBootApiExercise.entity.AccountEntity;
import apis_exercise.SpringBootApiExercise.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import apis_exercise.SpringBootApiExercise.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;

	public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
	}

	public Optional<AccountDto> findById(Integer id) {
		return accountRepository.findById(id)
				.map(accountMapper::toDto);
	}

	public List<AccountDto> findAll() {
		return accountMapper.toDtoList(accountRepository.findAll());
	}

	public void save(AccountDto accountDto) {
		accountRepository.save(accountMapper.toEntity(accountDto));
	}


	public boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	public void createAccount(AccountDto accountDto) {
		if (existsByEmail(accountDto.getEmail())) {
			throw new RuntimeException("Email already exists!");
		}
		save(accountDto);
	}

	public void activateAccount(int id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		if (account.isActive()) {
			throw new RuntimeException("Account already active!");
		}

		account.setActive(true);
		accountRepository.save(account);
	}

	public void deactivateAccount(int id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		if (!account.isActive()) {
			throw new RuntimeException("Account already deactivated!");
		}

		account.setActive(false);
		accountRepository.save(account);
	}

	public void deleteAccount(int id) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
		accountRepository.delete(account);
	}

	public void updateFirstNameAndLastName(int id, AccountDto accountDto) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));

		if(accountDto.getFirstName() != null && !accountDto.getFirstName().equals("")){
			account.setFirstName(accountDto.getFirstName());
		}
		if(accountDto.getLastName() != null && !accountDto.getLastName().equals("")){
			account.setLastName(accountDto.getLastName());
		}

		accountRepository.save(account);
	}

	public void updateFullAccountDetails(int id, AccountDto accountDto) {
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