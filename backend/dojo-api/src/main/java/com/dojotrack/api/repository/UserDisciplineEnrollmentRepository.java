package com.dojotrack.api.repository;

import com.dojotrack.api.model.UserDisciplineEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDisciplineEnrollmentRepository extends JpaRepository<UserDisciplineEnrollment, Long> {
}
