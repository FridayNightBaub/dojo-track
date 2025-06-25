package com.dojotrack.api.controller;

import com.dojotrack.api.model.ClassSession;
import com.dojotrack.api.repository.ClassSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class ClassSessionController {

    private final ClassSessionRepository classSessionRepository;

    public ClassSessionController(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    @GetMapping
    public List<ClassSession> getAllSessions() {
        return classSessionRepository.findAll();
    }

    @PostMapping
    public ClassSession createSession(@RequestBody ClassSession session) {
        return classSessionRepository.save(session);
    }

}
