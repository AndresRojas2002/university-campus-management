package co.com.andres.university_campus_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByNameContainingIgnoreCase(String text);

    List <Course> findByCourseCodeContainingIgnoreCase (String text);

    
}
