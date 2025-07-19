package co.com.andres.university_campus_management.model.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la solicitud de matrículas")
public record EnrollmentRequest(

    /**
     * Identificador del estudiante que se va a matricular.
     */
    @Schema(description = "ID del estudiante", example = "1")
    @JsonProperty("student")
    @NotNull(message = "EL ID DEL ESTUDIANTE ES OBLIGATORIO")
    Long student,

    /**
     * Identificador del curso en el que se va a matricular.
     */
    @Schema(description = "ID del curso", example = "1")
    @JsonProperty("course")
    @NotNull(message = "EL ID DEL CURSO ES OBLIGATORIO")
    Long course,

    /**
     * Fecha en la que se realiza la matrícula.
     */
    @Schema(description = "Fecha de matrícula", example = "2024-01-15")
    @JsonProperty("enrollment_date")
    LocalDate enrollmentDate

    ,
    /**
     * Estado de la matrícula.
     * Puede ser ACTIVE, CANCELLED o GRADUATED.
     */
    @Schema(description = "Estado de la matrícula", example = "ACTIVE")
    @JsonProperty("enrollment_state")
    EnrollmentState enrollmentState
) {

    /**
     * Constructor compacto que inicializa la fecha de matrícula
     * con la fecha actual si no se proporciona.
     */
    public EnrollmentRequest {
        if (enrollmentDate == null) {
            enrollmentDate = LocalDate.now();
        }
        // Si el estado de la matrícula es nulo, se asigna el valor ACTIVE por defecto
        if (enrollmentState == null) {
            enrollmentState = EnrollmentState.ACTIVE;
        }
    }

    /**
     * Valida que el ID del estudiante sea válido.
     * @return true si el ID es válido, false en caso contrario
     */
    /**
     * Valida que el ID del estudiante sea válido.
     * @return true si el ID es válido, false en caso contrario
     */
    public boolean isValidIdStudent() {
        return student != null && student > 0;
    }

    /**
     * Valida que el ID del curso sea válido.
     * @return true si el ID es válido, false en caso contrario
     */
    public boolean isValidIdCourse() {
        return course != null && course > 0;
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

