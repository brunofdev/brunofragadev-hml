package com.brunofragadev.module.project.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ficha técnica detalhando as tecnologias do projeto")
public record TechnicalSheetDTO(
        @Schema(description = "Linguagem de programação principal", example = "Java 21")
        String linguagem,

        @Schema(description = "Paradigma ou arquitetura principal", example = "Microsserviços, Clean Architecture")
        String paradigma,

        @Schema(description = "Framework base", example = "Spring Boot 3.4")
        String framework,

        @Schema(description = "Principais bibliotecas secundárias", example = "Spring Security, JWT, Lombok")
        String bibliotecas,

        @Schema(description = "Ferramentas de infraestrutura, cloud e banco de dados", example = "Docker, RabbitMQ, PostgreSQL")
        String infraestrutura
) {}