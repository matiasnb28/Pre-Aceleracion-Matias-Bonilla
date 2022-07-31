package com.preaceleracion.alkemy.servicies.Impl;

import com.preaceleracion.alkemy.dto.GeneroDTO;
import com.preaceleracion.alkemy.entities.GeneroEntity;
import com.preaceleracion.alkemy.mappers.GeneroMapper;
import com.preaceleracion.alkemy.repositories.GeneroRepository;
import com.preaceleracion.alkemy.servicies.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    private GeneroMapper generoMapper;

    private GeneroRepository generoRepository;

    public GeneroServiceImpl(@Autowired @Lazy GeneroMapper generoMapper, GeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }


    //Post
    @Override
    public GeneroDTO save(GeneroDTO gender) {

        GeneroEntity generoEntity = generoMapper.generoDTO2Entity(gender);
        GeneroEntity savedGender = generoRepository.save(generoEntity);
        GeneroDTO resultado = generoMapper.generoEntity2DTO(savedGender);

        return resultado;
    }
}
