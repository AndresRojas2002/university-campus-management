package co.com.andres.university_campus_management.model.DTO;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) que representa la respuesta de información
 * de un estudiante en el sistema de gestión universitaria.
 * 
 * Este record encapsula todos los datos necesarios para devolver la información
 * completa de un estudiante, incluyendo su identificador único, datos personales
 * y académicos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Schema(description = "DTO para la respuesta de información de estudiantes")
public record StudentResponse(

        /**
         * Identificador único del estudiante.
         */
        @Schema(description = "ID único del estudiante", example = "1")
        @JsonProperty("id_student")
        Long idStudent,

        /**
         * Nombre del estudiante.
         */
        @Schema(description = "Nombre del estudiante", example = "Luis Andres")
        @JsonProperty("name")
        String name,

        /**
         * Apellido del estudiante.
         */
        @Schema(description = "Apellido del estudiante", example = "Rojas Acevedo")
        @JsonProperty("last_name")
        String lastName,

        /**
         * Correo electrónico del estudiante.
         */
        @Schema(description = "Correo electrónico del estudiante", example = "andres.rojas@universidad.com")
        @JsonProperty("email")
        String email,

        /**
         * Dirección de residencia del estudiante.
         */
        @Schema(description = "Dirección de residencia del estudiante", example = "Calle 123 #45-67, Barrio Centro, Ciudad")
        @JsonProperty("address")
        String address,

        /**
         * Número de teléfono del estudiante.
         */
        @Schema(description = "Número de teléfono del estudiante", example = "3001234567")
        @JsonProperty("phone")
        String phone,

        /**
         * Número de identificación académica del estudiante.
         */
        @Schema(description = "Número de identificación académica del estudiante", example = "2024001234")
        @JsonProperty("student_number")
        String studentNumber,
        
        /**
         * Roles asignados al estudiante en el sistema.
         */
        @Schema(description = "Roles del estudiante en el sistema", example = "[\"ROLE_STUDENT\"]")
        @JsonProperty("roles")
        Set<String> roles
        ) {

        



}
