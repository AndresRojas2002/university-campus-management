package co.com.andres.university_campus_management.model.DTO;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para la creación y actualización de profesores")
public record ProfessorRequest(

    /**
     * Nombre del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Nombre del profesor",
        example = "Luis Andres") 
    @JsonProperty("name")
    @NotBlank(message = "EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String name,

    /**
     * Apellido del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Apellido del profesor", 
        example = "Rojas Acevedo") 
    @JsonProperty("last_name")
    @NotBlank(message = "EL APELLIDO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String lastName,

    /**
     * Correo electrónico del profesor.
     * Campo obligatorio que debe tener formato válido de email.
     */
    @Schema(
        description = "Correo electrónico del profesor", 
        example = "andres.rojas@universidad.com") 
    @JsonProperty("email")
    @NotBlank(message = "EL CORREO ELECTRÓNICO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String email,

    /**
     * Número de teléfono del profesor.
     * Campo opcional para contacto.
     */
    @Schema(
        description = "Número de teléfono del profesor", 
        example = "3001234567") 
    @JsonProperty("phone")
    String phone,

    /**
     * Dirección del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Dirección del profesor", 
        example = "Calle 123 #45-67, Barrio Centro, Ciudad") 
    @JsonProperty("address")
    @NotBlank(message = "LA DIRECCIÓN ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String address,

    // Campo que representa la lista de roles asignados al profesor.
    // Si no se especifica, por defecto se asigna el rol "ROLE_PROFESSOR".
    @Schema(
        description = "Lista de roles asignados al profesor. Ej: [\"ROLE_PROFESSOR\", \"ROLE_ADMIN\"]",
        example = "[\"ROLE_PROFESSOR\"]")
    @JsonProperty("roles")
    Set<String> roles,

    /**
     * Contraseña del profesor para autenticación en el sistema.
     * Campo obligatorio que debe tener al menos 8 caracteres.
     */
    @Schema(
        description = "Contraseña del profesor para autenticación", 
        example = "Profesor2024!") 
    @JsonProperty("password") 
    @NotBlank(message = "LA CONTRASEÑA ES UN CAMPO OBLIGATORIO")
    @Size(min = 8, message = "LA CONTRASEÑA DEBE TENER AL MENOS 8 CARACTERES")
    String password


) {
    /**
     * Constructor compacto del record ProfessorRequest.
     * Se ejecuta automáticamente al crear una nueva instancia del record.
     * 
     * Este constructor se encarga de inicializar el campo phone con un valor por defecto
     * cuando no se proporciona un número de teléfono, asegurando que el campo nunca sea null.
     */
    public ProfessorRequest {
        if (phone == null) {
            phone = "no especificado";
        }
        if (roles == null || roles.isEmpty()) {
            roles = Set.of("ROLE_PROFESSOR"); 
        }
    
    
    }

    /**
    * Valida que los roles enviados sean válidos, ignorando mayúsculas o espacios.
    * Acepta solo los roles: ROLE_PROFESSOR y ROLE_ADMIN.
    *
    * @return true si todos los roles son válidos, false en caso contrario
    */
    public boolean isValidRoles() {
    if (roles == null || roles.isEmpty()) {
        return true; 
    }

    Set<String> rolesValidos = Set.of("ROLE_PROFESSOR", "ROLE_ADMIN");

    return roles.stream()
        .map(rol -> rol.trim().toUpperCase())
        .allMatch(rolesValidos::contains);
}


      /**
     * Valida que el email tenga formato válido de dominio universitario.
     * El email debe terminar en @universidad.com
     * 
     * @return true si el email tiene formato válido, false en caso contrario
     */
    public boolean isValidEmail() {
        return email.matches("^[A-Za-z0-9+_.-]+@universidad\\.com$");
    }

      /**
     * Valida que el número de teléfono tenga formato válido.
     * Acepta números de 7 a 20 dígitos, opcionalmente precedidos por '+'.
     * 
     * @return true si el teléfono tiene formato válido, false en caso contrario
     */
    public boolean isValidPhone() {
        return phone != null && phone.matches("^\\+?[0-9]{7,20}$");
    }

}
