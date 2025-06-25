package com.dojotrack.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClassSession classSession;

    @ManyToOne
    private User student;

    private boolean attended; // updated after class
}
