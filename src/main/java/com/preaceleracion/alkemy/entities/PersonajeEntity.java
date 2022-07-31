package com.preaceleracion.alkemy.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete( sql = "UPDATE  characters SET deleted = true WHERE id=?")
@Where( clause = "deleted=false")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String image;

    private String name;

    private Integer age;

    private double weight;

    private String history;

    /*relacion con peliculas*/
    @ManyToMany(mappedBy = "characters", fetch  = FetchType.LAZY)
    private List<PeliculaEntity> movies = new ArrayList<>();


}

