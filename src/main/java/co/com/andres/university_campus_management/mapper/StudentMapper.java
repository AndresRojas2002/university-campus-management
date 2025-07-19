package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.model.entity.Student;

/**
 * Mapper para la conversión entre entidades y DTOs de estudiantes.
 * Utiliza MapStruct para generar automáticamente las implementaciones
 * de mapeo entre objetos Student, StudentRequest y StudentResponse.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    /**
     * Convierte un StudentRequest a una entidad Student.
     * Ignora el campo idStudent ya que se genera automáticamente.
     * 
     * @param request DTO con los datos del estudiante a crear
     * @return Entidad Student mapeada
     */
    @Mapping(target = "idStudent", ignore = true)
    @Mapping(target = "roles", expression = "java(Set.of(\"ROLE_STUDENT\"))")
    Student toEntity(StudentRequest request);

    /**
     * Convierte una entidad Student a un StudentResponse.
     * 
     * @param student Entidad del estudiante a convertir
     * @return DTO de respuesta con los datos del estudiante
     */
    StudentResponse toResponse(Student student);

}
