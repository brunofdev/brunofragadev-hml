package com.brunofragadev.core.user.controller;

import com.brunofragadev.core.auth.dto.UserLoginResponse;
import com.brunofragadev.infrastructure.configs.JwtProvider;
import com.brunofragadev.core.user.dto.entrada.AtualizarDadosPerfilDTO;
import com.brunofragadev.core.user.dto.entrada.CadastrarUsuarioDTO;
import com.brunofragadev.core.user.dto.entrada.UsuarioAlteracaoSenhaDTO;
import com.brunofragadev.core.user.dto.entrada.ValidarUsuarioDTO;
import com.brunofragadev.core.user.dto.saida.UsuarioDTO;
import com.brunofragadev.core.user.dto.saida.UsuarioRecuperacaoSenhaDTO;
import com.brunofragadev.core.user.entity.Usuario;
import com.brunofragadev.core.user.service.UsuarioServico;
import com.brunofragadev.shared.util.http.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    private final UsuarioServico usuarioServico;
    private final JwtProvider jwtProvider;


    public UsuarioController (UsuarioServico usuarioServico, JwtProvider jwtProvider){
        this.usuarioServico = usuarioServico;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<UsuarioDTO>> cadastrarUsuario(@Valid @RequestBody CadastrarUsuarioDTO dto){
        UsuarioDTO usuarioCriadoDTO = usuarioServico.cadastrarUsuario(dto);
        return ResponseEntity.ok(ApiResponse.success("Recurso criado" , usuarioCriadoDTO));
    }
    @GetMapping("/obter-todos")
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> listarUsuarios(){
        return ResponseEntity.ok(ApiResponse.success("Recurso disponivel", usuarioServico.listarUsuarios()));
    }
    @PostMapping("/ativar-conta")
    public ResponseEntity<ApiResponse<UserLoginResponse>> ativarConta(@RequestBody @Valid ValidarUsuarioDTO dto) {
        UsuarioDTO usuarioAtivado = usuarioServico.autenticarContaAtiva(dto);
        String token = jwtProvider.generateToken(usuarioAtivado.userName(), usuarioAtivado.role());
        return ResponseEntity.ok(ApiResponse.success("Recurso disponivel", new UserLoginResponse(token, usuarioAtivado)));
    }
    @GetMapping("/meus-dados")
    public ResponseEntity<ApiResponse<UsuarioDTO>> getUserDetailsByUsername(@AuthenticationPrincipal Usuario usuarioAutenticado){
        UsuarioDTO UserDTO = usuarioServico.fornecerDadosUsuarioAutenticado(usuarioAutenticado.getUsername());
        return ResponseEntity.ok().body(ApiResponse.success("Dados do usario autenticado", UserDTO));
    }

    @PostMapping("/reenviar-codigo")
    public ResponseEntity<Void> reenviarCodigo(@RequestBody String userName) {
        usuarioServico.gerarNovoCodigo(userName);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/senha/recuperacao")
    public ResponseEntity<ApiResponse<UsuarioRecuperacaoSenhaDTO>> recuperarSenha(@RequestBody String userNameOuEmail) {
        UsuarioRecuperacaoSenhaDTO emailDTO = usuarioServico.enviarCodigoRecuperacaoSenhaPorEmail(userNameOuEmail);
        return ResponseEntity.ok(ApiResponse.success("Email enviado com sucesso", emailDTO));
    }
    @PostMapping("/senha/recuperacao/validar-codigo")
    public ResponseEntity<Void> validarCodigoAlteracaoSenha(@RequestBody ValidarUsuarioDTO dto){
        usuarioServico.validarCodigoRecuperacaoSenha(dto);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/senha/recuperacao/alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid UsuarioAlteracaoSenhaDTO dto) {
        usuarioServico.alterarSenhaUsuario(dto);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/meus-dados/atualizar")
    public ResponseEntity<ApiResponse<UsuarioDTO>> atualizarPerfil(
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            @Valid @RequestBody AtualizarDadosPerfilDTO dto) {
        UsuarioDTO perfilAtualizado = usuarioServico.atualizarDadosPerfil(usuarioAutenticado.getUsername(), dto);
        return ResponseEntity.ok(ApiResponse.success("Perfil atualizado com sucesso", perfilAtualizado));
    }
}

