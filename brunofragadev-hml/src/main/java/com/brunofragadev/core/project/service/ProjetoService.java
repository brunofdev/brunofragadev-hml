package com.brunofragadev.core.project.service;

import com.brunofragadev.core.project.dto.ProjetoRequestDTO;
import com.brunofragadev.core.project.dto.ProjetoResponseDTO;
import com.brunofragadev.core.project.entity.Projeto;
import com.brunofragadev.core.project.mapper.ProjetoMapper;
import com.brunofragadev.core.project.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;
    private final ProjetoMapper mapper;

    public ProjetoService(ProjetoRepository repository, ProjetoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ProjetoResponseDTO salvar(ProjetoRequestDTO dto) {
        Projeto projeto = mapper.toEntity(dto, new Projeto());
        Projeto projetoSalvo = repository.save(projeto);
        return mapper.toDTO(projetoSalvo);
    }

    @Transactional(readOnly = true)
    public List<ProjetoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProjetoResponseDTO buscarPorId(Long id) {
        Projeto projeto = buscarEntidadePorId(id);
        return mapper.toDTO(projeto);
    }

    @Transactional
    public ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO dto) {
        Projeto projetoExistente = buscarEntidadePorId(id);
        mapper.toEntity(dto, projetoExistente);
        Projeto projetoAtualizado = repository.save(projetoExistente);
        return mapper.toDTO(projetoAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Projeto não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    private Projeto buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com ID: " + id));
    }
}