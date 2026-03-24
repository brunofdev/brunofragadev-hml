package com.brunofragadev.module.auth.service;


import com.brunofragadev.infrastructure.config.JwtProvider;
import com.brunofragadev.module.auth.dto.GoogleAuthRequest;
import com.brunofragadev.module.auth.dto.UserLoginResponse;
import com.brunofragadev.module.user.api.dto.response.UserDTO;
import com.brunofragadev.module.user.application.usecase.ProcessGoogleLoginUseCase;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class ApiGoogleAuthUserCase {

    @Value("${google.client.id}")
    private String googleClientId;
    private final ProcessGoogleLoginUseCase authGoogle;
    private final JwtProvider jwtProvider;

    public ApiGoogleAuthUserCase(ProcessGoogleLoginUseCase authGoogle, JwtProvider jwtProvider){
        this.authGoogle = authGoogle;
        this.jwtProvider = jwtProvider;
    }

    public UserLoginResponse loginGoogle(GoogleAuthRequest tokenGoogleCru) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            String token = tokenGoogleCru.googleToken();
            String tokenLimpo = token.replace("\"", "");

            GoogleIdToken idToken = verifier.verify(tokenLimpo);

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String nome = (String) payload.get("name");
                String fotoUrl = (String) payload.get("picture");

                UserDTO usuarioEntidade = authGoogle.execute(email, nome, fotoUrl);

                String seuToken = jwtProvider.generateToken(usuarioEntidade.userName(), usuarioEntidade.role());

                return new UserLoginResponse(seuToken, usuarioEntidade);

            } else {
                throw new RuntimeException("Acesso Negado: Token do Google adulterado ou expirado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha na comunicação com os servidores do Google: " + e.getMessage());
        }
    }
}
