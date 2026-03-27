package com.brunofragadev.module.auth.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais clássicas para login no sistema")
public record CredentialsRequest(
        @Schema(description = "Nome de usuário ou e-mail cadastrado", example = "bruno@email.com")
        @NotBlank(message = "O nome de usuário ou e-mail não pode estar em branco.")
        String userName,

        @Schema(description = "Senha secreta de acesso", example = "SenhaSegura123!")
        @NotBlank(message = "Senha não pode estar em branco.")
        String senha
) {}