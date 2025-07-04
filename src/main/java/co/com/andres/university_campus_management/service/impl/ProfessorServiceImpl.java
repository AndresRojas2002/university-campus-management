package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.mapper.ProfessorMapper;
import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;
import co.com.andres.university_campus_management.repository.ProfessorRepository;
import co.com.andres.university_campus_management.service.ProfessorService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Override
    public ProfessorResponse createProfessor(ProfessorRequest professorRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProfessorResponse> getAllProfessor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfessorResponse getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteProfessor(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ProfessorResponse updateProfessor(Long id, ProfessorRequest professorRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProfessorResponse> getByNameOtByLastName(String text) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
