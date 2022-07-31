package com.preaceleracion.alkemy.servicies;

import com.preaceleracion.alkemy.dto.PersonajeBasicDTO;
import com.preaceleracion.alkemy.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    void delete(Long id);

    PersonajeDTO putCharacter(Long id, PersonajeDTO edit);


    List<PersonajeDTO> getByFilters(String name, String image, Integer age, Set<Long> idMovie);

    List<PersonajeBasicDTO> getAllBasics();
}

