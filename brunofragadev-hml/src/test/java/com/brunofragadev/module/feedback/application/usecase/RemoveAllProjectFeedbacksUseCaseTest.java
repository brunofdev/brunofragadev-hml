package com.brunofragadev.module.feedback.application.usecase;

import com.brunofragadev.module.feedback.domain.repository.FeedbackRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveAllProjectFeedbacksUseCaseTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private RemoveAllProjectFeedbacksUseCase useCase;


    @Test
    @DisplayName("Deve lançar exceção quando o ID do projeto for nulo")
    void execute() {
        Long idNull = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           useCase.execute(idNull);
        });

        assertEquals("Referencia não pode ser vazia para deletar os feedbacks relacionados a um projeto", exception.getMessage());
        verify(feedbackRepository, never()).deleteAllByReferenceId(any());
    }

    @Test
    @DisplayName("Deve chamar o repositório corretamente quando o ID for válido")
    void deveDeletarFeedbacks_QuandoIdForValido() {
        Long idValido = 1L;
        useCase.execute(idValido);
        verify(feedbackRepository, times(1)).deleteAllByReferenceId(idValido);
    }
}