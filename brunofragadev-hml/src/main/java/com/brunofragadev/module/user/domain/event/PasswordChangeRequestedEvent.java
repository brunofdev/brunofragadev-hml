package com.brunofragadev.module.user.domain.event;

public record PasswordChangeRequestedEvent(
        String email,
        String userName,
        String verificationCode
) {
}
