package com.brunofragadev.module.user.domain.event;

public record PasswordChangedEvent(
        String email,
        String userName
) {
}
