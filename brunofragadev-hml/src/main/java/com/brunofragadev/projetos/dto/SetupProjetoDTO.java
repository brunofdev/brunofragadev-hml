package com.brunofragadev.projetos.dto;

import java.util.List;

public record SetupProjetoDTO(
        String obs,
        List<PassoSetupDTO> steps
) {}