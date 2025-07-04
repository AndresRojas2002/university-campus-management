package co.com.andres.university_campus_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;
import co.com.andres.university_campus_management.service.ProfessorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profesor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ProfessorResponse create(@Valid @RequestBody ProfessorRequest professorRequest) {
        return professorService.createProfessor(professorRequest);
    }

    @GetMapping
    public List<ProfessorResponse> getAll() {
        return professorService.getAllProfessor();
    }

    @GetMapping("/{id}")
    public ProfessorResponse getById(@PathVariable("id") Long id) {
        return professorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        professorService.deleteProfessor(id);
    }

    @PutMapping("/{id}")
    public ProfessorResponse update(@Valid   @PathVariable ("id") Long id, @RequestBody  ProfessorRequest professorRequest){
        return professorService.updateProfessor(id, professorRequest);
    }

    @GetMapping ("/buscar")
    public List<ProfessorResponse> getByNameOrLastName(@RequestParam ("b") String text){
        return professorService.getByNameOtByLastName(text);
    }
}
