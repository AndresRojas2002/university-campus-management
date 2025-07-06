package co.com.andres.university_campus_management.model.DTO;

import java.util.Set;

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
         * Clave primaria generada automáticamente.
         */
        @Schema(description = "Identificador único del estudiante", example = "1", required = true) 
        Long idStudent,

        /**
         * Nombre del estudiante.
         * Campo obligatorio que identifica al estudiante.
         */
        @Schema(description = "Nombre del estudiante", example = "Luis Andres") 
        String name,

        /**
         * Apellido del estudiante.
         * Campo obligatorio que identifica al estudiante.
         */
        @Schema(description = "Apellido del estudiante", example = "Rojas Acevedo") 
        String lastName,

        /**
         * Correo electrónico del estudiante.
         * Campo obligatorio que debe tener formato válido de email universitario.
         */
        @Schema(description = "Correo electrónico del estudiante", example = "andres.rojas@universidad.com") 
        String email,

        /**
         * Dirección de residencia del estudiante.
         * Campo que proporciona información de ubicación del estudiante.
         */
        @Schema(description = "Dirección de residencia del estudiante", example = "Calle 123 #45-67, Barrio Centro, Ciudad") 
        String address,

        /**
         * Número de teléfono del estudiante.
         * Campo que permite contacto directo con el estudiante.
         */
        @Schema(description = "Número de teléfono del estudiante", example = "3001234567") 
        String phone,

        /**
         * Número de identificación académica del estudiante.
         * Campo único que identifica al estudiante en el sistema académico.
         */
        @Schema(description = "Número de identificación académica del estudiante", example = "2024001234") 
        String studentNumber,
        
        /**
         * Roles asignados al estudiante en el sistema.
         * Colección de roles que define los permisos y accesos
         * que tiene el estudiante en la plataforma.
         */
        @Schema(description = "Roles del estudiante en el sistema", example = "[\"STUDENT\", \"ADMIN\"]") 
        Set<String> roles
        ) {

        



}
