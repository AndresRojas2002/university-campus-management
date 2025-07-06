package co.com.andres.university_campus_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.andres.university_campus_management.model.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
