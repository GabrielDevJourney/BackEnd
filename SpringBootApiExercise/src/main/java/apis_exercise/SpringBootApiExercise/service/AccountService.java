package service;

import dto.AccountDto;
import entity.AccountEntity;
import mapper.AccountMapper;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

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

	public void updateFirstNameAndLastName(int id, String firstName, String lastName) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
		account.setFirstName(firstName);
		account.setLastName(lastName);
		accountRepository.save(account);
	}

	public void updateFullAccountDetails(int id, String firstName, String lastName, String email) {
		AccountEntity account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setEmail(email);
		accountRepository.save(account);
	}

	public List<AccountDto> getDeactivatedAccounts() {
		return accountMapper.toDtoList(accountRepository.findByActiveIsFalse());
	}

	public List<AccountDto> getFirstNameAndLastNameAccountsThatAreDeactivated() {
		return accountMapper.toDtoList(
				accountRepository.findByActiveIsFalseOrderByFirstNameAscLastNameAsc()
		);
	}
}