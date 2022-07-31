package com.preaceleracion.alkemy.mappers;

import com.preaceleracion.alkemy.dto.GeneroDTO;
import com.preaceleracion.alkemy.entities.GeneroEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    // DTO a Entidad
    public GeneroEntity generoDTO2Entity(GeneroDTO gender) {

        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImage(gender.getImage());
        generoEntity.setName(gender.getName());

        return generoEntity;
    }

    // Entidad a DTO
    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {

        GeneroDTO generoDTO = new GeneroDTO();

        generoDTO.setImage(entity.getImage());
        generoDTO.setName(entity.getName());
        generoDTO.setId(entity.getId());

        return generoDTO;
    }
}

