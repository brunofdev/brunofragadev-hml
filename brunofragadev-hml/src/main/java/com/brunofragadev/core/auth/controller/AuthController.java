package com.brunofragadev.core.auth.controller;



import com.brunofragadev.core.auth.dto.GoogleAuthRequest;
import com.brunofragadev.core.auth.dto.UserLoginResponse;
import com.brunofragadev.core.auth.dto.CredentialsRequest;
import com.brunofragadev.core.auth.service.AuthService;
import com.brunofragadev.shared.util.http.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginCliente(@Valid @RequestBody CredentialsRequest credentialsDTO) {
        UserLoginResponse loginResult = authService.loginCliente(credentialsDTO);
        return ResponseEntity.ok(ApiResponse.success("Usuário autenticado com sucesso", loginResult));
    }
    @PostMapping("/login/google")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginSocial(@Valid @RequestBody GoogleAuthRequest tokenGoogle) {
        UserLoginResponse loginResult = authService.loginGoogle(tokenGoogle);
        return ResponseEntity.ok(ApiResponse.success("Usuário autenticado com sucesso", loginResult));
    }
    @GetMapping("/validar-admin")
    @PreAuthorize("hasRole('ADMIN3')")
    public ResponseEntity<String> validarAcessoAdmin() {
        return ResponseEntity.ok("Acesso Autorizado");
    }

}
