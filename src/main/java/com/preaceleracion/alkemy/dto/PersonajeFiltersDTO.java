package com.preaceleracion.alkemy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {

    private String name;
    private String image;
    private Integer age;
    private Set<Long> Idmovie;

    public PersonajeFiltersDTO(String name, String image, Integer age, Set<Long> idmovie) {
        this.name = name;
        this.image= image;
        this.age = age;
        Idmovie = idmovie;
    }
}
