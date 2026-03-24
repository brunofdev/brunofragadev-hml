package com.brunofragadev.core.project.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FichaTecnica {
    private String linguagem;
    private String paradigma;
    private String framework;
    private String bibliotecas;
    private String infraestrutura;
}