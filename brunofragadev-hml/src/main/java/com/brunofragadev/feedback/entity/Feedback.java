package com.brunofragadev.feedback.entity;

import com.brunofragadev.security.auditoria.Auditable;
import com.brunofragadev.usuarios.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "avaliacao", nullable = false)
    private Integer avaliacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_feedback", nullable = false)
    private TipoFeedback tipoFeedback = TipoFeedback.GERAL;

    // Se for GERAL (Home), fica NULL. Se for Projeto, guarda o ID do Projeto etc.
    @Column(name = "referencia_id", nullable = true)
    private Long referenciaId;

}