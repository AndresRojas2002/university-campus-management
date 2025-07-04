package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;
import co.com.andres.university_campus_management.model.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    
    @Mapping (target = "idCourse", ignore = true)
    Course toEntity(CourseRequest request); 

    CourseResponse toResponse(Course course);
    
}
