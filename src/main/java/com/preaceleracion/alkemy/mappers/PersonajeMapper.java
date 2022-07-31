package com.preaceleracion.alkemy.mappers;

import com.preaceleracion.alkemy.dto.PersonajeBasicDTO;
import com.preaceleracion.alkemy.dto.PersonajeDTO;
import com.preaceleracion.alkemy.entities.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {


    private PeliculaMapper peliculaMapper;

    public PersonajeMapper(@Autowired @Lazy PeliculaMapper peliculaMapper) {

        this.peliculaMapper = peliculaMapper;
    }

    //DTO a Entidad

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){

        PersonajeEntity personajeEntity = new PersonajeEntity();

        personajeEntity.setAge(dto.getAge());
        personajeEntity.setHistory(dto.getHistory());
        personajeEntity.setImage(dto.getImage());
        personajeEntity.setName(dto.getName());
        personajeEntity.setWeight(dto.getWeight());

        return personajeEntity;
    }

    //Entidad a DTO

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean b) {

        PersonajeDTO dto = new PersonajeDTO();

        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setHistory(entity.getHistory());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setWeight(entity.getWeight());

        if (b){
            dto.setCharacterMovies(peliculaMapper.peliculaEntityList2DtoList(entity.getMovies(), false));
        }

        return dto;
    }


    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> listaEntity, boolean b) {
        List<PersonajeDTO> dtoList = new ArrayList<>();
        for (PersonajeEntity entity : listaEntity) {
            dtoList.add(this.personajeEntity2DTO(entity, b));

        }
        return dtoList;
    }

    public List<PersonajeEntity> perosonajeEntityList(List<PersonajeDTO> movieCharacters) {

        List<PersonajeEntity>personajeEntityLista = new ArrayList<>();

        for(PersonajeDTO aux:  movieCharacters){
            personajeEntityLista.add(this.personajeDTO2Entity(aux));
        }

        return personajeEntityLista;



    }

    //trabajamos con la lista de personajes basic

    public List<PersonajeBasicDTO> personajeEntityList2DTOListBasic(List<PersonajeEntity> entityList) {

        List<PersonajeBasicDTO>dtoLista = new ArrayList<>();
        for(PersonajeEntity aux : entityList){
            dtoLista.add(this.personajeEntity2DtoBasic(aux));
        }
        return dtoLista;

    }

    private PersonajeBasicDTO personajeEntity2DtoBasic(PersonajeEntity aux) {

        PersonajeBasicDTO personajeBasicDTO = new PersonajeBasicDTO();

        personajeBasicDTO.setImage(aux.getImage());
        personajeBasicDTO.setName(aux.getName());

        return  personajeBasicDTO;
    }



}
