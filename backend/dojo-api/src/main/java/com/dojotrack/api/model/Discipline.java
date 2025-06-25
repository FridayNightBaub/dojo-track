package com.dojotrack.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "disciplines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

}
