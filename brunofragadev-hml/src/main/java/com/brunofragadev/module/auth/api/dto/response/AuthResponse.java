package com.brunofragadev.module.auth.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estrutura simplificada de resposta de autenticação")
public record AuthResponse(
        @Schema(description = "Token JWT gerado para a sessão ativa", example = "eyJhbGciOiJIUzI1NiIsInR5c...")
        String token,

        @Schema(description = "Resumo dos dados de autorização do usuário")
        UserResponse userResponse
) {}