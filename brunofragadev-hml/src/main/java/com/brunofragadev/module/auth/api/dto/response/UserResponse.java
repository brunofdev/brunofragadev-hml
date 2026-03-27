package com.brunofragadev.module.auth.api.dto.response;

import com.brunofragadev.module.user.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação enxuta de um usuário do sistema")
public record UserResponse(
        @Schema(description = "Identificador único do usuário", example = "bruno_fraga")
        String username,

        @Schema(description = "Nível de permissão (Role) atribuído ao usuário", example = "ADMIN3")
        Role role
) {}