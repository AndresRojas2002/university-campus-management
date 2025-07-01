package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;
import co.com.andres.university_campus_management.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<CourseResponse> getAllCourse() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public CourseResponse byIdCourse(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void deleteCourse(Long Id) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public List<CourseResponse> getByName(String text) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<CourseResponse> getByCourseCode(String text) {
        // TODO Auto-generated method stub
        return null;
    }

}
