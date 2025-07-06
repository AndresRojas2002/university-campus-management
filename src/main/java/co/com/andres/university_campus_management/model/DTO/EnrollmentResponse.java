package co.com.andres.university_campus_management.model.DTO;

import java.time.LocalDate;

import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import io.swagger.v3.oas.annotations.media.Schema;

public record EnrollmentResponse(

    /**
     * Identificador único de la matrícula.
     */
    @Schema(
        description = "Identificador único de la matrícula",
        example = "1",
        required = true
    )
    Long idEnrollment,

    /**
     * Identificador único del estudiante.
     */
    @Schema(
        description = "Identificador único del estudiante",
        example = "1",
        required = true
    )   
    Long idStudent,

    /**
     * Identificador único del curso.
     */
    @Schema(
        description = "Identificador único del curso",
        example = "1",
        required = true
    )
    Long idCourse,

    /**
     * Fecha de matrícula del estudiante.
     */
    @Schema(
        description = "Fecha de matrícula del estudiante",
        example = "2024-01-15",
        required = true
    )
    LocalDate enrollmentDate,

    /**
     * Estado de la matrícula.
     */
    @Schema(
        description = "Estado de la matrícula",
        example = "ACTIVE",
        required = true
    )
    EnrollmentState enrollmentState

) {

}
