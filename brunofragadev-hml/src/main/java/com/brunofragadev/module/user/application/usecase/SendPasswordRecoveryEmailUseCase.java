package com.brunofragadev.module.user.application.usecase;

import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.user.api.dto.response.PasswordRecoveryResponse;
import com.brunofragadev.module.user.domain.entity.User;
import com.brunofragadev.module.user.domain.event.PasswordChangeRequestedEvent;
import com.brunofragadev.module.user.domain.exception.UserEmailNotRegisteredException;
import com.brunofragadev.module.user.domain.repository.UserRepository;
import com.brunofragadev.shared.util.text.Formatters;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class SendPasswordRecoveryEmailUseCase {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;

    public SendPasswordRecoveryEmailUseCase(UserRepository userRepository, EmailService emailService, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PasswordRecoveryResponse execute(String userNameOrEmail) {
        User user = userRepository.findByUserNameOrEmail(userNameOrEmail.toUpperCase(), userNameOrEmail.toUpperCase())
                .orElseThrow(() -> new UserEmailNotRegisteredException("Email or Username not found"));
        String verificationCode = generateVerificationCode();
        user.definirCodigoVerificacao(verificationCode, LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        emailService.sendPasswordRecoveryEmail(user.getEmail(), user.getUsername(), verificationCode);
        eventPublisher.publishEvent(new PasswordChangeRequestedEvent(user.getEmail(), user.getUsername(), verificationCode));
        return new PasswordRecoveryResponse(Formatters.ofuscarEmail(user.getEmail()));
    }

    private String generateVerificationCode() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }
}