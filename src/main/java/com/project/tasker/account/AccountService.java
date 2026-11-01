package com.project.tasker.account;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	public Account create(final String email) {
		return this.accountRepository.save(Account.of(email.split("@")[0], email));
	}

	public Optional<Account> get(final UUID id) {
		return this.accountRepository.findById(id);
	}

	public Optional<Account> get(final String email) {
		return this.accountRepository.findByEmail(email);
	}

	@Transactional
	public Account update(final UUID id, final String fullName) {
		final Account account = this.accountRepository.findById(id).orElseThrow(/* TODO: Exception */);
		account.updateFullName(fullName);
		return account;
	}

	public boolean exists(final String email) {
		return this.accountRepository.existsByEmail(email);
	}

}
