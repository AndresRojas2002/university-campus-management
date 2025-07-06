package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.EnrollmentRequest;
import co.com.andres.university_campus_management.model.DTO.EnrollmentResponse;
import co.com.andres.university_campus_management.model.entity.Enrollment;

/**
 * Mapper para la conversión entre entidades y DTOs de matrículas.
 * Utiliza MapStruct para generar automáticamente las implementaciones
 * de mapeo entre objetos Enrollment, EnrollmentRequest y EnrollmentResponse.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    /**
     * Convierte una entidad Enrollment a un EnrollmentResponse.
     * 
     * @param enrollment Entidad de la matrícula a convertir
     * @return DTO de respuesta con los datos de la matrícula
     */
    EnrollmentResponse toResponse(Enrollment enrollment);

    /**
     * Convierte un EnrollmentRequest a una entidad Enrollment.
     * Ignora el campo idEnrollment ya que se genera automáticamente.
     * 
     * @param enrollmentRequest DTO con los datos de la matrícula a crear
     * @return Entidad Enrollment mapeada
     */
    @Mapping(target = "idEnrollment", ignore = true)
    Enrollment toEntity(EnrollmentRequest enrollmentRequest);
}
