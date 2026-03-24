package com.brunofragadev.core.feedback.service;

import com.brunofragadev.infrastructure.email.ServicoDeEmail;
import com.brunofragadev.core.feedback.repository.FeedbackRepositorio;
import com.brunofragadev.core.feedback.dto.entrada.CriarFeedbackDTO;
import com.brunofragadev.core.feedback.dto.saida.FeedbackDTO;
import com.brunofragadev.core.feedback.entity.Feedback;
import com.brunofragadev.core.feedback.mapper.FeedbackMapeador;
import com.brunofragadev.shared.service.ReferenceResolver;
import com.brunofragadev.core.user.entity.Role;
import com.brunofragadev.core.user.entity.Usuario;
import com.brunofragadev.core.user.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServico {


    private final FeedbackRepositorio feedbackRepositorio;
    private final FeedbackMapeador feedbackMapeador;
    private final ServicoDeEmail servicoDeEmail;
    private final ReferenceResolver referenceResolver;

    public FeedbackServico (FeedbackRepositorio feedbackRepositorio, FeedbackMapeador feedbackMapeador, ServicoDeEmail servicoDeEmail, ReferenceResolver referenceResolver){
        this.feedbackRepositorio = feedbackRepositorio;
        this.feedbackMapeador = feedbackMapeador;
        this.servicoDeEmail = servicoDeEmail;
        this.referenceResolver = referenceResolver;
    }

    public FeedbackDTO criarFeedback(CriarFeedbackDTO dto, Usuario usuario){
        Feedback feedback = feedbackMapeador.mapearFeedbackCriacao(dto, usuario);
        feedbackRepositorio.save(feedback);
        FeedbackDTO feedbackDTO = feedbackMapeador.mapearFeedbackDTO(feedback);
        String localFeedback = referenceResolver.resolverNome(feedbackDTO.tipoFeedback(), feedbackDTO.referenciaId());
        servicoDeEmail.enviarAlertaDeNovoFeedbackParaAdmin(feedbackDTO, localFeedback);
        return feedbackDTO;
    }

    public List<FeedbackDTO> listarFeedbacksTipoGeral (){
        return feedbackRepositorio.buscarTodosTipoGeralComFotos();
    }

    public List<FeedbackDTO> listarFeedbacksTipoProjeto (Long idProjeto) {
        return feedbackRepositorio.buscarTodosTipoProjetoComFotos(idProjeto);
    }

    public FeedbackDTO atualizarFeedback(Long idFeedback, CriarFeedbackDTO dto, Usuario usuario) {
        Feedback feedback = feedbackRepositorio.findById(idFeedback)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado!"));
        if (!feedback.getUsuario().getId().equals(usuario.getId())) {
            throw new InvalidCredentialsException("Acesso negado: Você não tem permissão para editar este feedback.");
        }
        feedback.setDescricao(dto.descricao());
        feedback.setAvaliacao(dto.avaliacao());
        feedbackRepositorio.save(feedback);
        return feedbackMapeador.mapearFeedbackDTO(feedback);
    }
    public void excluirFeedback(Long idFeedback, Usuario usuario) {
        Feedback feedback = feedbackRepositorio.findById(idFeedback)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado!"));

        boolean isDonoDoComentario = feedback.getUsuario().getId().equals(usuario.getId());
        boolean isAdmin = usuario.getRole() == Role.ADMIN3; // Ajuste o getRole() se o seu método chamar de forma diferente

        if (!isDonoDoComentario && !isAdmin) {
            throw new RuntimeException("Acesso negado: Você não tem permissão para excluir este feedback.");
        }

        feedbackRepositorio.delete(feedback);
    }
}