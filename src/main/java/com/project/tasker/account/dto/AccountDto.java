package com.project.tasker.account.dto;

import java.time.Instant;
import java.util.UUID;

public record AccountDto(UUID id, String fullName, String email, boolean hasPassword, Instant memberSince) {

}
