package com.project.tasker.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.email")
public record EmailProperties(String passwordResetUrl) {

}
