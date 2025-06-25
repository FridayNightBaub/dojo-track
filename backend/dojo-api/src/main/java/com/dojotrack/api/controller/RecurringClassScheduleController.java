package com.dojotrack.api.controller;

import com.dojotrack.api.model.RecurringClassSchedule;
import com.dojotrack.api.repository.RecurringClassScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recurring-schedules")
public class RecurringClassScheduleController {

    private final RecurringClassScheduleRepository scheduleRepository;

    public RecurringClassScheduleController(RecurringClassScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public List<RecurringClassSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @PostMapping
    public RecurringClassSchedule createSchedule(@RequestBody RecurringClassSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @PatchMapping("/{id}/toggle")
    public RecurringClassSchedule toggleSchedule(@PathVariable Long id) {
        RecurringClassSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setActive(!schedule.isActive());
        return scheduleRepository.save(schedule);
    }

}
