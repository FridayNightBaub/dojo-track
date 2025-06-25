package com.dojotrack.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_discipline_enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDisciplineEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    private String currentBelt; // optional: e.g. "White", "Blue", etc.
}
