package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) que representa la respuesta de información
 * de un curso en el sistema de gestión universitaria.
 * 
 * Este record encapsula todos los datos necesarios para devolver la información
 * completa de un curso, incluyendo su identificador único, datos académicos
 * y la referencia al profesor asignado.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Schema(description = "DTO para la respuesta de información de cursos")
public record CourseResponse(

        /**
         * Identificador único del curso.
         */
        @Schema(description = "ID único del curso", example = "1")
        @JsonProperty("id_course")
        Long idCourse,

        /**
         * Nombre del curso.
         */
        @Schema(description = "Nombre del curso", example = "Programación Avanzada")
        @JsonProperty("name")
        String name,

        /**
         * Código único del curso.
         */
        @Schema(description = "Código único del curso", example = "PROG-101")
        @JsonProperty("course_code")
        String courseCode,

        /**
         * Descripción detallada del curso.
         */
        @Schema(description = "Descripción detallada del curso", example = "Curso avanzado de programación orientada a objetos")
        @JsonProperty("description")
        String description,

        /**
         * Capacidad máxima de estudiantes que pueden inscribirse al curso.
         */
        @Schema(description = "Capacidad máxima de estudiantes", example = "30")
        @JsonProperty("max_capacity")
        Integer maxCapacity,

        /**
         * Identificador del profesor asignado al curso.
         */
        @Schema(description = "Identificador del profesor asignado", example = "1")
        @JsonProperty("professor_id")
        Long professorId) {

}
