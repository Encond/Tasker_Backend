package com.project.tasker.passwordreset;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.password-reset")
public record PasswordResetTokenProperties(Duration expiration) {

}
