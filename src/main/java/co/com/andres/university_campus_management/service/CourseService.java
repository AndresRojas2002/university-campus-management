package co.com.andres.university_campus_management.service;

import java.util.List;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;

public interface CourseService {

    CourseResponse createCourse (CourseRequest courseRequest);

    CourseResponse byIdCourse(Long id);

    List<CourseResponse> getAllCourse();

    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    void deleteCourse (Long Id);

    List<CourseResponse> getByName(String text);

    List<CourseResponse> getByCourseCode( String text);


    
}
