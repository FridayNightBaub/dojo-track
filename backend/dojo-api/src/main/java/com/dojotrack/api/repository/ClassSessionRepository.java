package com.dojotrack.api.repository;

import com.dojotrack.api.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
}
