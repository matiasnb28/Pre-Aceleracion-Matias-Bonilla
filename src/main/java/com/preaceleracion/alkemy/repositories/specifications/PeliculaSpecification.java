package com.preaceleracion.alkemy.repositories.specifications;

import com.preaceleracion.alkemy.dto.PeliculaFiltersDTO;
import com.preaceleracion.alkemy.entities.GeneroEntity;
import com.preaceleracion.alkemy.entities.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //title
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
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

            //date
            if (StringUtils.hasLength(filtersDTO.getDateCreation())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getDateCreation(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.get("dateCreation"), date)
                );
            }

            //gender
            if (!CollectionUtils.isEmpty(filtersDTO.getGender())) {
                Join<PeliculaEntity, GeneroEntity> join = root.join("gender", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(filtersDTO.getGender()));
            }

            query.distinct(true);

            //order
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}

