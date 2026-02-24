package com.brunofragadev.projetos.entitys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PassoSetup {
    private Integer num;
    private String text;
    private String cmd;
}