package com.brunofragadev.module.project.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Resposta contendo todos os detalhes estruturais de um projeto publicado")
public record ProjectResponse(
        @Schema(description = "ID único do projeto no banco de dados", example = "1")
        Long id,

        @Schema(description = "Título principal do projeto", example = "API de Gestão de Restaurantes")
        String title,

        @Schema(description = "Descrição detalhada sobre o problema resolvido")
        String description,

        @Schema(description = "URL do vídeo de demonstração")
        String video,

        @Schema(description = "Status de desenvolvimento atual", example = "Concluído")
        String status,

        @Schema(description = "Sua função específica no desenvolvimento", example = "Arquiteto de Software")
        String papel,

        @Schema(description = "Data ou período de execução do projeto")
        String dataProjeto,

        @Schema(description = "Link para o código-fonte no GitHub")
        String repositorioUrl,

        @Schema(description = "Link para o projeto rodando em produção")
        String liveUrl,

        @Schema(description = "Ficha técnica com a stack utilizada")
        TechnicalSheetDTO techs,

        @Schema(description = "Instruções de configuração e execução local")
        ProjectSetupDTO setup,

        @Schema(description = "Lista de imagens renderizadas na galeria")
        List<ProjectImageDTO> galeria
) {}