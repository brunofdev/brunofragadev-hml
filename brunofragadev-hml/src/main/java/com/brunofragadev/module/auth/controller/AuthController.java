package com.brunofragadev.module.auth.controller;



import com.brunofragadev.module.auth.dto.GoogleAuthRequest;
import com.brunofragadev.module.auth.dto.UserLoginResponse;
import com.brunofragadev.module.auth.dto.CredentialsRequest;
import com.brunofragadev.infrastructure.default_return_api.ApiResponse;
import com.brunofragadev.module.auth.service.ApiGoogleAuthUserCase;
import com.brunofragadev.module.auth.service.AuthUserUserCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserUserCase authUserUserCase;
    private final ApiGoogleAuthUserCase authGoogle;


    public AuthController(AuthUserUserCase authUserUserCase, ApiGoogleAuthUserCase authGoogle){
        this.authUserUserCase = authUserUserCase;
        this.authGoogle = authGoogle;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginCliente(@Valid @RequestBody CredentialsRequest credentialsDTO) {
        UserLoginResponse loginResult = authUserUserCase.loginCliente(credentialsDTO);
        return ResponseEntity.ok(ApiResponse.success("Usuário autenticado com sucesso", loginResult));
    }
    @PostMapping("/login/google")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginSocial(@Valid @RequestBody GoogleAuthRequest tokenGoogle) {
        UserLoginResponse loginResult = authGoogle.loginGoogle(tokenGoogle);
        return ResponseEntity.ok(ApiResponse.success("Usuário autenticado com sucesso", loginResult));
    }
    @GetMapping("/validar-admin")
    @PreAuthorize("hasRole('ADMIN3')")
    public ResponseEntity<String> validarAcessoAdmin() {
        return ResponseEntity.ok("Acesso Autorizado");
    }

}
