package com.brunofragadev.core.project.dto;

import java.util.List;

public record SetupProjetoDTO(
        String obs,
        List<PassoSetupDTO> steps
) {}