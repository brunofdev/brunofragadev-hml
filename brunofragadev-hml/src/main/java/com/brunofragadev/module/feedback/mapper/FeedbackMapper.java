package com.brunofragadev.module.feedback.mapper;

import com.brunofragadev.module.feedback.entity.Feedback;
import com.brunofragadev.module.feedback.dto.request.FeedbackCreateRequest;
import com.brunofragadev.module.feedback.dto.response.FeedbackDTO;
import com.brunofragadev.module.user.domain.entity.User;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class FeedbackMapper {

    public Feedback mapearFeedbackCriacao(FeedbackCreateRequest dto, User user){
        return Feedback.criar(
                user,
                dto.descricao(),
                dto.avaliacao(),
                dto.feedbackType(),
                dto.referenciaId()
        );
    }
    //VERIFICAR, POIS EXISTE UM CONSULTA NO REPOSITORIO QUE RETORNA UM DTO DIRETAMENTE,
    // NO UNICO CASO DE USO ATE O MOMENTO
    public FeedbackDTO mapearFeedbackDTO(Feedback feedback){
        return new FeedbackDTO(
                feedback.getId(),
                feedback.getUser().getUsername(),
                feedback.getUser().getUsername(),
                feedback.getDescricao(),
                feedback.getAvaliacao(),
                feedback.getDataCriacao(),
                feedback.getUser().getFotoperfil(),
                feedback.getFeedbackType(),
                feedback.getReferenciaId(),
                feedback.getUser().getIsAnonimo()
        );
    }
    public List<FeedbackDTO> mapearListaDeFeedbacks(List<Feedback> feedbacks){
        return feedbacks.stream()
                .map(this::mapearFeedbackDTO).toList();
    }
}
