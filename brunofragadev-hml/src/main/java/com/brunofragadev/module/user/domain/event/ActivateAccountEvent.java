package com.brunofragadev.module.user.domain.event;

import com.brunofragadev.module.user.api.dto.response.UserDTO;
import com.brunofragadev.module.user.domain.entity.User;

public record ActivateAccountEvent(UserDTO userDTO) {
}
