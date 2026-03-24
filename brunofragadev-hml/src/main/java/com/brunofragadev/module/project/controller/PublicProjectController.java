package com.brunofragadev.module.project.controller;

import com.brunofragadev.module.project.dto.response.ProjectResponse;
import com.brunofragadev.module.project.service.ProjectService;
import com.brunofragadev.infrastructure.default_return_api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/projetos/publicos")
public class PublicProjectController {

    private final ProjectService service;


    public PublicProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> listarProjetosPublicos() {
        List<ProjectResponse> projetos = service.listarTodos();
        return ResponseEntity.ok(ApiResponse.success("Projetos carregados para o portfólio", projetos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> buscarProjetoPublicoPorId(@PathVariable Long id) {
        ProjectResponse projeto = service.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.success("Detalhes do projeto carregados", projeto));
    }
}