package com.brunofragadev.core.project.dto;

public record ImagemProjetoDTO(
        Long id,
        String urlImagem,
        Integer ordemExibicao,
        Boolean isCapa,
        String legenda
) {}