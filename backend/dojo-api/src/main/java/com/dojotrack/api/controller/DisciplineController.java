package com.dojotrack.api.controller;

import com.dojotrack.api.model.Discipline;
import com.dojotrack.api.repository.DisciplineRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {

    private final DisciplineRepository disciplineRepository;

    public DisciplineController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping
    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

}
