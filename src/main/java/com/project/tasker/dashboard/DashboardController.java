package com.project.tasker.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tasker.dashboard.dto.DashboardDto;
import com.project.tasker.security.AuthPrincipal;
import com.project.tasker.security.Principal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	private final DashboardManagementService dashboardManagementService;

	@GetMapping
	public DashboardDto getDashboard(@AuthPrincipal final Principal principal) {
		return this.dashboardManagementService.getDashboard(principal.id());
	}

}
