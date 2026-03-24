package com.brunofragadev.core.user.validator;

import com.brunofragadev.core.user.dto.entrada.CadastrarUsuarioDTO;
import com.brunofragadev.core.user.exception.EmailAlreadyExistsException;
import com.brunofragadev.core.user.exception.UsernameAlreadyExistsException;
import com.brunofragadev.core.user.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public void validarNovoUsuario(CadastrarUsuarioDTO dto, Boolean isUpdate) {
        checaEmailExiste(dto.email());
        checaUserNameExiste(dto.userName());
    }
    private void checaEmailExiste(String email){
        if(usuarioRepositorio.existsByEmail(email)){
            throw new EmailAlreadyExistsException("Email ja cadastrado no sistema");
        }
    }

    private void checaUserNameExiste(String userName){
        if(usuarioRepositorio.existsByUserName(userName)){
            throw new UsernameAlreadyExistsException(
                    "Já existe um userName com este Nome cadastrado no sistema");
        }
    }


}
