package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la creación y actualización de cursos")
public record CourseRequest(
        
    /**
     * Nombre del curso.
     */
    @Schema(
        description = "Nombre del curso",
        example = "Programación Avanzada") 
    @JsonProperty("name") 
    @NotBlank(message = "EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String name,

    /**
     * Código único del curso.
     */
    @Schema(
        description = "Código único del curso", 
        example = "PROG-101") 
    @JsonProperty("course_code") 
    @NotBlank(message = "EL CÓDIGO DEL CURSO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String courseCode,

    /**
     * Descripción detallada del curso.
     */
    @Schema(
        description = "Descripción detallada del curso", 
        example = "Curso avanzado de programación orientada a objetos") 
    @JsonProperty("description") 
    String description,

    /**
     * Identificador único del profesor que imparte el curso.
     */
    @Schema(
        description = "Identificador único del profesor",
        example = "5")
    @JsonProperty("professor_id")
    @NotNull(message = "EL ID DEL PROFESOR ES UN CAMPO OBLIGATORIO Y NO PUEDE SER NULO")
    Long professorId,

    /**
     * Capacidad máxima de estudiantes que pueden inscribirse al curso.
     */
    @Schema(
        description = "Capacidad máxima de estudiantes", 
        example = "30") 
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

    /**
     * Valida que el ID del profesor sea válido.
     * El ID debe ser distinto de nulo y mayor que cero.
     * 
     * @return true si el ID del profesor es válido, false en caso contrario
     */
    public boolean isValidProfessorId() {
        return professorId != null && professorId > 0;
    }
}
