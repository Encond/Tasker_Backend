package com.project.tasker.goal;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tasker.account.Account;
import com.project.tasker.account.AccountService;
import com.project.tasker.goal.dto.GoalDetailsDto;
import com.project.tasker.goal.request.GoalCreateRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoalManagementService {

	private final GoalService goalService;
	private final GoalMapper goalMapper;

	private final AccountService accountService;

	@Transactional
	public GoalDetailsDto create(final GoalCreateRequest request, final UUID accountId) {
		final Account account = this.accountService.get(accountId).orElseThrow(/* TODO: Exception */);
		final Goal goal = this.goalService.create(request.title(), request.description(), request.dueDate(), account);
		return this.goalMapper.toDetailsDto(goal);
	}

	public GoalDetailsDto get(final UUID id, final UUID accountId) {
		return this.goalService.get(id, accountId).map(this.goalMapper::toDetailsDto).orElseThrow(/* TODO: Exception */);
	}

	public void delete(final UUID id, final UUID accountId) {
		this.goalService.delete(id, accountId);
	}

}
