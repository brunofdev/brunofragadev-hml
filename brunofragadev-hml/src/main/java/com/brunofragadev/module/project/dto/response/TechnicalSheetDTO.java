package com.brunofragadev.module.project.dto.response;

public record TechnicalSheetDTO(
        String linguagem, String paradigma, String framework,
        String bibliotecas, String infraestrutura
) {}