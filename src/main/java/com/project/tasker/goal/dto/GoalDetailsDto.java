package com.project.tasker.goal.dto;

import java.time.Instant;
import java.util.UUID;

public record GoalDetailsDto(UUID id, String title, String description, Instant dueDate, Instant createdAt) {

}
