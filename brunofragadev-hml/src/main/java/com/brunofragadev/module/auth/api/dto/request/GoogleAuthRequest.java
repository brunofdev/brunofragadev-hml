package com.brunofragadev.module.auth.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload para autenticação via provedor externo (Google)")
public record GoogleAuthRequest(
        @Schema(description = "Token de identificação JWT fornecido pelo Google (ID Token)", example = "eyJhbGciOiJSUzI1NiIsImtpZ...")
        String googleToken
) {}