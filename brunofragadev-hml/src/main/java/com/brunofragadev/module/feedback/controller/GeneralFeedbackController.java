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
@RequestMapping("/feedback/geral") // 👈 Rota base focada apenas no Geral
@SecurityRequirement(name = "bearerAuth")
public class GeneralFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<FeedbackDTO>> criarFeedbackTipoGeral(
            @RequestBody FeedbackCreateRequest dto,
            @AuthenticationPrincipal User user) {
        FeedbackDTO feedbackCriado = feedbackService.criarFeedback(dto, user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Feedback enviado com sucesso!", feedbackCriado));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<ApiResponse<List<FeedbackDTO>>> listarTodosTipoGeralFeedbacks() {
        return ResponseEntity.ok(ApiResponse.success("Recursos encontrados", feedbackService.listarFeedbacksTipoGeral()));
    }

    @PutMapping("/atualizar/{idFeedback}")
    public ResponseEntity<ApiResponse<FeedbackDTO>> atualizarFeedbackTipoGeral(
            @PathVariable Long idFeedback,
            @RequestBody FeedbackCreateRequest dto,
            @AuthenticationPrincipal User user) {
        FeedbackDTO feedbackAtualizado = feedbackService.atualizarFeedback(idFeedback, dto, user);
        return ResponseEntity.ok(ApiResponse.success("Feedback atualizado com sucesso!", feedbackAtualizado));
    }

    @DeleteMapping("/excluir/{idFeedback}")
    public ResponseEntity<ApiResponse<Void>> excluirFeedbackTipoGeral(
            @PathVariable Long idFeedback,
            @AuthenticationPrincipal User user) {
        feedbackService.excluirFeedback(idFeedback, user);
        return ResponseEntity.ok(ApiResponse.success("Feedback excluído com sucesso!", null));
    }
}