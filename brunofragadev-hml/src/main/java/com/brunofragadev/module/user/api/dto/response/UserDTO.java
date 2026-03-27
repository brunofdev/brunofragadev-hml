package com.brunofragadev.module.user.api.dto.response;

import com.brunofragadev.module.user.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO completo e detalhado contendo todos os dados cadastrais, de perfil e segurança do usuário.")
public record UserDTO(
        @Schema(description = "Identificador único do usuário gerado pelo banco de dados", example = "1")
        Long id,

        @Schema(description = "Nome completo e real do usuário (para fins administrativos)", example = "Bruno de Fraga")
        String nome,

        @Schema(description = "Nome de usuário único para login e identificação no sistema (Username)", example = "brunofdev")
        String userName,

        @Schema(description = "Nome que será exibido publicamente no portfólio e interações", example = "Bruno Dev")
        String nomePublico,

        @Schema(description = "Flag indicando se o usuário optou por ocultar sua identidade em interações públicas", example = "false")
        Boolean isAnonimo,

        @Schema(description = "Endereço de e-mail verificado do usuário", example = "bruno@email.com")
        String email,

        @Schema(description = "Nível de permissão (Role) atribuído ao usuário", example = "ADMIN3")
        Role role,

        @Schema(description = "Indica se a conta está ativada e pronta para uso", example = "true")
        Boolean contaAtiva,

        @Schema(description = "Cidade e Estado de residência (para exibição de perfil)", example = "Canoas/RS")
        String cidade,

        @Schema(description = "URL completa para o perfil no GitHub", example = "https://github.com/brunofdev")
        String gitHub,

        @Schema(description = "Cargo ou profissão principal atual", example = "Desenvolvedor Backend")
        String profissao,

        @Schema(description = "Breve biografia ou resumo profissional (até 1000 caracteres)", example = "Engenheiro de Software apaixonado por Clean Architecture e SOLID.")
        String bio,

        @Schema(description = "URL direta para a foto de perfil armazenada em Cloud", example = "https://brunofragadev.com/images/perfil/foto.jpg")
        String fotoPerfil,

        @Schema(description = "URL completa para o perfil no LinkedIn", example = "https://linkedin.com/in/bruno-de-fraga")
        String linkedin,

        @Schema(description = "País de residência (para exibição de perfil)", example = "Brasil")
        String pais,

        @Schema(description = "Telefone de contato verificado (com DDI/DDD)", example = "+5548999999999")
        String telefone
) {}