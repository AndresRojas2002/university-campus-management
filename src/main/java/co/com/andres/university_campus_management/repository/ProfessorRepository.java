package co.com.andres.university_campus_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.andres.university_campus_management.model.entity.Professor;

/**
 * Repositorio para la gestión de entidades Professor.
 * Proporciona métodos de acceso a datos para profesores utilizando Spring Data JPA.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    /**
     * Busca un profesor por su email único.
     * 
     * @param email Email del profesor a buscar
     * @return Optional que contiene el profesor si existe, vacío en caso contrario
     */
    Optional<Professor> findByEmail(String email);

    


    /**
     * Busca profesores cuyo nombre o apellido contenga el texto especificado (ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el nombre o apellido del profesor
     * @return Lista de profesores que coinciden con el criterio de búsqueda
     */
    List<Professor> findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String text, String text2);

}
