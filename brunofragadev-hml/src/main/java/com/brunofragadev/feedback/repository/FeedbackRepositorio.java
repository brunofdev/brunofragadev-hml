package com.brunofragadev.feedback.repository;

import com.brunofragadev.feedback.dto.FeedbackDTO;
import com.brunofragadev.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepositorio extends JpaRepository<Feedback, Long> {
    @Query("""
        SELECT new com.brunofragadev.feedback.dto.FeedbackDTO(
            f.id,
            u.userName,
            f.descricao,
            f.avaliacao,
            f.dataCriacao,
            u.fotoperfil
        )
        FROM Feedback f
        JOIN f.usuario u
        ORDER BY f.dataCriacao DESC
    """)
    List<FeedbackDTO> buscarTodosComFotos();

}
