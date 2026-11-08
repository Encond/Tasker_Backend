package com.project.tasker.goal;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.tasker.account.Account;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoalService {

	private final GoalRepository goalRepository;

	public Goal create(final String title, final String description, final Instant dueDate, final Account account) {
		return this.goalRepository.save(Goal.of(title, description, dueDate, account));
	}

	public Optional<Goal> get(final UUID id, final UUID accountId) {
		return this.goalRepository.findByIdAndAccountId(id, accountId);
	}

	public void delete(final UUID id, final UUID accountId) {
		// TODO: Remove tasks first
		this.goalRepository.deleteByIdAndAccountId(id, accountId);
	}

}
