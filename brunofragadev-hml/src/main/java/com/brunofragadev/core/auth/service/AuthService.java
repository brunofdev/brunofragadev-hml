package com.brunofragadev.core.auth.service;

import com.brunofragadev.core.auth.dto.GoogleAuthRequest;
import com.brunofragadev.core.auth.dto.CredentialsRequest;
import com.brunofragadev.core.auth.dto.UserLoginResponse;
import com.brunofragadev.infrastructure.configs.JwtProvider;
import com.brunofragadev.core.user.dto.saida.UsuarioDTO;
import com.brunofragadev.core.user.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final UsuarioServico usuarioServico;

    @Value("${google.client.id}")
    private String googleClientId;


    public AuthService(JwtProvider jwtProvider, UsuarioServico usuarioServico){
        this.jwtProvider = jwtProvider;
        this.usuarioServico = usuarioServico;
    }

    public UserLoginResponse loginCliente(CredentialsRequest credentials) {
        UsuarioDTO usuario = usuarioServico.validarCredenciais(credentials.userName(), credentials.senha());
        String token = jwtProvider.generateToken(usuario.userName(), usuario.role());
        return new UserLoginResponse(token, usuario);
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

                UsuarioDTO usuarioEntidade = usuarioServico.processarLoginGoogle(email, nome, fotoUrl);

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