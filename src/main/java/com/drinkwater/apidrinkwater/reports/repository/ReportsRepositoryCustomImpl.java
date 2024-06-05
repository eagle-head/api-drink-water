package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.WaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Repository
public class ReportsRepositoryCustomImpl implements ReportsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WaterIntakeReportDTO> findReport(Long userId, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<WaterIntakeReportDTO> query = builder.createQuery(WaterIntakeReportDTO.class);
        Root<WaterIntake> root = query.from(WaterIntake.class);

        // Convert LocalDate to Date for comparison
        Date startDateOnly = Date.from(startDate.atStartOfDay(ZoneOffset.UTC).toInstant());
        Date endDateOnly = Date.from(endDate.atStartOfDay(ZoneOffset.UTC).toInstant());

        // Extracting the date part using the date function (convert to UTC first)
        Expression<Date> functionConvertTz = builder.function(
            "convert_tz", Date.class, root.get("dateTimeUTC"),
            builder.literal("+00:00"), builder.literal("+00:00"));

        Expression<Date> functionDate = builder.function("date", Date.class, functionConvertTz);

        // Creating the predicates for filtering
        Predicate datePredicate = builder.between(functionDate, startDateOnly, endDateOnly);
        Predicate userPredicate = builder.equal(root.get("user").get("id"), userId);

        // Building the selection part of the query
        CompoundSelection<WaterIntakeReportDTO> selection = builder.construct(WaterIntakeReportDTO.class,
            functionDate,
            builder.count(root.get("id")),
            builder.sum(root.get("volume")));

        // Applying selection, where clause, and group by
        query.select(selection)
            .where(builder.and(datePredicate, userPredicate))
            .groupBy(functionDate);

        // Executing the query
        return this.entityManager.createQuery(query).getResultList();
    }
}
