package co.com.andres.university_campus_management.service;

import java.util.List;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;

/**
 * Interfaz de servicio para la gestión de cursos en el sistema universitario.
 * 
 * Esta interfaz define los métodos principales para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre los cursos, así como búsquedas
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
public interface CourseService {

    /**
     * Crea un nuevo curso en el sistema.
     * 
     * @param courseRequest DTO con la información del curso a crear
     * @return CourseResponse DTO con la información del curso creado
     */
    CourseResponse createCourse(CourseRequest courseRequest);

    /**
     * Busca un curso por su identificador único.
     * 
     * @param id Identificador único del curso
     * @return CourseResponse DTO con la información del curso encontrado
     */
    CourseResponse byIdCourse(Long id);

    /**
     * Obtiene todos los cursos registrados en el sistema.
     * 
     * @return Lista de CourseResponse con todos los cursos
     */
    List<CourseResponse> getAllCourse();

    /**
     * Actualiza la información de un curso existente.
     * 
     * @param id Identificador único del curso a actualizar
     * @param courseRequest DTO con la nueva información del curso
     * @return CourseResponse DTO con la información actualizada del curso
     */
    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    /**
     * Elimina un curso del sistema por su identificador único.
     * 
     * @param id Identificador único del curso a eliminar
     */
    void deleteCourse(Long id);

    /**
     * Busca cursos por nombre.
     * 
     * @param text Texto a buscar en el nombre del curso
     * @return Lista de CourseResponse con los cursos que coinciden con la búsqueda
     */
    List<CourseResponse> getByName(String text);

    /**
     * Busca cursos por código de curso.
     * 
     * @param text Código del curso a buscar
     * @return Lista de CourseResponse con los cursos que coinciden con el código
     */
    List<CourseResponse> getByCourseCode(String text);


    
}
