package com.project.tasker.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.tasker.account.dto.AccountDto;
import com.project.tasker.account.request.AccountUpdateRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountManagementService {

	private final AccountService accountService;
	private final AccountMapper accountMapper;

	public AccountDto get(final UUID id) {
		return this.accountService.get(id).map(this.accountMapper::toDto).orElseThrow(/* TODO: Exception */);
	}

	public AccountDto update(final UUID id, final AccountUpdateRequest request) {
		return this.accountMapper.toDto(this.accountService.update(id, request.fullName()));
	}

}
