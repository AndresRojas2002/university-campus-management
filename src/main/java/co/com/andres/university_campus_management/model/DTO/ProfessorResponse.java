package co.com.andres.university_campus_management.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para la respuesta de profesores")
public record ProfessorResponse(

    /**
     * Identificador único del profesor.
     * Clave primaria generada automáticamente.
     */
    @Schema(description = "Identificador único del profesor", example = "1", required = true) 
    Long idProfessor,

    /**
     * Nombre del profesor.
     * Campo obligatorio que identifica al profesor.
     */
    @Schema(description = "Nombre del profesor", example = "Luis Andres") 
    String name,

    /**
     * Apellido del profesor.
     * Campo obligatorio que identifica al profesor.
     */
    @Schema(description = "Apellido del profesor", example = "Rojas Acevedo") 
    String lastName,

    /**
     * Correo electrónico del profesor.
     * Campo obligatorio que debe tener formato válido de email.
     */
    @Schema(description = "Correo electrónico del profesor", example = "andres.rojas@universidad.edu.co") 
    String email,

    /**
     * Número de teléfono del profesor.
     * Campo que permite contacto directo con el profesor.
     */
    @Schema(description = "Número de teléfono del profesor", example = "3001234567") 
    String phone,

    /**
     * Dirección del profesor.
     * Campo que proporciona información de ubicación del profesor.
     */
    @Schema(description = "Dirección del profesor", example = "Calle 123 #45-67, Barrio Centro, Ciudad") 
    String address) {







}
