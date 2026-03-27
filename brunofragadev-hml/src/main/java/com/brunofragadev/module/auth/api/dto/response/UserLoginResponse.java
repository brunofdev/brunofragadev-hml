package com.brunofragadev.module.auth.api.dto.response;

import com.brunofragadev.module.user.api.dto.response.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta padrão de sucesso contendo a chave de acesso e os dados do usuário")
public record UserLoginResponse(
        @Schema(description = "Token JWT Bearer para acesso às rotas protegidas da API", example = "eyJhbGciOiJIUzI1NiIsInR5c...")
        String token,

        @Schema(description = "Objeto detalhado com as informações do perfil logado")
        UserDTO clienteDTO
) {}