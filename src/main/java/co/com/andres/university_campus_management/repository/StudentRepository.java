package co.com.andres.university_campus_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.andres.university_campus_management.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String text, String text2);
    
}
