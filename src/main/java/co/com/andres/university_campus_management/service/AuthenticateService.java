package co.com.andres.university_campus_management.service;

import co.com.andres.university_campus_management.model.DTO.AuthenticateRequest;
import co.com.andres.university_campus_management.model.DTO.AuthenticateResponse;

/**
 * Servicio para la autenticación de usuarios.
 * 
 * Esta interfaz define el contrato para los servicios de autenticación,
 * permitiendo implementar la lógica de login para estudiantes y profesores.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public interface AuthenticateService {
    
    /**
     * Realiza el proceso de login para estudiantes.
     * 
     * @param request Solicitud de autenticación con email y contraseña
     * @return Respuesta con el token JWT generado
     */
    AuthenticateResponse logginStudent(AuthenticateRequest request);

    /**
     * Realiza el proceso de login para profesores.
     * 
     * @param request Solicitud de autenticación con email y contraseña
     * @return Respuesta con el token JWT generado
     */
    AuthenticateResponse logginProfessor(AuthenticateRequest request);

   
    
}
