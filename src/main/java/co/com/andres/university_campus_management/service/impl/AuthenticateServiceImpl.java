package co.com.andres.university_campus_management.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final StudentRepository estudianteRepository;
    private final ProfessorRepository profesorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthenticateResponse loggin(AuthenticateRequest request) {
        return autenticarEstudiante(request)
            .or(() -> autenticarProfesor(request))
            .orElseThrow(() -> new BadCredentialsException("Usuario o contraseña inválidos"));
    }

    private Optional<AuthenticateResponse> autenticarEstudiante(AuthenticateRequest request) {
        return estudianteRepository.findByEmail(request.email())
            .filter(stud -> passwordEncoder.matches(request.password(), stud.getPassword()))
            .map(stud -> generarToken(stud.getEmail(), stud.getRoles()));
    }

    private Optional<AuthenticateResponse> autenticarProfesor(AuthenticateRequest request) {
        return profesorRepository.findByEmail(request.email())
            .filter(prof -> passwordEncoder.matches(request.password(), prof.getPassword()))
            .map(prof -> generarToken(prof.getEmail(), prof.getRoles()));
    }

    private AuthenticateResponse generarToken(String email, Set<String> roles) {
        String token = jwtUtil.generateToken(email, roles);
        return new AuthenticateResponse(token);
    }
}



    
