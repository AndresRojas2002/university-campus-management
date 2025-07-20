package co.com.andres.university_campus_management.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.config.exception.authenticate.InvalidCredentialsProfessorException;
import co.com.andres.university_campus_management.config.exception.authenticate.InvalidCredentialsStudentException;
import co.com.andres.university_campus_management.model.DTO.AuthenticateRequest;
import co.com.andres.university_campus_management.model.DTO.AuthenticateResponse;
import co.com.andres.university_campus_management.repository.ProfessorRepository;
import co.com.andres.university_campus_management.repository.StudentRepository;
import co.com.andres.university_campus_management.service.AuthenticateService;
import co.com.andres.university_campus_management.utils.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    // Repositorio para estudiantes
    private final StudentRepository estudianteRepository;
    // Repositorio para profesores
    private final ProfessorRepository profesorRepository;
    // Codificador de contraseñas
    private final PasswordEncoder passwordEncoder;
    // Utilidad para manejo de JWT
    private final JwtUtil jwtUtil;

    /**
     * Realiza el proceso de login para estudiantes.
     * Si no se encuentra el usuario o la contraseña es incorrecta, lanza una excepción.
     */
    public AuthenticateResponse logginStudent(AuthenticateRequest request) {
        return autenticarStudent(request)
            .orElseThrow(() -> new InvalidCredentialsStudentException());
    }

    /**
     * Realiza el proceso de login para profesores.
     * Si no se encuentra el usuario o la contraseña es incorrecta, lanza una excepción.
     */
    public AuthenticateResponse logginProfessor(AuthenticateRequest request) {
        return autenticarProfessor(request)
            .orElseThrow(() -> new InvalidCredentialsProfessorException());
    }

    /**
     * Intenta autenticar al usuario como estudiante.
     * @param request Solicitud de autenticación
     * @return Optional con AuthenticateResponse si la autenticación es exitosa
     */
    private Optional<AuthenticateResponse> autenticarStudent(AuthenticateRequest request) {
        // Busca el estudiante por email y verifica la contraseña
        return estudianteRepository.findByEmail(request.email())
            .filter(stud -> passwordEncoder.matches(request.password(), stud.getPassword()))
            .map(stud -> generateToken(stud.getEmail(), stud.getRoles()));
    }

    /**
     * Intenta autenticar al usuario como profesor.
     * @param request Solicitud de autenticación
     * @return Optional con AuthenticateResponse si la autenticación es exitosa
     */
    private  Optional<AuthenticateResponse> autenticarProfessor(AuthenticateRequest request) {
        // Busca el profesor por email y verifica la contraseña
        return profesorRepository.findByEmail(request.email())
            .filter(prof -> passwordEncoder.matches(request.password(), prof.getPassword()))
            .map(prof -> generateToken(prof.getEmail(), prof.getRoles()));
    }

    /**
     * Genera el token JWT y construye la respuesta de autenticación.
     * @param email Email del usuario autenticado
     * @param roles Roles del usuario
     * @return AuthenticateResponse con el token JWT generado
     */
    private AuthenticateResponse generateToken(String email, Set<String> roles) {
        String token = jwtUtil.generateToken(email, roles);
        return new AuthenticateResponse(token);
    }
}
