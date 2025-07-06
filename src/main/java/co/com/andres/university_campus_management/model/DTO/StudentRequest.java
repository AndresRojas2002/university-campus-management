package co.com.andres.university_campus_management.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        @JsonProperty("last_name") 
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
        String studentNumber,

        /**
         * Estado de la matrícula del estudiante.
         * Campo obligatorio que define el estado actual de la matrícula.
         */
        @Schema(
            description = "Estado de la matrícula del estudiante", 
            example = "ACTIVE", 
            required = true) 
        @JsonProperty("enrollment_state") 
        EnrollmentState enrollmentState,

        /**
         * Contraseña del estudiante para autenticación en el sistema.
         * Campo obligatorio que debe tener al menos 8 caracteres.
         */
        @Schema(
            description = "Contraseña del estudiante para autenticación", 
            example = "Estudiante2024!", 
            required = true) 
        @JsonProperty("password") 
        @NotBlank(message = "LA CONTRASEÑA ES UN CAMPO OBLIGATORIO")
        @Size(min = 8, message = "LA CONTRASEÑA DEBE TENER AL MENOS 8 CARACTERES")
        String password

) {

    /**
     * Constructor compacto que asigna valores por defecto cuando phone es null.
     * Garantiza que todos los campos opcionales tengan valores válidos.
     */
    public StudentRequest {
        if (phone == null) {
            phone = "No especificado";
        }
        if (enrollmentState == null) {
            enrollmentState = EnrollmentState.ACTIVE;
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
     * Acepta números de 7 a 20 dígitos, opcionalmente precedidos por '+'.
     * 
     * @return true si el teléfono tiene formato válido, false en caso contrario
     */
    public boolean isValidPhone() {
        return phone != null && phone.matches("^\\+?[0-9]{7,20}$");
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


}
