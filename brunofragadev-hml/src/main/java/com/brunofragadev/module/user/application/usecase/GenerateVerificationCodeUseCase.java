package com.brunofragadev.module.user.application.usecase;

import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.user.domain.entity.User;
import com.brunofragadev.module.user.domain.event.GeneratedCodeEvent;
import com.brunofragadev.module.user.domain.exception.UserNotFoundException;
import com.brunofragadev.module.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class GenerateVerificationCodeUseCase {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public GenerateVerificationCodeUseCase(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void execute(String userName) {
        User user = userRepository.findByUserName(userName.toUpperCase())
                .orElseThrow(() -> new UserNotFoundException("Nome de usuario não encontrado"));
        String verificationCode = generateVerificationCode();
        user.definirCodigoVerificacao(verificationCode, LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        eventPublisher.publishEvent(new GeneratedCodeEvent(user));
    }

    private String generateVerificationCode() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }
}