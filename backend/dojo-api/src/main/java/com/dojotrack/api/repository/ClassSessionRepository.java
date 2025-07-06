package com.dojotrack.api.repository;

import com.dojotrack.api.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    boolean existsByDisciplineIdAndInstructorIdAndDateAndTime(Long disciplineId, Long instructorId, LocalDate date, LocalTime time);

    List<ClassSession> findByDate(LocalDate date);
}
