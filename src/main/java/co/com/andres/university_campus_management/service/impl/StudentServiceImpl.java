package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.repository.StudentRepository;
import co.com.andres.university_campus_management.service.StudentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudentResponse getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<StudentResponse> getByNameOrLastName(String text) {
        // TODO Auto-generated method stub
        return null;
    }


}

