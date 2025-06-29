package co.com.andres.university_campus_management.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "respuesta de la informacion del estudiante")
public record StudentResponse(
@Schema (description ="identificador unico del estudiante", example = 1)
        Long idStudent,

        @Schema(description = "nombre del estudiante", example = "Luis Andres")
        String name,

        @Schema(description = "apellido de estudiante", example = "Rojas Acevedo")
        String lastName,

        @Schema(description = "correo electronico del estudiante", example = "andres.rojas@universidad.com")
        String email,
        @Schema(description = "dirección de residencia del estudiante", example = "Calle 123 #45-67, Barrio Centro, Ciudad")
        String address,

        @Schema(description = "número de teléfono del estudiante", example = "3001234567")
        String phone,

        @Schema(description = "número del estudiante", example = "2024001234")
        String studentNumber) {

}
