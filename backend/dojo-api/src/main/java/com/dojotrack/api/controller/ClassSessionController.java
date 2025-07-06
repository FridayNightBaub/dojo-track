package com.dojotrack.api.controller;

import com.dojotrack.api.model.ClassAttendance;
import com.dojotrack.api.model.ClassSession;
import com.dojotrack.api.repository.ClassAttendanceRepository;
import com.dojotrack.api.repository.ClassSessionRepository;
import com.dojotrack.api.service.SessionGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessions")
public class ClassSessionController {

    @Autowired
    private SessionGenerationService sessionGenerationService;

    @Autowired
    private ClassAttendanceRepository classAttendanceRepository;

    private final ClassSessionRepository classSessionRepository;

    public ClassSessionController(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    @GetMapping
    public List<ClassSession> getAllSessions() {
        return classSessionRepository.findAll();
    }

    @GetMapping("/today")
    public List<ClassSession> getTodaySessions() {
        LocalDate today = LocalDate.now();
        return classSessionRepository.findByDate(today);
    }

    @GetMapping("/{id}/attendees")
    public List<ClassAttendance> getSessionAttendees(@PathVariable Long id) {
        return classAttendanceRepository.findByClassSessionId(id);
    }


    @PostMapping
    public ClassSession createSession(@RequestBody ClassSession session) {
        return classSessionRepository.save(session);
    }

    @PostMapping("/generate")
    public String generateWeeklySessions() {
        int created = sessionGenerationService.generateSessionsForWeek();
        return created + " class sessions created for this week.";
    }

}
