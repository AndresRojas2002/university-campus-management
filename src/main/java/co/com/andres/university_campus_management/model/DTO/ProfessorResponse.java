package co.com.andres.university_campus_management.model.DTO;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para la respuesta de profesores")
public record ProfessorResponse(

    /**
     * Identificador único del profesor.
     */
    @Schema(description = "ID único del profesor", example = "1")
    @JsonProperty("id_professor")
    Long idProfessor,

    /**
     * Nombre del profesor.
     */
    @Schema(description = "Nombre del profesor", example = "Luis Andres")
    @JsonProperty("name")
    String name,

    /**
     * Apellido del profesor.
     */
    @Schema(description = "Apellido del profesor", example = "Rojas Acevedo")
    @JsonProperty("last_name")
    String lastName,

    /**
     * Correo electrónico del profesor.
     */
    @Schema(description = "Correo electrónico del profesor", example = "andres.rojas@universidad.com")
    @JsonProperty("email")
    String email,

    /**
     * Número de teléfono del profesor.
     */
    @Schema(description = "Número de teléfono del profesor", example = "3001234567")
    @JsonProperty("phone")
    String phone,

    /**
     * Dirección del profesor.
     */
    @Schema(description = "Dirección del profesor", example = "Calle 123 #45-67, Barrio Centro, Ciudad")
    @JsonProperty("address")
    String address,

    /**
     * Roles del profesor en el sistema.
     */
    @Schema(description = "Roles del profesor en el sistema", example = "[\"ROLE_PROFESSOR\", \"ROLE_ADMIN\"]")
    Set<String> roles
) {
}
