package com.brunofragadev.module.user.application.listener;

import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.user.domain.event.GeneratedCodeEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class SendEmailCodeVerificationListener {

    private final EmailService emailService;

    public SendEmailCodeVerificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCodeVerificationGenereted(GeneratedCodeEvent event){
        emailService.sendVerificationEmail(event.user().getEmail(), event.user().getNome(), event.user().getVerificationCode().getCodigo());
    }
}
