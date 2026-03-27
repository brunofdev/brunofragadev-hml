package com.brunofragadev.module.user.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Schema(description = "Formulário para atualização de dados do perfil público e privado do usuário")
public record UpdateProfileData(

        @Schema(description = "Nome que será exibido publicamente no sistema", example = "Bruno Dev", nullable = true)
        String nomePublico,

        @Schema(description = "Flag para ocultar a identidade do usuário em interações (ex: Feedbacks)", example = "false", nullable = true)
        Boolean isAnonimo,

        @Schema(description = "Cargo ou profissão atual", example = "Desenvolvedor de Software", nullable = true)
        @Size(max = 100, message = "A profissão deve ter no máximo 100 caracteres.")
        String profissao,

        @Schema(description = "Telefone celular padrão Brasil (com ou sem +55)", example = "+5548999999999", nullable = true)
        @Pattern(regexp = "^(\\+55)?[1-9]{2}9\\d{8}$", message = "Informe um celular brasileiro válido com DDD.")
        String telefone,

        @Schema(description = "País de residência", example = "Brasil", nullable = true)
        @Size(max = 50, message = "O país deve ter no máximo 50 caracteres.")
        String pais,

        @Schema(description = "Cidade e Estado de residência", example = "Florianópolis/SC", nullable = true)
        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
        String cidade,

        @Schema(description = "Link para o perfil no GitHub", example = "https://github.com/brunofdev", nullable = true)
        @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/.*", message = "Deve ser uma URL válida do GitHub.")
        String gitHub,

        @Schema(description = "Link para o perfil no LinkedIn", example = "https://linkedin.com/in/bruno-de-fraga", nullable = true)
        @Pattern(regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*", message = "Deve ser uma URL válida do LinkedIn.")
        String linkedin,

        @Schema(description = "Breve biografia ou resumo profissional", example = "Desenvolvedor apaixonado por arquitetura de software.", nullable = true)
        @Size(max = 1000, message = "A bio deve ter no máximo 1000 caracteres.")
        String bio,

        @Schema(description = "Link direto para a imagem de perfil", example = "https://meusite.com/foto.jpg", nullable = true)
        @URL(message = "A foto de perfil deve ser um link (URL) válido.")
        String fotoPerfil
) {}