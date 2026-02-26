package com.brunofragadev.projetos.controller;

import com.brunofragadev.projetos.dto.ProjetoResponseDTO;
import com.brunofragadev.projetos.service.ProjetoService;
import com.brunofragadev.utils.retorno_padrao_api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/projetos/publicos")
public class ProjetoPublicoController {

    private final ProjetoService service;


    public ProjetoPublicoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjetoResponseDTO>>> listarProjetosPublicos() {
        List<ProjetoResponseDTO> projetos = service.listarTodos();
        return ResponseEntity.ok(ApiResponse.success("Projetos carregados para o portfólio", projetos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjetoResponseDTO>> buscarProjetoPublicoPorId(@PathVariable Long id) {
        ProjetoResponseDTO projeto = service.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.success("Detalhes do projeto carregados", projeto));
    }
}