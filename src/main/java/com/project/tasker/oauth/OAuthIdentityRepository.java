package com.project.tasker.oauth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthIdentityRepository extends JpaRepository<OAuthIdentity, UUID> {

	Optional<OAuthIdentity> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

	Optional<OAuthIdentity> findByProviderAndAccountId(OAuthProvider provider, UUID accountId);

	boolean existsByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

	void deleteByProviderAndAccountId(OAuthProvider provider, UUID accountId);

}
