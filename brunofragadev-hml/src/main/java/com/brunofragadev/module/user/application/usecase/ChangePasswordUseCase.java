package com.brunofragadev.module.user.application.usecase;

import com.brunofragadev.module.user.api.dto.request.PasswordChangeRequest;
import com.brunofragadev.module.user.domain.entity.User;
import com.brunofragadev.module.user.domain.event.PasswordChangedEvent;
import com.brunofragadev.module.user.domain.exception.UserEmailNotRegisteredException;
import com.brunofragadev.module.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    public ChangePasswordUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;

    }

    @Transactional
    public void execute(PasswordChangeRequest request) {
        String emailOrUsername = request.userName().toUpperCase();
        User user = userRepository.findByUserNameOrEmail(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new UserEmailNotRegisteredException("Email ou nome de usuario não encontrados"));
        user.validarCodigo(request.codigoVerificado());
        String encodedPassword = passwordEncoder.encode(request.novaSenha());
        user.alterarSenha(encodedPassword);
        userRepository.save(user);
        eventPublisher.publishEvent(new PasswordChangedEvent(user.getEmail(), user.getUsername()));
    }
}