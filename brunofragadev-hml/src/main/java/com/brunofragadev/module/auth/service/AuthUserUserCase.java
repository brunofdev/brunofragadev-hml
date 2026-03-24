package com.brunofragadev.module.auth.service;

import com.brunofragadev.infrastructure.config.JwtProvider;
import com.brunofragadev.module.auth.dto.CredentialsRequest;
import com.brunofragadev.module.auth.dto.UserLoginResponse;
import com.brunofragadev.module.user.api.dto.response.UserDTO;
import com.brunofragadev.module.user.application.usecase.ValidateCredentialsUseCase;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUserCase {
    private final JwtProvider jwtProvider;
    private final ValidateCredentialsUseCase userAuth;

    public AuthUserUserCase(JwtProvider jwtProvider, ValidateCredentialsUseCase userAuth){
        this.jwtProvider = jwtProvider;
        this.userAuth = userAuth;
    }

    public UserLoginResponse loginCliente(CredentialsRequest credentials) {
        UserDTO usuario = userAuth.execute(credentials.userName(), credentials.senha());
        String token = jwtProvider.generateToken(usuario.userName(), usuario.role());
        return new UserLoginResponse(token, usuario);
    }
}
