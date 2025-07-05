package co.com.andres.university_campus_management.config.exception.studentException;

/**
 * Excepción personalizada que se lanza cuando el correo electrónico proporcionado
 * no cumple con el formato requerido de dominio universitario (@universidad.com).
 * 
 * Esta excepción extiende RuntimeException y se utiliza para validar que el
 * correo electrónico del estudiante termine correctamente según las políticas
 * de la universidad.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentWintEmailValidException extends RuntimeException{
  
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el formato requerido para el correo electrónico.
     */
    public StudentWintEmailValidException() {
        super("EL CORREO ELECTRÓNICO NO ES VÁLIDO. DEBE TERMINAR EN @universidad.com");
    }
}
