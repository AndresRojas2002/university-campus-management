package co.com.andres.university_campus_management.utils;

import java.util.*;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Utilidad para el manejo de tokens JWT (JSON Web Tokens).
 * 
 * Esta clase proporciona métodos para generar, validar y extraer información
 * de tokens JWT utilizados para la autenticación en la aplicación.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Component
public class JwtUtil {

    /**
     * Clave secreta para firmar y verificar tokens JWT.
     * Se obtiene desde la configuración de la aplicación.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tiempo de expiración del token en milisegundos.
     * Se obtiene desde la configuración de la aplicación.
     */
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Genera un token JWT para un usuario con sus roles.
     * 
     * @param email Correo electrónico del usuario (subject del token)
     * @param roles Conjunto de roles del usuario
     * @return Token JWT generado como String
     */
    public String generateToken(String email, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream()
        .map(String::toUpperCase)
        .toList());
        return createToken(claims, email);
    }

    /**
     * Extrae el email del token JWT.
     * 
     * @param token Token JWT del cual extraer el usuario
     * @return Email del usuario o null si no se puede extraer
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae los roles del usuario desde el token JWT.
     * 
     * @param token Token JWT del cual extraer los roles
     * @return Lista de roles del usuario, lista vacía si no se encuentran
     */
    public List<String> extractRoles(String token) {
        Object roles = extractAllClaims(token).get("roles");
        if (roles instanceof List<?> lista) {
            return lista.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .toList();
        }
        return List.of();
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     * 
     * @param token Token JWT del cual extraer la fecha de expiración
     * @return Fecha de expiración del token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Valida si un token JWT es válido para un usuario específico.
     * 
     * @param token Token JWT a validar
     * @param email Email del usuario para validar
     * @return true si el token es válido, false en caso contrario
     */
    public boolean validateToken(String token, String email) {
        final var emails = extractUsername(token);
        return (emails.equals(email))&& !isTokenExpired(token);
    }

    /**
     * Verifica si un token JWT ha expirado.
     * 
     * @param token Token JWT a verificar
     * @return true si el token ha expirado, false en caso contrario
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae un claim específico del token JWT usando una función de resolución.
     * 
     * @param <T> Tipo de dato del claim a extraer
     * @param token Token JWT del cual extraer el claim
     * @param resolver Función para resolver el claim específico
     * @return Valor del claim extraído
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final var claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extrae todos los claims del token JWT.
     * 
     * @param token Token JWT del cual extraer los claims
     * @return Objeto Claims con todos los claims del token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .verifyWith(getSignInKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    /**
     * Obtiene la clave secreta para firmar y verificar tokens.
     * 
     * @return SecretKey generada a partir de la clave secreta configurada
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Crea un token JWT con los claims y subject especificados.
     * 
     * @param claims Claims adicionales a incluir en el token
     * @param subject Subject del token (generalmente el email del usuario)
     * @return Token JWT generado como String
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .claims(claims)
                   .subject(subject)
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + expiration))
                   .signWith(getSignInKey())
                   .compact();
    }
}
