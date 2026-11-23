package com.project.tasker.task;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.tasker.goal.Goal;
import com.project.tasker.goal.GoalService;
import com.project.tasker.task.dto.TaskDto;
import com.project.tasker.task.request.TaskCreateRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskManagementService {

	private final TaskService taskService;
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	private final GoalService goalService;

	public TaskDto create(final TaskCreateRequest request, final UUID accountId) {
		final Goal goal = this.goalService.get(request.goalId(), accountId).orElseThrow(/* TODO: Exception */);
		final Task task = this.taskService.create(request.description(), request.priority(), request.dueDate(), goal);
		return this.taskMapper.toDto(task);
	}

	public Slice<TaskDto> getAll(final UUID goalId, final UUID accountId, Pageable pageable, final TaskSort sort) {
		pageable = ((PageRequest) pageable).withSort(switch (sort) {
		case CREATED_AT_DESC -> Sort.by("createdAt").descending();
		case CREATED_AT_ASC -> Sort.by("createdAt").ascending();
		case DUE_DATE_DESC -> Sort.by("dueDate").descending();
		case DUE_DATE_ASC -> Sort.by("dueDate").ascending();
		case PRIORITY_DESC -> Sort.by("priority").descending();
		case PRIORITY_ASC -> Sort.by("priority").ascending();
		});

		return this.taskRepository.findAllDtosByGoalIdAndGoalAccountId(goalId, accountId, pageable);
	}

	public TaskDto update(final UUID id, final TaskPriority priority, final UUID accountId) {
		return this.taskMapper.toDto(this.taskService.update(id, priority, accountId));
	}

	public void markAllAsComplete(final UUID goalId, final UUID accountId) {
		this.taskService.markAllAsComplete(goalId, accountId);
	}

	public void markAllAsIncomplete(final UUID goalId, final UUID accountId) {
		this.taskService.markAllAsIncomplete(goalId, accountId);
	}

	public void markAsComplete(final UUID goalId, final UUID accountId) {
		this.taskService.markAsComplete(goalId, accountId);
	}

	public void markAsIncomplete(final UUID goalId, final UUID accountId) {
		this.taskService.markAsIncomplete(goalId, accountId);
	}

	public void delete(final UUID id, final UUID accountId) {
		this.taskService.delete(id, accountId);
	}

}
