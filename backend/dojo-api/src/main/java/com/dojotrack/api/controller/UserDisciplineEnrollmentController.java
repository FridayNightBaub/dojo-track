package com.dojotrack.api.controller;

import com.dojotrack.api.model.UserDisciplineEnrollment;
import com.dojotrack.api.repository.UserDisciplineEnrollmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class UserDisciplineEnrollmentController {

    private final UserDisciplineEnrollmentRepository enrollmentRepository;

    public UserDisciplineEnrollmentController(UserDisciplineEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @GetMapping
    public List<UserDisciplineEnrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping
    public UserDisciplineEnrollment createEnrollment(@RequestBody UserDisciplineEnrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

}
