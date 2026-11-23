package com.project.tasker.task;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.tasker.task.dto.TaskDto;

public interface TaskRepository extends JpaRepository<Task, UUID> {

	@Query("""
			SELECT NEW com.project.tasker.task.dto.TaskDto(
				t.id,
				t.description,
				t.priority,
				t.dueDate,
				t.completed,
				t.createdAt
			)
			FROM Task t
			WHERE t.goal.id = :goalId AND t.goal.account.id = :accountId
			""")
	Slice<TaskDto> findAllDtosByGoalIdAndGoalAccountId(UUID goalId, UUID accountId, Pageable pageable);

	Optional<Task> findByIdAndGoalAccountId(UUID id, UUID accountId);

	@Query("""
			UPDATE Task t
			SET t.completed = true
			WHERE t.goal.id = :goalId AND t.goal.account.id = :accountId AND t.completed = false
			""")
	int markAllAsComplete(UUID goalId, UUID accountId);

	@Query("""
			UPDATE Task t
			SET t.completed = false
			WHERE t.goal.id = :goalId AND t.goal.account.id = :accountId AND t.completed = true
			""")
	int markAllAsIncomplete(UUID goalId, UUID accountId);

	void deleteAllByGoalIdAndGoalAccountId(UUID goalId, UUID accountId);

	void deleteByIdAndGoalAccountId(UUID id, UUID accountId);

	long countByGoalAccountIdAndCompletedTrue(UUID accountId);

}
