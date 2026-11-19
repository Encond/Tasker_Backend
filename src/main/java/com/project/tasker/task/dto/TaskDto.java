package com.project.tasker.task.dto;

import java.time.Instant;
import java.util.UUID;

import com.project.tasker.task.TaskPriority;

public record TaskDto(UUID id, String description, TaskPriority priority, Instant dueDate, boolean completed, Instant createdAt) {

}
