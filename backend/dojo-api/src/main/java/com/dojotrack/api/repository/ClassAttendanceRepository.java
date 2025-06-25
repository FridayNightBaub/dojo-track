package com.dojotrack.api.repository;

import com.dojotrack.api.model.ClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassAttendanceRepository extends JpaRepository<ClassAttendance, Long> {
}
