package com.project.tasker.passwordreset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.tasker.passwordreset.request.PasswordChangeRequest;
import com.project.tasker.passwordreset.request.PasswordResetRequest;
import com.project.tasker.security.AuthPrincipal;
import com.project.tasker.security.Principal;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/account/password")
public class PasswordController {

	private final PasswordManagementService passwordManagementService;

	@PostMapping("/reset/request")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void requestPasswordReset(@AuthPrincipal final Principal principal) {
		this.passwordManagementService.requestPasswordReset(principal.id());
	}

	@PostMapping("/reset")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resetPassword(@Valid @RequestBody final PasswordResetRequest request) {
		this.passwordManagementService.resetPassword(request);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@AuthPrincipal final Principal principal, @Valid @RequestBody final PasswordChangeRequest request) {
		this.passwordManagementService.changePassword(principal.id(), request);
	}

}
