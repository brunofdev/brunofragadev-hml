package com.brunofragadev.module.user.domain.event;

import com.brunofragadev.module.user.domain.entity.User;

public record UserRegisteredEvent(User user) {
}
