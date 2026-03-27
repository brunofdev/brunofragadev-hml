package com.brunofragadev.module.user.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta simplificada contendo o e-mail para confirmação do envio das instruções de recuperação.")
public record PasswordRecoveryResponse(
        @Schema(description = "E-mail de destino mascarado (oculto) para confirmação visual do usuário.", example = "b***@email.com")
        String email
) {}