package com.project.tasker.dashboard;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tasker.dashboard.dto.DashboardDto;
import com.project.tasker.goal.GoalRepository;
import com.project.tasker.task.TaskRepository;

import lombok.AllArgsConstructor;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class DashboardManagementService {

	private final GoalRepository goalRepository;
	private final TaskRepository taskRepository;

	public DashboardDto getDashboard(final UUID accountId) {
		// @formatter:off
		return new DashboardDto(this.goalRepository.countActiveGoals(accountId),
								this.goalRepository.countCompletedGoals(accountId),
								this.taskRepository.countByGoalAccountIdAndCompletedTrue(accountId)
		);
		// @formatter:on
	}

}
