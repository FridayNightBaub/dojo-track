package com.dojotrack.api.controller;

import com.dojotrack.api.model.ClassAttendance;
import com.dojotrack.api.repository.ClassAttendanceRepository;
import com.dojotrack.api.service.UserProgressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserScheduleController {

    private final ClassAttendanceRepository attendanceRepo;

    private final UserProgressService progressService;

    public UserScheduleController(ClassAttendanceRepository attendanceRepo, UserProgressService progressService) {
        this.attendanceRepo = attendanceRepo;
        this.progressService = progressService;
    }

    @GetMapping("/{userId}/schedule")
    public List<ClassAttendance> getUpcomingSchedule(@PathVariable Long userId) {
        LocalDate today = LocalDate.now();
        return attendanceRepo.findByStudentIdAndClassSession_DateGreaterThanEqual(userId, today);
    }

    @GetMapping("/{userId}/progress/by-discipline")
    public Map<String, Long> getDisciplineBreakdown(@PathVariable Long userId) {
        List<Object[]> results = attendanceRepo.getAttendanceByDiscipline(userId);
        return results.stream().collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1]
        ));
    }

    @GetMapping("/{userId}/progress/total-attended")
    public long getTotalAttended(@PathVariable Long userId) {
        return attendanceRepo.countByStudentIdAndAttendedTrue(userId);
    }

    @GetMapping("/{userId}/progress/streak")
    public Map<String, Integer> getStreaks(@PathVariable Long userId) {
        int current = progressService.calculateCurrentStreak(userId);
        int longest = progressService.calculateLongestStreak(userId);

        Map<String, Integer> result = new HashMap<>();
        result.put("currentStreak", current);
        result.put("longestStreak", longest);
        return result;
    }


}
