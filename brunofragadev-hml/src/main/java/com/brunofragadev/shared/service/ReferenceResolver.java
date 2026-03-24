package com.brunofragadev.shared.service;

import com.brunofragadev.module.feedback.entity.FeedbackType;
import com.brunofragadev.module.project.entity.Project;
import com.brunofragadev.module.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ReferenceResolver {

    private final ProjectRepository projectRepository;

    public ReferenceResolver(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public String resolverNome(FeedbackType tipo, Long referenciaId) {
        if (tipo == FeedbackType.GERAL || referenciaId == null) {
            return "Página geral";
        }
        if (tipo == FeedbackType.PROJETO) {
            return "Postado no projeto: " + projectRepository.findById(referenciaId)
                    .map(Project::getTitle)
                    .orElse("Projeto não encontrado");
        }
        return "Referência desconhecida";
    }

}
