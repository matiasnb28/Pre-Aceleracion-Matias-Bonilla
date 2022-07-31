package com.preaceleracion.alkemy.servicies.Impl;

import com.preaceleracion.alkemy.dto.PersonajeBasicDTO;
import com.preaceleracion.alkemy.dto.PersonajeDTO;
import com.preaceleracion.alkemy.dto.PersonajeFiltersDTO;
import com.preaceleracion.alkemy.entities.PersonajeEntity;
import com.preaceleracion.alkemy.exception.ParamNotFound;
import com.preaceleracion.alkemy.mappers.PersonajeMapper;
import com.preaceleracion.alkemy.repositories.PersonajeRepository;
import com.preaceleracion.alkemy.repositories.specifications.PersonajeSpecification;
import com.preaceleracion.alkemy.servicies.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {


    private PersonajeMapper personajeMapper;

    private PersonajeRepository personajeRepository;

    private PersonajeSpecification personajeSpecification;

    public PersonajeServiceImpl(@Autowired @Lazy PersonajeMapper personajeMapper, @Autowired @Lazy PersonajeRepository personajeRepository, @Autowired @Lazy PersonajeSpecification personajeSpecification) {
        this.personajeMapper = personajeMapper;
        this.personajeRepository = personajeRepository;
        this.personajeSpecification = personajeSpecification;
    }

    //post
    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(personajeSaved, false);

        return resultado;
    }

    //Delete
    public void delete(Long id){
        personajeRepository.deleteById(id);
    }
    //Put
    @Override
    public PersonajeDTO putCharacter(Long id, PersonajeDTO edit) {

        PersonajeEntity savedCharacter = this.characterEdit(id);
        savedCharacter.setWeight(edit.getWeight());
        savedCharacter.setName(edit.getName());
        savedCharacter.setImage(edit.getImage());
        savedCharacter.setHistory(edit.getHistory());
        savedCharacter.setAge(edit.getAge());

        PersonajeEntity putCharacter = personajeRepository.save(savedCharacter);
        PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(putCharacter, false);
        return savedDTO;
    }


    private PersonajeEntity characterEdit(Long id) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);
        if (!personajeEntity.isPresent()){
            throw new ParamNotFound("Id no valido");
        }
        return personajeEntity.get();
    }

    @Override
    public List<PersonajeBasicDTO> getAllBasics() {

        List<PersonajeEntity> entityList = personajeRepository.findAll();
        List<PersonajeBasicDTO> resultado = personajeMapper.personajeEntityList2DTOListBasic(entityList);

        return resultado;
    }

    //Filtros
    public List<PersonajeDTO> getByFilters(String name, String image, Integer age, Set<Long> Idmovie){

        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, image, age, Idmovie);

        List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntityList2DTOList(entities, true);
        return dtos;
    }




}
