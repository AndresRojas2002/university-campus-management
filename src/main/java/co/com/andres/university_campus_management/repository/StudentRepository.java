package co.com.andres.university_campus_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Student;

/**
 * Repositorio para la gestión de entidades Student.
 * Proporciona métodos de acceso a datos para estudiantes utilizando Spring Data JPA.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Busca un estudiante por su email único.
     * 
     * @param email Email del estudiante a buscar
     * @return Optional que contiene el estudiante si existe, vacío en caso contrario
     */
    Optional<Student> findByEmail(String email);

    /**
     * Busca un estudiante por su número de estudiante único.
     * 
     * @param studentNumber Número del estudiante a buscar
     * @return Optional que contiene el estudiante si existe, vacío en caso contrario
     */
    Optional<Student> findByStudentNumber(String studentNumber);

    /**
     * Busca estudiantes cuyo nombre o apellido contenga el texto especificado (ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el nombre del estudiante
     * @param text2 Texto a buscar en el apellido del estudiante
     * @return Lista de estudiantes que coinciden con el criterio de búsqueda
     */
    List<Student> findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String text, String text2);
    
}
