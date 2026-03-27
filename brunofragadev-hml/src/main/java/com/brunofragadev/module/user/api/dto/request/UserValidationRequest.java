package com.brunofragadev.module.user.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Formulário para validação de conta ou autorização de ações usando código OTP (One Time Password)")
public record UserValidationRequest(

        @Schema(description = "Nome de usuário ou e-mail cadastrado", example = "bruno@email.com")
        @NotBlank(message = "O nome de usuário ou email não pode estar em branco.")
        String userName,

        @Schema(description = "Código de segurança numérico recebido por e-mail", example = "123456")
        @NotBlank(message = "O código não pode estar em branco.")
        String codigo
) {}