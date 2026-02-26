package com.brunofragadev.feedback.dto;

import com.brunofragadev.feedback.entity.TipoFeedback;

public record CriarFeedbackDTO(
        String descricao,
        Integer avaliacao,
        //pode receber null, sera salvo na pagina principal
        TipoFeedback tipoFeedback,
        Long referenciaId
) {
}
