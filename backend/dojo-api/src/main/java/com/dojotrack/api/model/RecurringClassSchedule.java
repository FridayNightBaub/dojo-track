package com.dojotrack.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "recurring_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Discipline discipline;

    @ManyToOne
    private User instructor;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime time;
    private String location;
    private String title; // optional

    private boolean active;

}
