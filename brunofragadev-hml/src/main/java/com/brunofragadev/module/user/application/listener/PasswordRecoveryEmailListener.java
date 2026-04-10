package com.brunofragadev.module.user.application.listener;

import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.user.domain.event.PasswordChangedEvent;
import com.brunofragadev.module.user.domain.event.PasswordChangeRequestedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PasswordRecoveryEmailListener {

    private final EmailService emailService;

    public PasswordRecoveryEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPasswordIsChanged(PasswordChangedEvent event){
        emailService.sendPasswordChangedSuccessfullyEmail(event.email(), event.userName());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPasswordChangeRequest (PasswordChangeRequestedEvent event){
        emailService.sendPasswordRecoveryEmail(event.email(), event.userName(), event.verificationCode());
    }

}
