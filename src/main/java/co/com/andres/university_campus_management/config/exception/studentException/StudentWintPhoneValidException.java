package co.com.andres.university_campus_management.config.exception.studentException;

/**
 * Excepción personalizada que se lanza cuando el número de teléfono no cumple
 * con el formato requerido de validación.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para validar que el
 * número de teléfono tenga entre 7 y 15 dígitos numéricos, opcionalmente
 * precedidos por el símbolo '+' para números internacionales.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentWintPhoneValidException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el formato requerido para el número de teléfono.
     */
    public StudentWintPhoneValidException (){
        super("EL NÚMERO DE TELÉFONO DEBE TENER FORMATO VÁLIDO (7-15 DÍGITOS)");
    }
    
}
