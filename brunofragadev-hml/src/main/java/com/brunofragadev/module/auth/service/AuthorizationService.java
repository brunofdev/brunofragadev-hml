package com.brunofragadev.module.auth.service;

import com.brunofragadev.module.user.domain.repository.UserRepository;

import com.brunofragadev.module.user.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {
        var usuario = userRepository.findByUserName(userName);
        if(usuario.isPresent()){
            return usuario.get();
        }
        throw new UserNotFoundException("Nenhum usuario enviado no token foi localizado");
    }
}
