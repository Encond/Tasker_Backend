package com.project.tasker.account;

import org.springframework.stereotype.Component;

import com.project.tasker.account.dto.AccountDto;

@Component
public class AccountMapper {

	public AccountDto toDto(final Account account) {
		return new AccountDto(account.getId(), account.getFullName(), account.getEmail(), account.hasPassword(), account.getCreatedAt());
	}

}
