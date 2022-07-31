package com.preaceleracion.alkemy.repositories.specifications;

import com.preaceleracion.alkemy.dto.PersonajeFiltersDTO;
import com.preaceleracion.alkemy.entities.PeliculaEntity;
import com.preaceleracion.alkemy.entities.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            //name
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )

                );
            }
            //age
            if (filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filtersDTO.getAge()));

            }
            //movies
            if (!CollectionUtils.isEmpty(filtersDTO.getIdmovie())) {
                Join<PersonajeEntity, PeliculaEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesIDS = join.get("id");
                predicates.add(moviesIDS.in(filtersDTO.getIdmovie()));

            }

            //image
            if (StringUtils.hasLength(filtersDTO.getImage())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("image")),
                                "%" + filtersDTO.getImage().toLowerCase() + "%"
                        )

                );
            }




            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }
}
