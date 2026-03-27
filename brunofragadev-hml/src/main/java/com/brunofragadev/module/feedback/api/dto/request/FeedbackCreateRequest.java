package com.brunofragadev.module.feedback.api.dto.request;

import com.brunofragadev.module.feedback.domain.entity.FeedbackType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Formulário de submissão para criar ou atualizar um feedback no portfólio")
public record FeedbackCreateRequest(

        @Schema(description = "Corpo do texto da avaliação", example = "O projeto ficou sensacional e a arquitetura está muito limpa!")
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres.")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{P}áàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+$", message = "A descrição contém caracteres inválidos")
        String descricao,

        @Schema(description = "Nota de qualidade atribuída (1 a 5)", example = "5")
        @NotNull(message = "A avaliação é obrigatória")
        @Min(value = 1, message = "A avaliação deve ser no mínimo 1")
        @Max(value = 5, message = "A avaliação deve ser no máximo 5")
        Integer avaliacao,

        @Schema(description = "Contexto do feedback (Ex: PROJECT, GENERAL)", example = "PROJECT")
        @NotNull(message = "O tipo de feedback não pode ser nulo")
        FeedbackType tipoFeedback,

        @Schema(description = "ID do alvo da avaliação (Use 0 para avaliações gerais do portfólio ou o ID específico de um projeto)", example = "15")
        @PositiveOrZero(message = "O ID de referência deve ser 0 (zero para página geral) ou positivo para projetos")
        Long referenciaId
) {}