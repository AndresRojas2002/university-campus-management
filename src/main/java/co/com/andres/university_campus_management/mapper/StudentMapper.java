package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.model.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping (target = "idStudent", ignore = true)
    Student toEntity(StudentRequest request); 

    StudentResponse toResponse(Student student);
    
}
