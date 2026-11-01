package com.project.tasker.oauth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.tasker.account.Account;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OAuthIdentityService {

	private final OAuthIdentityRepository oAuthIdentityRepository;

	public OAuthIdentity create(final OAuthProvider provider, final String providerUserId, final Account account) {
		return this.oAuthIdentityRepository.save(OAuthIdentity.of(provider, providerUserId, account));
	}

	public Optional<OAuthIdentity> get(final OAuthProvider provider, final String providerUserId) {
		return this.oAuthIdentityRepository.findByProviderAndProviderUserId(provider, providerUserId);
	}

	public void delete(final OAuthProvider provider, final UUID accountId) {
		this.oAuthIdentityRepository.deleteByProviderAndAccountId(provider, accountId);
	}

	public boolean exists(final OAuthProvider provider, final String providerUserId) {
		return this.oAuthIdentityRepository.existsByProviderAndProviderUserId(provider, providerUserId);
	}

}
