package com.dojotrack.api.repository;


import com.dojotrack.api.model.RecurringClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringClassScheduleRepository extends JpaRepository<RecurringClassSchedule, Long> {
}
