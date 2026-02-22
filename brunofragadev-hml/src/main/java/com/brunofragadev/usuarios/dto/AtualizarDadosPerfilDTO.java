package com.brunofragadev.usuarios.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record AtualizarDadosPerfilDTO(

        @Size(max = 100, message = "A profissão deve ter no máximo 100 caracteres.")
        String profissao,

        // Aceita números com ou sem o +, ex: +5548999999999 ou 48999999999
        @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10,15}$", message = "Formato de telefone inválido. Use apenas números, com ou sem DDI.")
        String telefone,

        @Size(max = 50, message = "O país deve ter no máximo 50 caracteres.")
        String pais,

        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
        String cidade,

        // Garante que o link seja do GitHub
        @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/.*", message = "Deve ser uma URL válida do GitHub.")
        String gitHub,

        // Garante que o link seja do LinkedIn
        @Pattern(regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*", message = "Deve ser uma URL válida do LinkedIn.")
        String linkedin,

        // A sua regra de ouro para a Bio
        @Size(max = 1000, message = "A bio deve ter no máximo 1000 caracteres.")
        String bio,

        // Se a foto for trafegada como Link, validamos se é uma URL real
        @URL(message = "A foto de perfil deve ser um link (URL) válido.")
        String fotoPerfil
) {
}