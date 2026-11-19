package com.project.tasker.task;

import com.project.tasker.task.dto.TaskDto;

public class TaskMapper {

	public TaskDto toDto(final Task task) {
		// @formatter:off
		return new TaskDto(task.getId(),
						   task.getDescription(),
						   task.getPriority(),
						   task.getDueDate(),
						   task.isCompleted(),
						   task.getCreatedAt()		
		);
		// @formatter:on
	}

}
