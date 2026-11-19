package com.project.tasker.task.request;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.project.tasker.task.TaskPriority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record TaskCreateRequest(@NotBlank @Length(max = 255) String description,
								@NotNull 					 TaskPriority priority,
								@NotNull 					 Instant dueDate,
								@NotNull 					 UUID goalId
// @formatter:on
) {

}
