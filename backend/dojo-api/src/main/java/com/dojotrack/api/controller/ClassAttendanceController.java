package com.dojotrack.api.controller;

import com.dojotrack.api.model.ClassAttendance;
import com.dojotrack.api.repository.ClassAttendanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class ClassAttendanceController {

    private final ClassAttendanceRepository attendanceRepository;

    public ClassAttendanceController(ClassAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping
    public List<ClassAttendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @PostMapping
    public ClassAttendance markAttendance(@RequestBody ClassAttendance attendance) {
        return attendanceRepository.save(attendance);
    }

}


