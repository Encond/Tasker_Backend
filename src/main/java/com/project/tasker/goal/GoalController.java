package com.project.tasker.goal;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tasker.goal.dto.GoalDetailsDto;
import com.project.tasker.goal.dto.GoalDto;
import com.project.tasker.goal.request.GoalCreateRequest;
import com.project.tasker.security.AuthPrincipal;
import com.project.tasker.security.Principal;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/goals")
public class GoalController {

	private final GoalManagementService goalManagementService;

	@PostMapping
	public GoalDetailsDto create(@AuthPrincipal final Principal principal, @Valid @RequestBody GoalCreateRequest request) {
		return this.goalManagementService.create(request, principal.id());
	}

	@GetMapping
	// @formatter:off
	public Slice<GoalDto> getAll(@AuthPrincipal 						final Principal principal,
																		final Pageable pageable,
								 @RequestParam(defaultValue = "LATEST") final GoalSort sort) {
	// @formatter:on
		return this.goalManagementService.getAll(principal.id(), pageable, sort);
	}

	@GetMapping("/id")
	public GoalDetailsDto get(@AuthPrincipal final Principal principal, @PathVariable final UUID id) {
		return this.goalManagementService.get(id, principal.id());
	}

	@DeleteMapping("/id")
	public void delete(@AuthPrincipal final Principal principal, @PathVariable final UUID id) {
		this.goalManagementService.delete(id, principal.id());
	}

}
