package co.com.andres.university_campus_management.service;

import java.util.List;

import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.model.DTO.StudentRequest;

/**
 * Interfaz de servicio para la gestión de estudiantes en el sistema universitario.
 * 
 * Esta interfaz define los métodos principales para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre los estudiantes, así como búsquedas
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
public interface StudentService {

    /**
     * Crea un nuevo estudiante en el sistema.
     * 
     * @param studentRequest DTO con la información del estudiante a crear
     * @return StudentResponse DTO con la información del estudiante creado
     */
    StudentResponse createStudent(StudentRequest studentRequest);

    /**
     * Obtiene todos los estudiantes registrados en el sistema.
     * 
     * @return Lista de StudentResponse con todos los estudiantes
     */
    List<StudentResponse> getAllStudent();

    /**
     * Busca un estudiante por su identificador único.
     * 
     * @param id Identificador único del estudiante
     * @return StudentResponse DTO con la información del estudiante encontrado
    */
    StudentResponse getById(Long id);

    /**
     * Actualiza la información de un estudiante existente.
     * 
     * @param id Identificador único del estudiante a actualizar
     * @param studentRequest DTO con la nueva información del estudiante
     * @return StudentResponse DTO con la información actualizada del estudiante
    */
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    /**
     * Elimina un estudiante del sistema por su identificador único.
     * 
     * @param id Identificador único del estudiante a eliminar
     */
    void deleteById(Long id);

    /**
     * Busca estudiantes por nombre o apellido.
     * La búsqueda es insensible a mayúsculas/minúsculas y puede ser parcial.
     * 
     * @param text Texto a buscar en el nombre o apellido del estudiante
     * @return Lista de StudentResponse con los estudiantes que coinciden con la búsqueda
    */
    List<StudentResponse> getByNameOrLastName(String text);

}
