package com.project.tasker.goal;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.tasker.goal.dto.GoalDto;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

	@Query("""
			SELECT NEW com.project.tasker.goal.dto.GoalDto(
				g.id,
				g.title,
				g.description,
				g.dueDate,
				g.createdAt,
				SUM(CASE WHEN t.completed = false THEN 1 ELSE 0 END),
				SUM(CASE WHEN t.completed = true THEN 1 ELSE 0 END)
			)
			FROM Goal g
			LEFT JOIN Task t ON t.goal = g
			WHERE g.account.id = :accountId
			GROUP BY g.id, g.title, g.description, g.dueDate, g.createdAt
			""")
	Slice<GoalDto> findAllDtosByAccountId(UUID accountId, Pageable pageable);

	@Query("""
			SELECT NEW com.project.tasker.goal.dto.GoalDto(
				g.id,
				g.title,
				g.description,
				g.dueDate,
				g.createdAt,
				SUM(CASE WHEN t.completed = false THEN 1 ELSE 0 END),
				SUM(CASE WHEN t.completed = true THEN 1 ELSE 0 END)
			)
			FROM Goal g
			LEFT JOIN Task t ON t.goal = g
			WHERE g.account.id = :accountId
			GROUP BY g.id, g.title, g.description, g.dueDate, g.createdAt
			ORDER BY SUM(CASE WHEN t.completed = true THEN 1 ELSE 0 END) DESC, g.id ASC
			""")
	Slice<GoalDto> findAllDtosByAccountIdOrderByProgressDesc(UUID accountId, Pageable pageable);

	@Query("""
			SELECT NEW com.project.tasker.goal.dto.GoalDto(
				g.id,
				g.title,
				g.description,
				g.dueDate,
				g.createdAt,
				SUM(CASE WHEN t.completed = false THEN 1 ELSE 0 END),
				SUM(CASE WHEN t.completed = true THEN 1 ELSE 0 END)
			)
			FROM Goal g
			LEFT JOIN Task t ON t.goal = g
			WHERE g.account.id = :accountId
			GROUP BY g.id, g.title, g.description, g.dueDate, g.createdAt
			ORDER BY SUM(CASE WHEN t.completed = true THEN 1 ELSE 0 END) ASC, g.id ASC
			""")
	Slice<GoalDto> findAllDtosByAccountIdOrderByProgressAsc(UUID accountId, Pageable pageable);

	Optional<Goal> findByIdAndAccountId(UUID id, UUID accountId);

	void deleteByIdAndAccountId(UUID id, UUID accountId);

}
