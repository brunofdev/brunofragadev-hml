package com.brunofragadev.shared.service;

import com.brunofragadev.core.feedback.entity.TipoFeedback;
import com.brunofragadev.core.project.entity.Projeto;
import com.brunofragadev.core.project.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReferenceResolver {

    private final ProjetoRepository projetoRepository;

    public ReferenceResolver(ProjetoRepository projetoRepository){
        this.projetoRepository = projetoRepository;
    }

    public String resolverNome(TipoFeedback tipo, Long referenciaId) {
        if (tipo == TipoFeedback.GERAL || referenciaId == null) {
            return "Página geral";
        }
        if (tipo == TipoFeedback.PROJETO) {
            return "Postado no projeto: " + projetoRepository.findById(referenciaId)
                    .map(Projeto::getTitle)
                    .orElse("Projeto não encontrado");
        }
        return "Referência desconhecida";
    }

}
