package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Repository
public class ReportsRepositoryCustomImpl implements ReportsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailyWaterIntakeReportDTO> findDailyReport(Long userId, OffsetDateTime date) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DailyWaterIntakeReportDTO> query = builder.createQuery(DailyWaterIntakeReportDTO.class);
        Root<WaterIntake> root = query.from(WaterIntake.class);

        // Normalize the date to UTC (Offset +00:00)
        OffsetDateTime normalizedDate = date.withOffsetSameInstant(ZoneOffset.UTC);

        // Extracting the date part using the date function (convert to UTC first)
        Expression<Date> functionConvertTz = builder.function(
            "convert_tz", Date.class, root.get("dateTimeUTC"),
            builder.literal("+00:00"), builder.literal("+00:00"));
        Expression<Date> functionDate = builder.function("date", Date.class, functionConvertTz);

        // Converting normalized OffsetDateTime to Date to ensure correct comparison (date only)
        Date dateOnly = Date.from(normalizedDate.toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());

        // Creating the predicates for filtering
        Predicate datePredicate = builder.equal(functionDate, dateOnly);
        Predicate userPredicate = builder.equal(root.get("user").get("id"), userId);

        // Building the selection part of the query
        CompoundSelection<DailyWaterIntakeReportDTO> selection = builder.construct(DailyWaterIntakeReportDTO.class,
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
