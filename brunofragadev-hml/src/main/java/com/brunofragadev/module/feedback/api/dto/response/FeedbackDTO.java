package com.brunofragadev.module.feedback.api.dto.response;

import com.brunofragadev.module.feedback.domain.entity.FeedbackType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Objeto de retorno contendo os detalhes consolidados de um feedback salvo")
public record FeedbackDTO(

        @Schema(description = "Identificador único do feedback gerado pelo banco de dados", example = "105")
        Long id,

        @Schema(description = "Nome de exibição do autor da avaliação", example = "João da Silva")
        String criadoPor,

        @Schema(description = "Nome de usuário de sistema (username) do autor", example = "joao_silva")
        String userName,

        @Schema(description = "Texto do comentário submetido", example = "O projeto ficou sensacional e a arquitetura está muito limpa!")
        String comentario,

        @Schema(description = "Nota armazenada no banco", example = "5")
        Integer notaAvaliacao,

        @Schema(description = "Data e hora exatas do registro da avaliação", example = "2026-03-27T10:30:00")
        LocalDateTime dataDeCriacao,

        @Schema(description = "URL da foto de perfil do autor para exibição na UI", example = "https://storage.provider.com/fotos/joao.jpg")
        String fotoUsuario,

        @Schema(description = "Classificação estrutural do feedback", example = "PROJECT")
        FeedbackType feedbackType,

        @Schema(description = "ID da entidade avaliada (0 para geral)", example = "15")
        Long referenciaId,

        @Schema(description = "Flag indicando se o autor optou por não exibir seus dados públicos", example = "false")
        Boolean isAnonimo
) {}