package com.project.tasker.task;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tasker.goal.Goal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public Task create(final String description, final TaskPriority priority, final Instant dueDate, final Goal goal) {
		return this.taskRepository.save(Task.of(description, priority, dueDate, goal));
	}

	@Transactional
	public Task update(final UUID id, final TaskPriority priority, final UUID accountId) {
		final Task task = this.taskRepository.findByIdAndGoalAccountId(id, accountId).orElseThrow(/* TODO: Exception */);
		task.updatePriority(priority);
		return task;
	}

	public void markAllAsComplete(final UUID goalId, final UUID accountId) {
		this.taskRepository.markAllAsComplete(goalId, accountId);
	}

	public void markAllAsIncomplete(final UUID goalId, final UUID accountId) {
		this.taskRepository.markAllAsIncomplete(goalId, accountId);
	}

	@Transactional
	public void markAsComplete(final UUID id, final UUID accountId) {
		this.taskRepository.findByIdAndGoalAccountId(id, accountId).ifPresent(task -> task.markAsComplete());
	}

	@Transactional
	public void markAsIncomplete(final UUID id, final UUID accountId) {
		this.taskRepository.findByIdAndGoalAccountId(id, accountId).ifPresent(task -> task.markAsIncomplete());
	}

	public void deleteAll(final UUID goalId, final UUID accountId) {
		this.taskRepository.deleteAllByGoalIdAndGoalAccountId(goalId, accountId);
	}

	public void delete(final UUID id, final UUID accountId) {
		this.taskRepository.deleteByIdAndGoalAccountId(id, accountId);
	}

}
