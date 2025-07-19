package co.com.andres.university_campus_management.service;


import java.util.List;

import co.com.andres.university_campus_management.model.DTO.EnrollmentRequest;
import co.com.andres.university_campus_management.model.DTO.EnrollmentResponse;
import co.com.andres.university_campus_management.model.entity.EnrollmentState;


/**
 * Interfaz de servicio para la gestión de matrículas en el sistema universitario.
 * 
 * Esta interfaz define los métodos principales para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre las matrículas, así como búsquedas
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
public interface EnrollmentService {

    /**
     * Crea una nueva matrícula en el sistema.
     * 
     * @param enrollmentRequest DTO con la información de la matrícula a crear
     * @return EnrollmentResponse DTO con la información de la matrícula creada
     */
    EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest);

    /**
     * Obtiene todas las matrículas del sistema.
     * 
     * @return List<EnrollmentResponse> DTO con la información de todas las matrículas
     */
    List<EnrollmentResponse> getAllEnrollments();

    /**
     * Obtiene una matrícula por el ID del estudiante.
     * 
     * @param idStudent ID del estudiante
     * @return EnrollmentResponse con la información de la matrícula
     */
    EnrollmentResponse getEnrollmentByStudentId(Long idStudent);

    /**
     * Obtiene una matrícula por el ID del curso.
     * 
     * @param idCourse ID del curso
     * @return EnrollmentResponse con la información de la matrícula
     */
    EnrollmentResponse getEnrollmentByCourseId(Long idCourse);
    
    /**
     * Obtiene una matrícula por su identificador único.
     * 
     * @param idEnrollment Identificador único de la matrícula
     * @return EnrollmentResponse DTO con la información de la matrícula encontrada
     */
    EnrollmentResponse getEnrollmentById(Long idEnrollment);


    /**
     * Elimina una matrícula del sistema.
     * 
     * @param idEnrollment Identificador único de la matrícula a eliminar
     */
    void deleteEnrollment(Long idEnrollment);

    /**
     * Obtiene todas las matrículas por estado específico.
     * 
     * @param state Estado de la matrícula a buscar
     * @return List<EnrollmentResponse> Lista de matrículas con el estado especificado
     */
    List<EnrollmentResponse> getEnrollmentByState(EnrollmentState state);
}
