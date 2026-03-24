package com.brunofragadev.module.feedback.repository;

import com.brunofragadev.module.feedback.dto.response.FeedbackDTO;
import com.brunofragadev.module.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("""
        SELECT new com.brunofragadev.module.feedback.dto.response.FeedbackDTO(
            f.id,
            u.nomePublico,
            u.userName,
            f.descricao,
            f.avaliacao,
            f.dataCriacao,
            u.fotoperfil,
            f.feedbackType,
            f.referenciaId,
            u.isAnonimo
        )
        FROM Feedback f
        JOIN f.user u
        WHERE f.feedbackType = com.brunofragadev.module.feedback.entity.FeedbackType.GERAL
        ORDER BY f.dataCriacao DESC
    """)
    List<FeedbackDTO> buscarTodosTipoGeralComFotos();

    @Query("""
        SELECT new com.brunofragadev.module.feedback.dto.response.FeedbackDTO(
            f.id,
            u.nomePublico,
            u.userName,
            f.descricao,
            f.avaliacao,
            f.dataCriacao,
            u.fotoperfil,
            f.feedbackType,
            f.referenciaId,
            u.isAnonimo
        )
        FROM Feedback f
        JOIN f.user u
        WHERE f.feedbackType = com.brunofragadev.module.feedback.entity.FeedbackType.PROJETO
        AND f.referenciaId = :projetoId
        ORDER BY f.dataCriacao DESC
    """)
    List<FeedbackDTO> buscarTodosTipoProjetoComFotos(@org.springframework.data.repository.query.Param("projetoId") Long projetoId);

}
