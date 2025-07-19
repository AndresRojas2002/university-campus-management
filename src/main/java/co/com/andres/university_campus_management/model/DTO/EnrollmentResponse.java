package co.com.andres.university_campus_management.model.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para la respuesta de matrículas")
public record EnrollmentResponse(

    /**
     * Identificador único de la matrícula.
     */
    @Schema(description = "ID único de la matrícula", example = "1")
    @JsonProperty("id_enrollment")
    Long idEnrollment,

    /**
     * Identificador del estudiante matriculado.
     */
    @Schema(description = "ID del estudiante", example = "1")
    @JsonProperty("student_id")
    Long studentId,

    /**
     * Identificador del curso en el que se matricula.
     */
    @Schema(description = "ID del curso", example = "1")
    @JsonProperty("course_id")
    Long courseId,

    /**
     * Fecha en la que se realizó la matrícula.
     */
    @Schema(description = "Fecha de matrícula", example = "2024-01-15")
    @JsonProperty("enrollment_date")
    LocalDate enrollmentDate,

    /**
     * Estado actual de la matrícula.
     */
    @Schema(description = "Estado de la matrícula", example = "ACTIVE")
    @JsonProperty("enrollment_state")
    EnrollmentState enrollmentState
) {
}
