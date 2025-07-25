package co.com.andres.university_campus_management.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.AuthenticateRequest;
import co.com.andres.university_campus_management.model.DTO.AuthenticateResponse;
import co.com.andres.university_campus_management.service.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para la autenticación de usuarios.
 * 
 * Este controlador maneja las solicitudes de autenticación para estudiantes y
 * profesores,
 * proporcionando un endpoint para el inicio de sesión que genera tokens JWT.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@RestController
@RequiredArgsConstructor
public class AuthenticateController {

    /**
     * Servicio de autenticación para manejar la lógica de negocio.
     */
    private final AuthenticateService authenticateService;

    /**
     * Endpoint para autenticar estudiantes.
     * 
     * Este método recibe las credenciales del estudiante y devuelve un token JWT
     * si la autenticación es exitosa. El token contiene información del usuario
     * y sus roles para autorización posterior.
     * 
     * @param body Solicitud de autenticación con email y contraseña
     * @return Respuesta con el token JWT generado
     * @throws BadCredentialsException Si las credenciales son inválidas
     */
    @Operation(summary = "Autenticar estudiante", description = "Recibe las credenciales del estudiante y retorna un token JWT si la autenticación es exitosa.")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(schema = @Schema(implementation = AuthenticateResponse.class)))
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/authenticate/estudiante")
    public AuthenticateResponse authenticateStudent(@Valid @RequestBody AuthenticateRequest body) {
        return authenticateService.logginStudent(body);
    }

    /**
     * Endpoint para autenticar profesores.
     * 
     * Este método recibe las credenciales del profesor y devuelve un token JWT
     * si la autenticación es exitosa. El token contiene información del usuario
     * y sus roles para autorización posterior.
     * 
     * @param body Solicitud de autenticación con email y contraseña
     * @return Respuesta con el token JWT generado
     * @throws BadCredentialsException Si las credenciales son inválidas
     */
    @Operation(summary = "Autenticar profesor", description = "Recibe las credenciales del profesor y retorna un token JWT si la autenticación es exitosa.")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa", content = @Content(schema = @Schema(implementation = AuthenticateResponse.class)))
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/authenticate/profesor")
    public AuthenticateResponse authenticateProfessor(@Valid @RequestBody AuthenticateRequest body) {
        return authenticateService.logginProfessor(body);
    }
}
