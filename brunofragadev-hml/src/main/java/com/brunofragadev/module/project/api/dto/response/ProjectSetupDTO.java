package com.brunofragadev.module.project.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Objeto contendo o guia passo a passo para rodar a aplicação")
public record ProjectSetupDTO(
        @Schema(description = "Observações gerais antes de iniciar", example = "Certifique-se de ter o Docker instalado e rodando.")
        String obs,

        @Schema(description = "Lista sequencial de passos de execução")
        List<SetupStepDTO> steps
) {}