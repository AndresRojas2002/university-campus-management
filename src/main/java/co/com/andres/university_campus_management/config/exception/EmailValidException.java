package co.com.andres.university_campus_management.config.exception;

/**
 * Excepción personalizada que se lanza cuando el correo electrónico no cumple
 * con el formato requerido de validación para el dominio universitario.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para validar que el
 * correo electrónico tenga el formato correcto y termine con el dominio
 * específico de la universidad (@universidad.com).
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class EmailValidException  extends RuntimeException{

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el formato requerido para el correo electrónico.
     */
    public EmailValidException(){
        super("EL CORREO ELECTRÓNICO DEBE TENER FORMATO VÁLIDO DE DOMINIO UNIVERSITARIO (@universidad.com)");
    }
    
}
