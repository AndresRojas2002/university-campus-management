package co.com.andres.university_campus_management.model.DTO;

/**
 * DTO para la respuesta de autenticación.
 * 
 * Este record representa la respuesta que se envía al usuario después de una autenticación exitosa,
 * conteniendo el token JWT generado.
 * 
 * @param jwt Token JWT generado para el usuario autenticado
 */
public record AuthenticateResponse(
        String jwt) {

}
