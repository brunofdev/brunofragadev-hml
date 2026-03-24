package com.brunofragadev.core.project.dto;

public record FichaTecnicaDTO(
        String linguagem, String paradigma, String framework,
        String bibliotecas, String infraestrutura
) {}