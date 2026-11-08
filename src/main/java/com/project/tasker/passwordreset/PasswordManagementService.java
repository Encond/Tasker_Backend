package com.project.tasker.passwordreset;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tasker.account.AccountService;
import com.project.tasker.email.EmailService;
import com.project.tasker.passwordreset.request.ChangePasswordRequest;
import com.project.tasker.passwordreset.request.PasswordResetRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PasswordManagementService {

	private final PasswordResetTokenService passwordResetTokenService;
	private final PasswordResetTokenProperties passwordResetTokenProperties;

	private final AccountService accountService;
	private final EmailService emailService;

	private final SecureRandom secureRandom;
	private final PasswordEncoder passwordEncoder;

	public void requestPasswordReset(final UUID accountId) {
		this.accountService.get(accountId).ifPresent(account -> {
			final String token = this.generateToken();
			final String tokenHash = this.hashToken(token);

			this.passwordResetTokenService.create(tokenHash, Instant.now().plus(this.passwordResetTokenProperties.expiration()), account);

			this.emailService.sendPasswordResetToken(account.getEmail(), token);
		});
	}

	@Transactional
	public void resetPassword(final String token, final PasswordResetRequest request) {
		this.passwordResetTokenService.get(this.hashToken(token)).ifPresent((passwordResetToken) -> {
			if (passwordResetToken.isExpired() || passwordResetToken.isUsed()) {
				// TODO: Exception
			}

			passwordResetToken.getAccount().updatePassword(this.passwordEncoder.encode(request.newPassword()));
			passwordResetToken.markAsUsed();
		});
	}

	@Transactional
	public void changePassword(final UUID accountId, final ChangePasswordRequest request) {
		this.accountService.get(accountId).ifPresent((account) -> {
			if (!account.hasPassword() || this.passwordEncoder.matches(request.currentPassword(), account.getPasswordHash())) {
				// TODO: Exception
			}

			account.updatePassword(this.passwordEncoder.encode(request.newPassword()));
		});
	}

	private String generateToken() {
		final byte[] bytes = new byte[32];
		this.secureRandom.nextBytes(bytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	private String hashToken(final String token) {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = messageDigest.digest(token.getBytes());
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 is not available", e);
		}
	}

}
