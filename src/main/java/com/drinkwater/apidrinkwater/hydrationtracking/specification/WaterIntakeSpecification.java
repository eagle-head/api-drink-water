package com.drinkwater.apidrinkwater.hydrationtracking.specification;

import com.drinkwater.apidrinkwater.hydrationtracking.dto.WaterIntakeFilterDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.usermanagement.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class WaterIntakeSpecification {

    private WaterIntakeSpecification() {
    }

    public static Specification<WaterIntake> getSpecifications(User user, WaterIntakeFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("user"), user));

            if (filterDTO.getStartDateTimeUTC() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateTimeUTC"), filterDTO.getStartDateTimeUTC()));
            }

            if (filterDTO.getEndDateTimeUTC() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateTimeUTC"), filterDTO.getEndDateTimeUTC()));
            }

            if (filterDTO.getMinVolume() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("volume"), filterDTO.getMinVolume()));
            }

            if (filterDTO.getMaxVolume() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("volume"), filterDTO.getMaxVolume()));
            }

            if (filterDTO.getVolumeUnit() != null) {
                predicates.add(criteriaBuilder.equal(root.get("volumeUnit"), filterDTO.getVolumeUnit()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
