package com.project.tasker.passwordreset.request;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetRequest(@NotBlank String token, @NotBlank String newPassword) {

}
