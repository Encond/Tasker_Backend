package com.project.tasker.passwordreset;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tasker.account.Account;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PasswordResetTokenService {

	private final PasswordResetTokenRepository passwordResetTokenRepository;

	public PasswordResetToken create(final String tokenHash, final Instant expiresAt, final Account account) {
		return this.passwordResetTokenRepository.save(PasswordResetToken.of(tokenHash, expiresAt, account));
	}

	@Transactional
	public Optional<PasswordResetToken> get(final String tokenHash) {
		return this.passwordResetTokenRepository.findByTokenHash(tokenHash);
	}

}
