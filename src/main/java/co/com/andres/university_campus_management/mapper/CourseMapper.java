package co.com.andres.university_campus_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;
import co.com.andres.university_campus_management.model.entity.Course;

/**
 * Mapper para la conversión entre entidades y DTOs de cursos.
 * Utiliza MapStruct para generar automáticamente las implementaciones
 * de mapeo entre objetos Course, CourseRequest y CourseResponse.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Mapper(componentModel = "spring")
public interface CourseMapper {

    /**
     * Convierte un CourseRequest a una entidad Course.
     * Ignora el campo idCourse ya que se genera automáticamente.
     * 
     * @param request DTO con los datos del curso a crear
     * @return Entidad Course mapeada
     */
    @Mapping(target = "idCourse", ignore = true)
    Course toEntity(CourseRequest request); 

    /**
     * Convierte una entidad Course a un CourseResponse.
     * 
     * @param course Entidad del curso a convertir
     * @return DTO de respuesta con los datos del curso
     */
    CourseResponse toResponse(Course course);
    
}
