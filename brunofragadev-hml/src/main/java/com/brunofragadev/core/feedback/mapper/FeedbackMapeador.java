package com.brunofragadev.core.feedback.mapper;

import com.brunofragadev.core.feedback.entity.Feedback;
import com.brunofragadev.core.feedback.dto.entrada.CriarFeedbackDTO;
import com.brunofragadev.core.feedback.dto.saida.FeedbackDTO;
import com.brunofragadev.core.user.entity.Usuario;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class FeedbackMapeador {

    public Feedback mapearFeedbackCriacao(CriarFeedbackDTO dto, Usuario usuario){
        return Feedback.criar(
                usuario,
                dto.descricao(),
                dto.avaliacao(),
                dto.tipoFeedback(),
                dto.referenciaId()
        );
    }
    //VERIFICAR, POIS EXISTE UM CONSULTA NO REPOSITORIO QUE RETORNA UM DTO DIRETAMENTE,
    // NO UNICO CASO DE USO ATE O MOMENTO
    public FeedbackDTO mapearFeedbackDTO(Feedback feedback){
        return new FeedbackDTO(
                feedback.getId(),
                feedback.getUsuario().getUsername(),
                feedback.getUsuario().getUsername(),
                feedback.getDescricao(),
                feedback.getAvaliacao(),
                feedback.getDataCriacao(),
                feedback.getUsuario().getFotoperfil(),
                feedback.getTipoFeedback(),
                feedback.getReferenciaId(),
                feedback.getUsuario().getIsAnonimo()
        );
    }
    public List<FeedbackDTO> mapearListaDeFeedbacks(List<Feedback> feedbacks){
        return feedbacks.stream()
                .map(this::mapearFeedbackDTO).toList();
    }
}
