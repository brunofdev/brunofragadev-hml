package com.brunofragadev.module.project.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de uma imagem vinculada à galeria do projeto")
public record ProjectImageDTO(
        @Schema(description = "ID interno da imagem", example = "42")
        Long id,

        @Schema(description = "URL pública onde a imagem está armazenada", example = "https://storage.com/projeto-capa.jpg")
        String urlImagem,

        @Schema(description = "Ordem em que a imagem deve aparecer no carrossel", example = "1")
        Integer ordemExibicao,

        @Schema(description = "Define se esta imagem será a miniatura principal do card do projeto", example = "true")
        Boolean isCapa,

        @Schema(description = "Texto alternativo ou legenda da foto", example = "Diagrama de Arquitetura do Sistema")
        String legenda
) {}