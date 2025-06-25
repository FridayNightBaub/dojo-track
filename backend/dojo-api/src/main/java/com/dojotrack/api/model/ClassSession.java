package com.dojotrack.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "class_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Discipline discipline;

    @ManyToOne
    private User instructor;

    private LocalDate date;
    private LocalTime time;

    private String location;
    private String title; // optional: "No-Gi Fundamentals", "Striking Drills"

}
