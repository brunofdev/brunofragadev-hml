package com.brunofragadev.core.auth.dto;


import com.brunofragadev.core.user.dto.saida.UsuarioDTO;

public record UserLoginResponse(String token, UsuarioDTO clienteDTO) {
}
