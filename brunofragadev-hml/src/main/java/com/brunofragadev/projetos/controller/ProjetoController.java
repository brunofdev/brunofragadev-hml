package com.brunofragadev.projetos.controller;

import com.brunofragadev.projetos.dto.ProjetoRequestDTO;
import com.brunofragadev.projetos.dto.ProjetoResponseDTO;
import com.brunofragadev.projetos.service.ProjetoService;
import com.brunofragadev.utils.retorno_padrao_api.ApiResponse;
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
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjetoResponseDTO>> criar(@Valid @RequestBody ProjetoRequestDTO dto) {
        ProjetoResponseDTO projetoCriado = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Projeto publicado com sucesso", projetoCriado));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjetoResponseDTO>>> listarTodos() {
        List<ProjetoResponseDTO> projetos = service.listarTodos();
        return ResponseEntity.ok(ApiResponse.success("Projetos listados com sucesso", projetos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjetoResponseDTO>> buscarPorId(@PathVariable Long id) {
        ProjetoResponseDTO projeto = service.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.success("Projeto encontrado com sucesso", projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjetoResponseDTO>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProjetoRequestDTO dto) {
        ProjetoResponseDTO projetoAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Projeto atualizado com sucesso", projetoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}