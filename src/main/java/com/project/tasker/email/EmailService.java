package com.project.tasker.email;

import java.util.Locale;
import java.util.Map;

import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailService {

	private final EmailProperties emailProperties;

	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;

	public void sendPasswordResetToken(final String email, final String token) {
		final String resetUrl = String.format("%s?token=%s&email=%s", this.emailProperties.passwordResetUrl(), token, email);
		final Context context = new Context(Locale.getDefault(), Map.of("resetUrl", resetUrl));
		final String html = this.templateEngine.process("password-reset", context);
		this.sendHtml(email, "Password reset request", html);
	}

	private void sendHtml(final String email, final String subject, final String html) {
		final MimeMessage message = this.javaMailSender.createMimeMessage();

		try {
			final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(html, true);
		} catch (MessagingException e) {
			throw new MailPreparationException("Failed to prepare email", e);
		}

		this.javaMailSender.send(message);
	}

}
