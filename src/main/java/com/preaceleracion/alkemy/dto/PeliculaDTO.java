package com.preaceleracion.alkemy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String image;

    private String title;

    private String dateCreation;

    private int qualification;

    private Long genderId;

    private List<PersonajeDTO> movieCharacters;


}