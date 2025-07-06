package com.dojotrack.api.service;

import com.dojotrack.api.model.ClassAttendance;
import com.dojotrack.api.repository.ClassAttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserProgressService {

    private final ClassAttendanceRepository attendanceRepo;

    public UserProgressService(ClassAttendanceRepository attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public int calculateCurrentStreak(Long userId) {
        List<ClassAttendance> attended = attendanceRepo
                .findByStudentIdAndAttendedTrueOrderByClassSession_DateAsc(userId);

        int streak = 0;
        for (int i = attended.size() - 1; i >= 0; i--) {
            LocalDate current = attended.get(i).getClassSession().getDate();
            if (i == attended.size() - 1 ||
                    current.minusDays(1).equals(attended.get(i + 1).getClassSession().getDate())) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
    }

    public int calculateLongestStreak(Long userId) {
        List<ClassAttendance> attended = attendanceRepo
                .findByStudentIdAndAttendedTrueOrderByClassSession_DateAsc(userId);

        int longest = 0;
        int current = 1;

        for (int i = 1; i < attended.size(); i++) {
            LocalDate prev = attended.get(i - 1).getClassSession().getDate();
            LocalDate curr = attended.get(i).getClassSession().getDate();

            if (!curr.isAfter(prev.plusDays(1))) {
                current++;
            } else {
                longest = Math.max(longest, current);
                current = 1;
            }
        }

        return Math.max(longest, current);
    }
}
