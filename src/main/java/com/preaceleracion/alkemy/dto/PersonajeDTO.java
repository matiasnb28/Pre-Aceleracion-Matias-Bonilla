package com.preaceleracion.alkemy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private double weight;
    private String history;
    private List<PeliculaDTO> characterMovies;



}