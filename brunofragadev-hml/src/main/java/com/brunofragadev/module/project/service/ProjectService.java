package com.brunofragadev.module.project.service;

import com.brunofragadev.module.project.dto.request.ProjectRequest;
import com.brunofragadev.module.project.dto.response.ProjectResponse;
import com.brunofragadev.module.project.entity.Project;
import com.brunofragadev.module.project.mapper.ProjectMapper;
import com.brunofragadev.module.project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public ProjectService(ProjectRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ProjectResponse salvar(ProjectRequest dto) {
        Project project = mapper.toEntity(dto, new Project());
        Project projectSalvo = repository.save(project);
        return mapper.toDTO(projectSalvo);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProjectResponse buscarPorId(Long id) {
        Project project = buscarEntidadePorId(id);
        return mapper.toDTO(project);
    }

    @Transactional
    public ProjectResponse atualizar(Long id, ProjectRequest dto) {
        Project projectExistente = buscarEntidadePorId(id);
        mapper.toEntity(dto, projectExistente);
        Project projectAtualizado = repository.save(projectExistente);
        return mapper.toDTO(projectAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Projeto não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    private Project buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com ID: " + id));
    }
}