package com.brunofragadev.module.project.api.dto.request;

import com.brunofragadev.module.project.api.dto.response.ProjectImageDTO;
import com.brunofragadev.module.project.api.dto.response.ProjectSetupDTO;
import com.brunofragadev.module.project.api.dto.response.TechnicalSheetDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Objeto contendo os dados necessários para cadastrar ou atualizar um projeto no portfólio")
public record ProjectRequest(
        @Schema(description = "Título principal do projeto", example = "API de Gestão de Restaurantes")
        String title,

        @Schema(description = "Descrição detalhada sobre o problema resolvido", example = "Sistema desenvolvido para controlar pedidos e estoque...")
        String description,

        @Schema(description = "URL do vídeo de demonstração (YouTube/Vimeo)", example = "https://youtube.com/watch?v=12345")
        String video,

        @Schema(description = "Status de desenvolvimento atual", example = "Concluído")
        String status,

        @Schema(description = "Sua função específica no desenvolvimento", example = "Desenvolvedor Backend")
        String papel,

        @Schema(description = "Data ou período de execução do projeto", example = "Fev/2026 - Mar/2026")
        String dataProjeto,

        @Schema(description = "Link para o código-fonte no GitHub", example = "https://github.com/brunofdev/sistema-restaurante")
        String repositorioUrl,

        @Schema(description = "Link para o projeto rodando em produção", example = "https://api.meurestaurante.com")
        String liveUrl,

        @Schema(description = "Ficha técnica com a stack utilizada")
        TechnicalSheetDTO techs,

        @Schema(description = "Instruções de configuração e execução local")
        ProjectSetupDTO setup,

        @Schema(description = "Lista de imagens para a galeria do projeto")
        List<ProjectImageDTO> galeria
) {}