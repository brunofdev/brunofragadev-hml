package com.brunofragadev.module.user.domain.event;

import com.brunofragadev.module.user.api.dto.response.UserDTO;


public record ActivateAccountEvent(UserDTO userDTO) {
}
