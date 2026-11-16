package com.project.tasker.task;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.project.tasker.goal.Goal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskPriority priority;

	@Column(nullable = false)
	private Instant dueDate;

	@Column(nullable = false)
	private boolean completed;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Goal goal;

	private Task(final String description, final TaskPriority priority, final Instant dueDate, final Goal goal) {
		super();

		this.description = description;
		this.priority = priority;
		this.dueDate = dueDate;
		this.goal = goal;
	}

	public static Task of(final String description, final TaskPriority priority, final Instant dueDate, final Goal goal) {
		return new Task(description, priority, dueDate, goal);
	}

	public void updatePriority(final TaskPriority priority) {
		this.priority = priority;
	}

	public void markAsComplete() {
		this.completed = true;
	}

	public void markAsIncomplete() {
		this.completed = false;
	}

}
