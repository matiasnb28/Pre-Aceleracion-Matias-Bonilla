package com.preaceleracion.alkemy.repositories;

import com.preaceleracion.alkemy.entities.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {


    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);

}

