package com.brunofragadev.projetos.dto;

import java.util.List;

public record ProjetoRequestDTO(
        String title,
        String description,
        String video,
        String status,
        String papel,
        String dataProjeto,
        String repositorioUrl,
        String liveUrl,
        FichaTecnicaDTO techs,
        SetupProjetoDTO setup,
        List<ImagemProjetoDTO> galeria
) {}