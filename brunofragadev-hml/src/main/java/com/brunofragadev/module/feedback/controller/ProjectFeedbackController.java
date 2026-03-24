package com.brunofragadev.module.feedback.controller;

import com.brunofragadev.module.feedback.service.FeedbackService;
import com.brunofragadev.module.feedback.dto.request.FeedbackCreateRequest;
import com.brunofragadev.module.feedback.dto.response.FeedbackDTO;
import com.brunofragadev.module.user.domain.entity.User;
import com.brunofragadev.infrastructure.default_return_api.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/feedback/projetos")
@SecurityRequirement(name = "bearerAuth")
public class ProjectFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<FeedbackDTO>> criarFeedbackTipoProjeto(
            @RequestBody FeedbackCreateRequest dto,
            @AuthenticationPrincipal User user) {
        FeedbackDTO feedbackCriado = feedbackService.criarFeedback(dto, user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Feedback enviado com sucesso!", feedbackCriado));
    }

    @GetMapping("/listar-todos/{idprojeto}")
    public ResponseEntity<ApiResponse<List<FeedbackDTO>>> listarTodosTipoProjetoFeedbacks(
            @PathVariable Long idprojeto) {
        return ResponseEntity.ok(ApiResponse.success("Recursos encontrados", feedbackService.listarFeedbacksTipoProjeto(idprojeto)));
    }

    @PutMapping("/atualizar/{idFeedback}")
    public ResponseEntity<ApiResponse<FeedbackDTO>> atualizarFeedbackTipoProjeto(
            @PathVariable Long idFeedback,
            @RequestBody FeedbackCreateRequest dto,
            @AuthenticationPrincipal User user) {
        FeedbackDTO feedbackAtualizado = feedbackService.atualizarFeedback(idFeedback, dto, user);
        return ResponseEntity.ok(ApiResponse.success("Feedback do projeto atualizado com sucesso!", feedbackAtualizado));
    }

    @DeleteMapping("/excluir/{idFeedback}")
    public ResponseEntity<ApiResponse<Void>> excluirFeedbackTipoProjeto(
            @PathVariable Long idFeedback,
            @AuthenticationPrincipal User user) {
        feedbackService.excluirFeedback(idFeedback, user);
        return ResponseEntity.ok(ApiResponse.success("Feedback do projeto excluído com sucesso!", null));
    }
}