package com.brunofragadev.core.auth.service;

import com.brunofragadev.core.user.repository.UsuarioRepositorio;

import com.brunofragadev.core.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {
        var usuario = usuarioRepositorio.findByUserName(userName);
        if(usuario.isPresent()){
            return usuario.get();
        }
        throw new UserNotFoundException("Nenhum usuario enviado no token foi localizado");
    }
}
