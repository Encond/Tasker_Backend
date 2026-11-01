package com.project.tasker.account.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record AccountUpdateRequest(@NotBlank @Length(max = 100) String fullName) {

}
