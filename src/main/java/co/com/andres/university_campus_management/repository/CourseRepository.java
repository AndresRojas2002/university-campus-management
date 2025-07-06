package co.com.andres.university_campus_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Course;

/**
 * Repositorio para la gestión de entidades Course.
 * Proporciona métodos de acceso a datos para cursos utilizando Spring Data JPA.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Busca un curso por su código único.
     * 
     * @param code Código del curso a buscar
     * @return Optional que contiene el curso si existe, vacío en caso contrario
     */
    Optional<Course> findByCourseCode(String code);

    /**
     * Busca cursos cuyo nombre contenga el texto especificado (ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el nombre del curso
     * @return Lista de cursos que coinciden con el criterio de búsqueda
     */
    List<Course> findByNameContainingIgnoreCase(String text);

    /**
     * Busca cursos cuyo código contenga el texto especificado (ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el código del curso
     * @return Lista de cursos que coinciden con el criterio de búsqueda
     */
    List<Course> findByCourseCodeContainingIgnoreCase(String text);

}

