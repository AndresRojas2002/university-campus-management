package co.com.andres.university_campus_management.model.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateRequest(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password) {

}
