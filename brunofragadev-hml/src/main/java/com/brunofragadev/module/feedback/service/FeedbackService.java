package com.brunofragadev.module.feedback.service;

import com.brunofragadev.module.user.domain.entity.User;
import com.brunofragadev.infrastructure.email.EmailService;
import com.brunofragadev.module.feedback.repository.FeedbackRepository;
import com.brunofragadev.module.feedback.dto.request.FeedbackCreateRequest;
import com.brunofragadev.module.feedback.dto.response.FeedbackDTO;
import com.brunofragadev.module.feedback.entity.Feedback;
import com.brunofragadev.module.feedback.mapper.FeedbackMapper;
import com.brunofragadev.shared.service.ReferenceResolver;
import com.brunofragadev.module.user.domain.entity.Role;
import com.brunofragadev.module.user.domain.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {


    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final EmailService emailService;
    private final ReferenceResolver referenceResolver;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, EmailService emailService, ReferenceResolver referenceResolver){
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.emailService = emailService;
        this.referenceResolver = referenceResolver;
    }

    public FeedbackDTO criarFeedback(FeedbackCreateRequest dto, User user){
        Feedback feedback = feedbackMapper.mapearFeedbackCriacao(dto, user);
        feedbackRepository.save(feedback);
        FeedbackDTO feedbackDTO = feedbackMapper.mapearFeedbackDTO(feedback);
        String localFeedback = referenceResolver.resolverNome(feedbackDTO.feedbackType(), feedbackDTO.referenciaId());
        emailService.sendNewFeedbackAlertToAdmin(feedbackDTO, localFeedback);
        return feedbackDTO;
    }

    public List<FeedbackDTO> listarFeedbacksTipoGeral (){
        return feedbackRepository.buscarTodosTipoGeralComFotos();
    }

    public List<FeedbackDTO> listarFeedbacksTipoProjeto (Long idProjeto) {
        return feedbackRepository.buscarTodosTipoProjetoComFotos(idProjeto);
    }

    public FeedbackDTO atualizarFeedback(Long idFeedback, FeedbackCreateRequest dto, User user) {
        Feedback feedback = feedbackRepository.findById(idFeedback)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado!"));
        if (!feedback.getUser().getId().equals(user.getId())) {
            throw new InvalidCredentialsException("Acesso negado: Você não tem permissão para editar este feedback.");
        }
        feedback.setDescricao(dto.descricao());
        feedback.setAvaliacao(dto.avaliacao());
        feedbackRepository.save(feedback);
        return feedbackMapper.mapearFeedbackDTO(feedback);
    }
    public void excluirFeedback(Long idFeedback, User user) {
        Feedback feedback = feedbackRepository.findById(idFeedback)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado!"));

        boolean isDonoDoComentario = feedback.getUser().getId().equals(user.getId());
        boolean isAdmin = user.getRole() == Role.ADMIN3; // Ajuste o getRole() se o seu método chamar de forma diferente

        if (!isDonoDoComentario && !isAdmin) {
            throw new RuntimeException("Acesso negado: Você não tem permissão para excluir este feedback.");
        }

        feedbackRepository.delete(feedback);
    }
}