package com.ifpb.pwebProject.repository;

import com.ifpb.pwebProject.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    @Query("select a from Activity a where a.date = :date and a.user.id = :id")
    List<Activity> getActivtiesPerDateByUser(@Param("date") LocalDate date, @Param("id") String userID);

    @Query("SELECT a FROM Activity a WHERE YEAR(a.date) = :year AND MONTH(a.date) = :month AND a.user.id = :userId")
    List<Activity> getActivitiesPerMonthByUser(
            @Param("year") int year,
            @Param("month") int month,
            @Param("userId") String userId
    );
}
