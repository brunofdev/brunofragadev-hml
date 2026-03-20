package com.brunofragadev.feedback.entity;

import com.brunofragadev.security.auditoria.Auditable;
import com.brunofragadev.usuarios.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "feedback")
@EqualsAndHashCode(of = "id")
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
    private TipoFeedback tipoFeedback;

    // Se for GERAL (Home), fica NULL. Se for Projeto, guarda o ID do Projeto etc.
    @Column(name = "referencia_id", nullable = true)
    private Long referenciaId;

    public static Feedback criar(Usuario usuario, String descricao, Integer avaliacao, TipoFeedback tipoFeedback, Long referenciaId){
        Feedback f = new Feedback();
        validarDadosCriacao(usuario, descricao, avaliacao);
        f.usuario = usuario;
        f.descricao = descricao;
        f.avaliacao = avaliacao;
        f.tipoFeedback = (tipoFeedback == null) ? TipoFeedback.GERAL : tipoFeedback;
        f.referenciaId = referenciaId;
        return f;
    }

    private static void validarUsuario(Usuario usuario) {
        if (usuario == null)
            throw new IllegalArgumentException("Usuário não pode ser nulo");
    }
    private static void validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Descrição não pode ser vazia");
    }
    private static void validarAvaliacao(Integer avaliacao) {
        if (avaliacao == null || avaliacao < 1 || avaliacao > 5)
            throw new IllegalArgumentException("Avaliação deve ser entre 1 e 5");
    }
    private static void validarDadosCriacao(Usuario usuario, String descricao, Integer avaliacao ){
        validarUsuario(usuario);
        validarDescricao(descricao);
        validarAvaliacao(avaliacao);
    }

    public void setDescricao(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    public void setAvaliacao(Integer avaliacao) {
        validarAvaliacao(avaliacao);
        this.avaliacao = avaliacao;
    }
}