package co.com.andres.university_campus_management.service;

import java.util.List;

import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;

/**
 * Interfaz de servicio para la gestión de profesores en el sistema universitario.
 * 
 * Esta interfaz define los métodos principales para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre los profesores, así como búsquedas
 * específicas por diferentes criterios.
 * 
 * Los métodos trabajan con DTOs (Data Transfer Objects) para mantener la separación
 * entre la capa de servicio y la capa de presentación, asegurando una arquitectura
 * limpia y mantenible.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public interface ProfessorService {

    /**
     * Crea un nuevo profesor en el sistema.
     * 
     * @param professorRequest DTO con la información del profesor a crear
     * @return ProfessorResponse DTO con la información del profesor creado
     */
    ProfessorResponse createProfessor(ProfessorRequest professorRequest);

    /**
     * Obtiene todos los profesores registrados en el sistema.
     * 
     * @return Lista de ProfessorResponse con todos los profesores
     */
    List<ProfessorResponse> getAllProfessor();

    /**
     * Busca un profesor por su identificador único.
     * 
     * @param id Identificador único del profesor
     * @return ProfessorResponse DTO con la información del profesor encontrado
     */
    ProfessorResponse getById(Long id);

    /**
     * Actualiza la información de un profesor existente.
     * 
     * @param id Identificador único del profesor a actualizar
     * @param professorRequest DTO con la nueva información del profesor
     * @return ProfessorResponse DTO con la información actualizada del profesor
     */
    ProfessorResponse updateProfessor(Long id, ProfessorRequest professorRequest);

    /**
     * Elimina un profesor del sistema por su identificador único.
     * 
     * @param id Identificador único del profesor a eliminar
     */
    void deleteProfessor(Long id);

    /**
     * Busca profesores por nombre o apellido.
     * 
     * @param text Texto a buscar en el nombre o apellido del profesor
     * @return Lista de ProfessorResponse con los profesores que coinciden con la búsqueda
     */
    List<ProfessorResponse> getByNameOtByLastName(String text);
    
}
  