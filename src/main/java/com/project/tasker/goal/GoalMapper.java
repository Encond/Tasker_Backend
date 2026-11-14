package com.project.tasker.goal;

import org.springframework.stereotype.Component;

import com.project.tasker.goal.dto.GoalDetailsDto;

@Component
public class GoalMapper {

	public GoalDetailsDto toDetailsDto(final Goal goal) {
		return new GoalDetailsDto(goal.getId(), goal.getTitle(), goal.getDescription(), goal.getDueDate(), goal.getCreatedAt());
	}

}
