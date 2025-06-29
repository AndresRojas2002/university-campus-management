package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) que representa la solicitud de creación o
 * actualización de un estudiante en el sistema de gestión universitaria.
 * 
 * Este record encapsula todos los datos necesarios para registrar un nuevo
 * estudiante o actualizar la información de uno existente, incluyendo validaciones y
 * métodos de utilidad para el procesamiento de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */

 @Schema (description = "DTO para la creacion y actualizacion de estudiantes")
public record StudentRequest(
        /**
         * Nombre del estudiante.
         * Campo obligatorio que no puede estar vacío.
         */
        @Schema(
            description = "Nombre del estudiante",
            example = "Luis Andres", 
            required = true) 
        @JsonProperty("name") 
        @NotBlank(message = "EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
        String name,

        /**
         * Apellido del estudiante.
         * Campo obligatorio que no puede estar vacío.
         */
        @Schema(
            description = "Apellido del estudiante", 
            example = "Rojas Acevedo", 
            required = true) 
        @JsonProperty("last_Name") 
        @NotBlank(message = "EL APELLIDO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO")
        String lastName,

        /**
         * Correo electrónico del estudiante.
         * Campo obligatorio que debe tener formato válido de email universitario.
         */
        @Schema(
            description = "Correo electrónico del estudiante. con formato @universidad.com", 
            example = "andres.rojas@universidad.com", 
            required = true) 
        @JsonProperty("email") 
        @NotBlank(message = "EL CORREO ELECTRÓNICO ES UN CAMPO OBLIGATORIO Y DEBE TENER FORMATO VÁLIDO")
        String email,

        /**
         * Dirección de residencia del estudiante.
         * Campo obligatorio que no puede estar vacío.
         */
        @Schema(
            description = "Dirección de residencia del estudiante", 
            example = "Calle 123 #45-67, Barrio Centro, Ciudad", 
            required = true) 
        @JsonProperty("address") 
        @NotBlank(message = "LA DIRECCIÓN DE RESIDENCIA ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍA")
        String address,

        /**
         * Número de teléfono del estudiante.
         * Campo opcional para contacto directo.
         */
        @Schema(
            description = "Número de teléfono del estudiante", 
            example = "3001234567", 
            required = false) 
        @JsonProperty("phone")
        String phone,

        /**
         * Número de estudiante asignado por la universidad.
         * Campo obligatorio que debe tener entre 8 y 10 dígitos numéricos.
         */
        @Schema(
            description = "Número del estudiante", 
            example = "2024001234", 
            required = true) 
        @JsonProperty("student_Number") 
        @NotBlank(message = "EL NÚMERO DE ESTUDIANTE ES UN CAMPO OBLIGATORIO Y DEBE TENER ENTRE 8 Y 10 DÍGITOS")
        String studentNumber

) {

    /**
     * Constructor compacto que asigna valores por defecto cuando phone es null.
     * Garantiza que todos los campos opcionales tengan valores válidos.
     */
    public StudentRequest {
        if (phone == null) {
            phone = "No especificado";
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

    /**
     * Valida que el número de estudiante tenga formato válido.
     * Debe contener entre 8 y 10 dígitos numéricos.
     * 
     * @return true si el número de estudiante tiene formato válido, false en caso
     *         contrario
     */
    public boolean isValidStudentNumber() {
        return studentNumber != null && studentNumber.matches("^[0-9]{8,10}$");
    }

    /**
     * Valida que todos los campos requeridos estén presentes y no estén vacíos.
     * Verifica que name, lastName, email, address, phone y studentNumber
     * tengan valores válidos.
     * 
     * @return true si todos los campos son válidos, false en caso contrario
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
                lastName != null && !lastName.trim().isEmpty() &&
                email != null && !email.trim().isEmpty() &&
                address != null && !address.trim().isEmpty() &&
                phone != null && !phone.trim().isEmpty() &&
                studentNumber != null && !studentNumber.trim().isEmpty();
    }

    /**
     * Obtiene el nombre completo del estudiante con formato adecuado.
     * Elimina espacios extra y concatena nombre y apellido correctamente.
     * 
     * @return String con el nombre completo formateado
     */
    public String getFullName() {
        return String.format("%s %s", name, lastName).trim();
    }

    /**
     * Genera un resumen de la información del estudiante.
     * Incluye nombre completo, número de estudiante y email.
     * 
     * @return String con la información resumida del estudiante
     */
    public String getStudentSummary() {
        return String.format("Estudiante: %s - Número: %s - Email: %s",
                getFullName(), studentNumber, email);
    }

}
