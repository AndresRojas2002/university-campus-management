package co.com.andres.university_campus_management.config;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.andres.university_campus_management.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Filtro de autenticación JWT que procesa tokens de autorización en las solicitudes HTTP.
 * 
 * Este filtro intercepta todas las solicitudes HTTP y verifica si contienen un token JWT válido
 * en el encabezado Authorization. Si el token es válido, establece la autenticación en el contexto
 * de seguridad de Spring.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Utilidad para manejo de tokens JWT.
     */
    private final JwtUtil jwtUtil;

    /**
     * Procesa internamente cada solicitud HTTP para autenticación JWT.
     * 
     * Este método se ejecuta para cada solicitud HTTP y realiza las siguientes operaciones:
     * 1. Extrae el encabezado Authorization de la solicitud
     * 2. Verifica si el encabezado contiene un token Bearer válido
     * 3. Valida el token JWT y extrae la información del usuario
     * 4. Establece la autenticación en el contexto de seguridad si el token es válido
     * 
     * @param request La solicitud HTTP entrante
     * @param response La respuesta HTTP saliente
     * @param filterChain La cadena de filtros para continuar el procesamiento
     * @throws ServletException Si ocurre un error en el servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Extrae el encabezado de autorización de la solicitud
        var authHeader = request.getHeader("Authorization");

        // Si no hay encabezado de autorización o no comienza con "Bearer ", continúa con el filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("No se encontró token Bearer en el encabezado Authorization");
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el token eliminando el prefijo "Bearer "
        var token = authHeader.substring(7);
        var email = jwtUtil.extractUsername(token);

        // Verifica si se pudo extraer el email y no hay autenticación previa en el contexto
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Extrae y mapea los roles del token a autoridades de Spring Security
            var roles = jwtUtil.extractRoles(token).stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            // Valida el token JWT
            if (jwtUtil.validateToken(token, email)) {
                // Crea un objeto User de Spring Security con el email y roles
                var user = new User(email, "", roles);
                
                // Crea el token de autenticación
                var authToken = new UsernamePasswordAuthenticationToken(user, null, roles);
                
                // Establece los detalles de la autenticación
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
                
                log.debug("Autenticación JWT exitosa para el usuario: {}", email);
            } else {
                log.warn("Token JWT inválido para el usuario: {}", email);
            }
        }

        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
