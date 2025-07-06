package co.com.andres.university_campus_management.config.exception.authenticate;

/**
 * Excepción personalizada que se lanza cuando las credenciales de autenticación
 * (correo electrónico o contraseña) son incorrectas durante el proceso de login.
 * 
 * Esta excepción extiende RuntimeException para permitir un manejo más flexible
 * de errores de autenticación sin necesidad de declarar throws en los métodos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class PasswordOrEmailException extends RuntimeException {
    
    /**
     * Constructor por defecto con mensaje predefinido en mayúsculas.
     * Se utiliza cuando no se requiere especificar un mensaje personalizado.
     */
    public PasswordOrEmailException() {
        super("EL CORREO ELECTRÓNICO O LA CONTRASEÑA SON INCORRECTOS");
    }
    
}
