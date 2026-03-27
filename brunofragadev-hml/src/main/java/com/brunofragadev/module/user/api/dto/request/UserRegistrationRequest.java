package com.brunofragadev.module.user.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Formulário completo para abertura de uma nova conta no sistema")
public record UserRegistrationRequest(

        @Schema(description = "Nome completo do titular da conta", example = "Bruno de Fraga")
        @NotBlank(message = "O nome não pode estar em branco.")
        @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.")
        @Pattern(regexp = "^[A-Za-zÀ-ú\\s'-]+$", message = "O nome deve conter apenas letras, espaços e hifens.")
        String nome,

        @Schema(description = "E-mail válido que será utilizado para comunicação e recuperação", example = "bruno@email.com")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O formato do e-mail é inválido.")
        String email,

        @Schema(description = "Identificador único de sistema (Username)", example = "brunofdev")
        @NotBlank(message = "O nome de usuário não pode estar em branco.")
        @Size(min = 5, max = 20, message = "O nome de usuário deve ter entre 5 e 20 caracteres.")
        @Pattern(regexp = "\\S+", message = "O nome de usuário não pode conter espaços em branco.")
        String userName,

        @Schema(description = "Nome de exibição nas interfaces públicas", example = "Bruno Dev", nullable = true)
        String nomePublico,

        @Schema(description = "Senha de acesso inicial", example = "Senha@123")
        @NotBlank(message = "A senha não pode estar em branco.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
                message = "A senha deve conter no mínimo 8 caracteres, incluindo pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial.")
        String senha

) {}