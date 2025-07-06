package com.dojotrack.api.repository;

import com.dojotrack.api.model.ClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClassAttendanceRepository extends JpaRepository<ClassAttendance, Long> {

    List<ClassAttendance> findByClassSessionId(Long classSessionId);

    List<ClassAttendance> findByStudentIdAndClassSession_DateGreaterThanEqual(Long studentId, LocalDate date);

    long countByStudentIdAndAttendedTrue(Long studentId);

    List<ClassAttendance> findByStudentIdAndAttendedTrueOrderByClassSession_DateAsc(Long studentId);


    @Query("SELECT ca.classSession.discipline.name, COUNT(ca) " +
            "FROM ClassAttendance ca " +
            "WHERE ca.student.id = :userId AND ca.attended = true " +
            "GROUP BY ca.classSession.discipline.name")
    List<Object[]> getAttendanceByDiscipline(@Param("userId") Long userId);

}
