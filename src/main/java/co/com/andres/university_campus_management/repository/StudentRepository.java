package co.com.andres.university_campus_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional <Student> findByStudentNumber(String StudentNumber);

    

    List<Student> findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String text, String text2);
    
}
