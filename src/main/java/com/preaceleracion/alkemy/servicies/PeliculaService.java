package com.preaceleracion.alkemy.servicies;

import com.preaceleracion.alkemy.dto.PeliculaBasicDTO;
import com.preaceleracion.alkemy.dto.PeliculaDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO movie);

    void delete(Long id);

    PeliculaDTO putMovie(Long id, PeliculaDTO edit);

    List<PeliculaDTO> getByFilters(String title, String image, String dateCreation, Set<Long> gender, String order);

    List<PeliculaBasicDTO> getAllBasics();
}
