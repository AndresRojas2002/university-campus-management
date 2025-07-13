package co.com.andres.university_campus_management.model.DTO;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para la solicitud de autenticación.
 * 
 * Este record representa los datos necesarios para autenticar a un usuario,
 * incluyendo el email y la contraseña. Ambos campos son obligatorios.
 * 
 * @param email    Correo electrónico del usuario (obligatorio)
 * @param password Contraseña del usuario (obligatoria)
 */
public record AuthenticateRequest(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password) {

}
