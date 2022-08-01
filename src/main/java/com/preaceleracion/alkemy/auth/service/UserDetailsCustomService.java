package com.preaceleracion.alkemy.auth.service;

import com.preaceleracion.alkemy.auth.dto.UserDTO;
import com.preaceleracion.alkemy.auth.entity.UserEntity;
import com.preaceleracion.alkemy.auth.repository.UserRepository;
import com.preaceleracion.alkemy.servicies.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {


    private UserRepository userRepository;

    private EmailService emailService;

    public UserDetailsCustomService(@Autowired @Lazy UserRepository userRepository, @Autowired @Lazy EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override //sobreescribir cómo voy a ir a buscar el usuario cuando me llega
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException("Usuario o contraseña no encontrada");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());


    }

    public boolean save(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);

        if (userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }

        return userEntity != null;
    }
}
