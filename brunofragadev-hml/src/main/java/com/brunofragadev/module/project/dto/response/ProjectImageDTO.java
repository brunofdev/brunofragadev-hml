package com.brunofragadev.module.project.dto.response;

public record ProjectImageDTO(
        Long id,
        String urlImagem,
        Integer ordemExibicao,
        Boolean isCapa,
        String legenda
) {}