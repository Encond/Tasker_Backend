package com.project.tasker.goal.request;

import java.time.Instant;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record GoalCreateRequest(@NotBlank @Length(max = 100) String title,
								@NotBlank @Length(max = 100) String description,
								@NotNull Instant dueDate) {
// @formatter:on
}
