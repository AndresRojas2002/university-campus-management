package co.com.andres.university_campus_management.config.exception.enrollmentException;

/**
 * Excepción personalizada que se lanza cuando se intenta crear una matrícula
 * con un ID de estudiante inválido o inexistente.
 * 
 * Esta excepción extiende de RuntimeException para indicar un error en tiempo
 * de ejecución relacionado con la validación de datos de entrada.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class EnrollmentWithIdSudentValidException extends RuntimeException {
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error.
     */
    public EnrollmentWithIdSudentValidException() {
        super("NO SE PUEDE CREAR UNA MATRÍCULA CON UN ID DE ESTUDIANTE INVÁLIDO");
    }
}
    
