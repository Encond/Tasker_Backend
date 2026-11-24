package com.project.tasker.task;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tasker.security.AuthPrincipal;
import com.project.tasker.security.Principal;
import com.project.tasker.task.dto.TaskDto;
import com.project.tasker.task.request.TaskCreateRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskManagementService taskManagementService;

	@PostMapping
	public TaskDto create(@AuthPrincipal final Principal principal, @Valid @RequestBody final TaskCreateRequest request) {
		return this.taskManagementService.create(request, principal.id());
	}

	@GetMapping("/{goalId}")
	// @formatter:off
	public Slice<TaskDto> getAll(@AuthPrincipal 							   final Principal principal,
																			   final Pageable pageable,
								 @RequestParam(defaultValue = "DUE_DATE_DESC") final TaskSort sort,
								 @PathVariable 								   final UUID goalId) {
	// @formatter:on
		return this.taskManagementService.getAll(goalId, principal.id(), pageable, sort);
	}

	@PutMapping("/{id}")
	// @formatter:off
	public TaskDto update(@AuthPrincipal 					  final Principal principal,
						  @PathVariable  					  final UUID id,
						  @RequestParam(defaultValue = "LOW") final TaskPriority priority) {
	// @formatter:on
		return this.taskManagementService.update(id, priority, principal.id());
	}

	@PostMapping("/{goalId}/complete/all")
	public void markAllAsComplete(@AuthPrincipal final Principal principal, @PathVariable final UUID goalId) {
		this.taskManagementService.markAllAsComplete(goalId, principal.id());
	}

	@PostMapping("/{goalId}/incomplete/all")
	public void markAllAsIncomplete(@AuthPrincipal final Principal principal, @PathVariable final UUID goalId) {
		this.taskManagementService.markAllAsIncomplete(goalId, principal.id());
	}

	@PostMapping("/{goalId}/complete")
	public void markAsComplete(@AuthPrincipal final Principal principal, @PathVariable final UUID goalId) {
		this.taskManagementService.markAsComplete(goalId, principal.id());
	}

	@PostMapping("/{goalId}/incomplete")
	public void markAsIncomplete(@AuthPrincipal final Principal principal, @PathVariable final UUID goalId) {
		this.taskManagementService.markAsIncomplete(goalId, principal.id());
	}

	@DeleteMapping("/{id}")
	public void delete(@AuthPrincipal final Principal principal, @PathVariable final UUID id) {
		this.taskManagementService.delete(id, principal.id());
	}

}
