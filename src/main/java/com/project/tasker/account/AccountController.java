package com.project.tasker.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tasker.account.dto.AccountDto;
import com.project.tasker.account.request.AccountUpdateRequest;
import com.project.tasker.security.AuthPrincipal;
import com.project.tasker.security.Principal;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountManagementService accountManagementService;

	@GetMapping
	public AccountDto get(@AuthPrincipal final Principal principal) {
		return this.accountManagementService.get(principal.id());
	}

	@PutMapping
	public AccountDto update(@AuthPrincipal final Principal principal, @Valid @RequestBody final AccountUpdateRequest request) {
		return this.accountManagementService.update(principal.id(), request);
	}

}
