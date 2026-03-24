package com.brunofragadev.module.project.controller;

import com.brunofragadev.module.project.dto.request.ProjectRequest;
import com.brunofragadev.module.project.dto.response.ProjectResponse;
import com.brunofragadev.module.project.service.ProjectService;
import com.brunofragadev.infrastructure.default_return_api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/paineladm/projetos")
@Validated
public class PrivateProjectController {

    private final ProjectService service;

    public PrivateProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> criar(@Valid @RequestBody ProjectRequest dto) {
        ProjectResponse projetoCriado = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Projeto publicado com sucesso", projetoCriado));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> listarTodos() {
        List<ProjectResponse> projetos = service.listarTodos();
        return ResponseEntity.ok(ApiResponse.success("Projetos listados com sucesso", projetos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> buscarPorId(@PathVariable Long id) {
        ProjectResponse projeto = service.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.success("Projeto encontrado com sucesso", projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest dto) {
        ProjectResponse projetoAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Projeto atualizado com sucesso", projetoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}