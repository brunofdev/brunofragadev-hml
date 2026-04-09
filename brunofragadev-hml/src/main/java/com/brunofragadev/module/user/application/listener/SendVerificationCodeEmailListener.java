package com.brunofragadev.module.user.application.listener;

import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.user.domain.event.UserRegisteredEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class SendVerificationCodeEmailListener {

    private final EmailService emailService;

    public SendVerificationCodeEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserRegistered(UserRegisteredEvent event){
        String userEmail = event.user().getEmail();
        String userName = event.user().getNome();
        String verificationCode = event.user().getVerificationCode().getCodigo();
        emailService.sendVerificationEmail(userEmail, userName, verificationCode);
    }
}
