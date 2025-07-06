package co.com.andres.university_campus_management.model.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) que representa la solicitud de creación de
 * una matrícula estudiantil en el sistema de gestión universitaria.
 * 
 * Este record encapsula todos los datos necesarios para registrar una nueva
 * matrícula, incluyendo validaciones y métodos de utilidad para el procesamiento
 * de datos de inscripción académica.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Schema(description = "DTO para la creación de matrículas estudiantiles")
public record EnrollmentRequest(

        /**
         * Identificador único del estudiante.
         * Campo obligatorio que no puede ser nulo.
         */
        @Schema(
            description = "Identificador único del estudiante",
            example = "1", 
            required = true) 
        @JsonProperty("student_id") 
        @NotNull(message = "EL ID DEL ESTUDIANTE ES UN CAMPO OBLIGATORIO Y NO PUEDE SER NULO")
        Long idStudent,

        /**
         * Identificador único del curso.
         * Campo obligatorio que no puede ser nulo.
         */
        @Schema(
            description = "Identificador único del curso",
            example = "1", 
            required = true) 
        @JsonProperty("course_id")
        @NotNull(message = "EL ID DEL CURSO ES UN CAMPO OBLIGATORIO Y NO PUEDE SER NULO")
        Long idCourse,

        /**
         * Fecha de matrícula del estudiante.
         * Campo opcional que se establece por defecto con la fecha actual si es nulo.
         */
        @Schema(
            description = "Fecha de matrícula del estudiante",
            example = "2024-01-15", 
            required = false) 
        @JsonProperty("enrollment_date")
        LocalDate enrollmentDate

) {

    /**
     * Constructor compacto que inicializa la fecha de matrícula
     * con la fecha actual si no se proporciona.
     */
    public EnrollmentRequest {
        if (enrollmentDate == null) {
            enrollmentDate = LocalDate.now();
        }
    }

    /**
     * Valida que el ID del estudiante sea válido.
     * @return true si el ID es válido, false en caso contrario
     */
    public boolean isValidIdStudent() {
        return idStudent != null && idStudent > 0;
    }

    /**
     * Valida que el ID del curso sea válido.
     * @return true si el ID es válido, false en caso contrario
     */
    public boolean isValidIdCourse() {
        return idCourse != null && idCourse > 0;
    }

    /**
     * Valida que la fecha de matrícula sea válida y contenga un formato válido.
     * @return true si la fecha es válida y tiene formato correcto, false en caso contrario
     */
    public boolean isValidEnrollmentDate() {
        return enrollmentDate != null && 
               !enrollmentDate.isBefore(LocalDate.now()) &&
               enrollmentDate.toString().matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
