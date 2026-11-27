package com.project.tasker.auth.token.access;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.project.tasker.account.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AccessTokenService {

	private final AccessTokenProperties accessTokenProperties;
	private final SecretKey secretKey;

	public AccessTokenService(final AccessTokenProperties accessTokenProperties) {
		super();

		this.accessTokenProperties = accessTokenProperties;
		this.secretKey = this.buildSecretKey();
	}

	public String generate(final Account account) {
		final Instant now = Instant.now();
		// @formatter:off
		return Jwts.builder()
				.subject(account.getId().toString())
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(this.accessTokenProperties.expiration())))
				.signWith(secretKey)
				.compact();
		// @formatter:on
	}

	public AccessTokenClaims extractClaims(final String token) {
		// @formatter:off
		final Claims claims = Jwts.parser()
				.verifyWith(this.secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		// @formatter:on
		return new AccessTokenClaims(UUID.fromString(claims.getSubject()));
	}

	private SecretKey buildSecretKey() {
		final byte[] secretBytes = this.accessTokenProperties.secret().getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(secretBytes);
	}

}
