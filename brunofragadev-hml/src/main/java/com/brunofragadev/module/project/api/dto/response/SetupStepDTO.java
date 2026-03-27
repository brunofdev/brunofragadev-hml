package com.brunofragadev.module.project.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Passo individual para configuração de ambiente")
public record SetupStepDTO(
        @Schema(description = "Número do passo na sequência", example = "1")
        Integer num,

        @Schema(description = "Descrição legível da ação", example = "Clone o repositório e acesse a pasta")
        String text,

        @Schema(description = "Comando de terminal a ser executado (opcional)", example = "git clone https://... && cd projeto")
        String cmd
) {}