package com.dojotrack.api.service;

import com.dojotrack.api.model.ClassSession;
import com.dojotrack.api.model.RecurringClassSchedule;
import com.dojotrack.api.repository.ClassSessionRepository;
import com.dojotrack.api.repository.RecurringClassScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class SessionGenerationService {

    private final RecurringClassScheduleRepository scheduleRepo;
    private final ClassSessionRepository sessionRepo;

    public SessionGenerationService(RecurringClassScheduleRepository scheduleRepo,
                                    ClassSessionRepository sessionRepo) {
        this.scheduleRepo = scheduleRepo;
        this.sessionRepo = sessionRepo;
    }

    public int generateSessionsForWeek() {
        List<RecurringClassSchedule> activeSchedules = scheduleRepo.findAll().stream()
                .filter(RecurringClassSchedule::isActive)
                .toList();

        int createdCount = 0;
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        for (RecurringClassSchedule schedule : activeSchedules) {
            LocalDate sessionDate = today.with(TemporalAdjusters.nextOrSame(schedule.getDayOfWeek()));

            if (!sessionDate.isAfter(endOfWeek)) {
                boolean alreadyExists = sessionRepo.existsByDisciplineIdAndInstructorIdAndDateAndTime(
                        schedule.getDiscipline().getId(),
                        schedule.getInstructor().getId(),
                        sessionDate,
                        schedule.getTime()
                );

                if (!alreadyExists) {
                    ClassSession session = ClassSession.builder()
                            .discipline(schedule.getDiscipline())
                            .instructor(schedule.getInstructor())
                            .date(sessionDate)
                            .time(schedule.getTime())
                            .location(schedule.getLocation())
                            .title(schedule.getTitle())
                            .build();

                    sessionRepo.save(session);
                    createdCount++;
                }
            }
        }

        return createdCount;
    }
}
