package co.com.andres.university_campus_management.service;

import java.util.List;

import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;

public interface ProfessorService {

    ProfessorResponse createProfessor(ProfessorRequest professorRequest);

    List<ProfessorResponse> getAllProfessor();

    ProfessorResponse getById(Long id);

    ProfessorResponse updateProfessor(Long id, ProfessorRequest professorRequest);

    void deleteProfessor(Long id);

    List<ProfessorResponse> getByNameOtByLastName(String text);
    
}
  