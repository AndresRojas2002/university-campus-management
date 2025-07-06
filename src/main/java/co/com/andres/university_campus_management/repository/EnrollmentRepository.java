package co.com.andres.university_campus_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Enrollment;
import co.com.andres.university_campus_management.model.entity.EnrollmentState;

/**
 * Repositorio para la gestión de entidades Enrollment.
 * Proporciona métodos de acceso a datos para matrículas utilizando Spring Data JPA.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Busca todas las matrículas de un estudiante específico.
     * 
     * @param studentId Identificador del estudiante
     * @return Lista de matrículas del estudiante
     */
    Optional<Enrollment> findByStudentId(Long studentId);

    /**
     * Busca todas las matrículas de un curso específico.
     * 
     * @param courseId Identificador del curso
     * @return Lista de matrículas del curso
     */
    Optional<Enrollment> findByCourseId(Long courseId);

    /**
     * Busca matrículas por estado específico.
     * 
     * @param state Estado de la matrícula a buscar (ACTIVE, CANCELLED, GRADUATED)
     * @return Lista de matrículas con el estado especificado
     */
    List<Enrollment> findByEnrollmentState(EnrollmentState state);

   

   
}
