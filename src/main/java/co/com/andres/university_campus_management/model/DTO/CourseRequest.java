package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


/**
 * DTO (Data Transfer Object) que representa la solicitud de creación o
 * actualización de un curso en el sistema de gestión universitaria.
 * 
 * Este record encapsula todos los datos necesarios para registrar un nuevo
 * curso o actualizar la información de uno existente, incluyendo validaciones y
 * métodos de utilidad para el procesamiento de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Schema(description = "DTO para la creación y actualización de cursos")
public record CourseRequest(
        
        /**
         * Nombre del curso.
         * Campo obligatorio que no puede estar vacío.
         */
        @Schema(
            description = "Nombre del curso",
            example = "Programación Avanzada", 
            required = true) 
        @JsonProperty("name") 
        @NotBlank(message = "EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
        String name,

        /**
         * Código único del curso.
         * Campo obligatorio que no puede estar vacío.
         * Debe seguir el formato: 3-4 letras mayúsculas seguido de un guión y 3 dígitos.
         */
        @Schema(
            description = "Código único del curso", 
            example = "PROG-101", 
            required = true) 
        @JsonProperty("course_code") 
        @NotBlank(message = "EL CÓDIGO DEL CURSO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
        String courseCode,

        /**
         * Descripción detallada del curso.
         * Campo opcional que se establece por defecto si es nulo.
         */
        @Schema(
            description = "Descripción detallada del curso", 
            example = "Curso avanzado de programación orientada a objetos", 
            required = false) 
        @JsonProperty("description") 
        String description,

        /**
         * Capacidad máxima de estudiantes que pueden inscribirse al curso.
         * Campo opcional que debe estar entre 1 y 200 estudiantes.
         */
        @Schema(
            description = "Capacidad máxima de estudiantes", 
            example = "30", 
            required = false) 
        @JsonProperty("maximum_capacity") 
        Integer maxCapacity) {

    /**
     * Constructor compacto que valida y establece valores por defecto
     * para los campos opcionales del curso.
     */
    public CourseRequest {
        // Establece descripción por defecto si es nula
        if (description == null) {
            description = "No hay descripción disponible";
        }
        
        // Establece capacidad máxima por defecto si es nula
        if (maxCapacity == null ) {
            maxCapacity = 50;
        }
    }

    /**
     * Valida que el código del curso tenga el formato correcto.
     * El formato debe ser: 3-4 letras mayúsculas seguido de un guión y 3 dígitos.
     * 
     * @return true si el código es válido, false en caso contrario
     */
    public boolean isValidCourseCode() {
        return courseCode != null && courseCode.matches("^[A-Z]{3,4}-\\d{3}$");
    }

    /**
     * Valida que la capacidad máxima esté dentro del rango permitido.
     * La capacidad debe estar entre 1 y 50 estudiantes.
     * 
     * @return true si la capacidad es válida, false en caso contrario
     */
    public boolean isValidMaxCapacity() {
        return maxCapacity != null && maxCapacity >= 1 && maxCapacity <= 50;
    }



           

           
}
