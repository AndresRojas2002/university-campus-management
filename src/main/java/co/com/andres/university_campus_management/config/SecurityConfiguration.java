package co.com.andres.university_campus_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad para la aplicación de gestión universitaria.
 * 
 * Esta clase configura Spring Security para manejar la autenticación JWT,
 * definir las reglas de autorización para los endpoints y configurar
 * los beans necesarios para el funcionamiento del sistema de seguridad.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    /**
     * Configura la cadena de filtros de seguridad HTTP.
     * 
     * Define las reglas de autorización para los diferentes endpoints:
     * - Permite acceso público a los endpoints de autenticación (/api/auth/**)
     * - Permite acceso público de lectura a los cursos (/api/cursos)
     * - Requiere autenticación para todas las demás solicitudes
     * - Deshabilita CSRF y CORS para simplificar la configuración
     * - Integra el filtro JWT personalizado en la cadena de filtros
     * 
     * @param http Configuración HTTP de Spring Security
     * @param jwtFilter Filtro personalizado para autenticación JWT
     * @return SecurityFilterChain configurado
     * @throws Exception Si ocurre un error en la configuración
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/cursos").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configura el codificador de contraseñas BCrypt.
     * 
     * BCrypt es un algoritmo de hash seguro que se utiliza para
     * encriptar las contraseñas de los usuarios antes de almacenarlas
     * en la base de datos.
     * 
     * @return PasswordEncoder configurado con BCrypt
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el administrador de autenticación.
     * 
     * El AuthenticationManager es responsable de procesar las
     * solicitudes de autenticación y validar las credenciales
     * de los usuarios.
     * 
     * @param config Configuración de autenticación de Spring Security
     * @return AuthenticationManager configurado
     * @throws Exception Si ocurre un error en la configuración
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
