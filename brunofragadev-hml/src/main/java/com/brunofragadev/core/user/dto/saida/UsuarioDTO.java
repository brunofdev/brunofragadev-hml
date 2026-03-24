package com.brunofragadev.core.user.dto.saida;

import com.brunofragadev.core.user.entity.Role;

public record UsuarioDTO(
        Long id,
        String nome,
        String userName,
        String nomePublico,
        Boolean isAnonimo,
        String email,
        Role role,
        Boolean contaAtiva,
        String cidade,
        String gitHub,
        String profissao,
        String bio,
        String fotoPerfil,
        String linkedin,
        String pais,
        String telefone

){
}
