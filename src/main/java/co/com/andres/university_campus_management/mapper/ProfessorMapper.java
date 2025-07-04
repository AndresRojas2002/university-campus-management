package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;
import co.com.andres.university_campus_management.model.entity.Professor;

/**
 * Mapper para la conversión entre entidades y DTOs de profesores.
 * Utiliza MapStruct para generar automáticamente las implementaciones
 * de mapeo entre objetos Professor, ProfessorRequest y ProfessorResponse.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    /**
     * Convierte un ProfessorRequest a una entidad Professor.
     * Ignora el campo idProfessor ya que se genera automáticamente.
     * 
     * @param request DTO con los datos del profesor a crear
     * @return Entidad Professor mapeada
     */
    @Mapping(target = "idProfessor", ignore = true)
    Professor toEntity(ProfessorRequest request);

    /**
     * Convierte una entidad Professor a un ProfessorResponse.
     * 
     * @param professor Entidad del profesor a convertir
     * @return DTO de respuesta con los datos del profesor
     */
    ProfessorResponse toResponse(Professor professor);
    
}
