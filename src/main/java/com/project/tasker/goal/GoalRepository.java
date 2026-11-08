package com.project.tasker.goal;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

	Optional<Goal> findByIdAndAccountId(UUID id, UUID accountId);

	void deleteByIdAndAccountId(UUID id, UUID accountId);

}
