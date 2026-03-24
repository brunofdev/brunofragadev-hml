package com.brunofragadev.core.auth.dto;


import com.brunofragadev.core.user.entity.Role;

public record UserResponse(String username, Role role) {
}
