package com.drinkwater.apidrinkwater.reports.repository;

import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeReportDTO;
import com.drinkwater.apidrinkwater.hydrationtracking.model.WaterIntake;
import com.drinkwater.apidrinkwater.reports.dto.DailyWaterIntakeRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReportsRepositoryCustomImpl implements ReportsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailyWaterIntakeReportDTO> findDailyReport(DailyWaterIntakeRequestDTO request) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DailyWaterIntakeReportDTO> query = builder.createQuery(DailyWaterIntakeReportDTO.class);
        Root<WaterIntake> root = query.from(WaterIntake.class);

        Date date = Date.from(request.getDate().toInstant());

        Expression<Date> functionDateTimeUTC = builder.function("date", Date.class, root.get("dateTimeUTC"));

        CompoundSelection<DailyWaterIntakeReportDTO> selection = builder.construct(DailyWaterIntakeReportDTO.class,
            functionDateTimeUTC,
            builder.count(root.get("id")),
            builder.sum(root.get("volume")));

        Predicate datePredicate = builder.equal(functionDateTimeUTC, date);
        Predicate userPredicate = builder.equal(root.get("user").get("id"), request.getUserId());

        query.select(selection);
        query.where(builder.and(datePredicate, userPredicate));
        query.groupBy(functionDateTimeUTC);

        return this.entityManager.createQuery(query).getResultList();
    }
}
