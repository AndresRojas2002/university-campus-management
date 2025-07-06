package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para la creación y actualización de profesores")
public record ProfessorRequest(

    /**
     * Nombre del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Nombre del profesor",
        example = "Luis Andres", 
        required = true) 
    @JsonProperty("name")
    @NotBlank(message = "EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String name,

    /**
     * Apellido del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Apellido del profesor", 
        example = "Rojas Acevedo", 
        required = true) 
    @JsonProperty("last_name")
    @NotBlank(message = "EL APELLIDO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String lastName,

    /**
     * Correo electrónico del profesor.
     * Campo obligatorio que debe tener formato válido de email.
     */
    @Schema(
        description = "Correo electrónico del profesor", 
        example = "andres.rojas@universidad.edu.co", 
        required = true) 
    @JsonProperty("email")
    @NotBlank(message = "EL CORREO ELECTRÓNICO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String email,

    /**
     * Número de teléfono del profesor.
     * Campo opcional para contacto.
     */
    @Schema(
        description = "Número de teléfono del profesor", 
        example = "3001234567", 
        required = false) 
    @JsonProperty("phone")
    String phone,

    /**
     * Dirección del profesor.
     * Campo obligatorio que no puede estar vacío.
     */
    @Schema(
        description = "Dirección del profesor", 
        example = "Calle 123 #45-67, Barrio Centro, Ciudad", 
        required = true) 
    @JsonProperty("address")
    @NotBlank(message = "LA DIRECCIÓN ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
    String address
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
     * Acepta números de 7 a 15 dígitos, opcionalmente precedidos por '+'.
     * 
     * @return true si el teléfono tiene formato válido, false en caso contrario
     */
    public boolean isValidPhone() {
        return phone != null && phone.matches("^\\+?[0-9]{7,15}$");
    }

}
