package co.com.andres.university_campus_management.model.DTO;

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
         * Clave primaria generada automáticamente.
         */
        @Schema(description = "Identificador único del curso", example = "1", required = true) 
        Long idCourse,

        /**
         * Nombre del curso.
         * Campo obligatorio que identifica el curso.
         */
        @Schema(description = "Nombre del curso", example = "Programación Avanzada") 
        String name,

        /**
         * Código único del curso.
         * Campo obligatorio que sigue el formato: 3-4 letras mayúsculas seguido de un
         * guión y 3 dígitos.
         */
        @Schema(description = "Código único del curso", example = "PROG-101") 
        String courseCode,

        /**
         * Descripción detallada del curso.
         * Campo que proporciona información adicional sobre el contenido del curso.
         */
        @Schema(description = "Descripción detallada del curso", example = "Curso avanzado de programación orientada a objetos") 
        String description,

        /**
         * Capacidad máxima de estudiantes que pueden inscribirse al curso.
         * Campo que define el límite de estudiantes permitidos.
         */
        @Schema(description = "Capacidad máxima de estudiantes", example = "30")
        Integer maxCapacity,

        /**
         * Identificador del profesor asignado al curso.
         * Referencia a la clave primaria de la entidad Professor.
         */
        @Schema(description = "Identificador del profesor asignado", example = "1") 
        Long professorId) {

}
