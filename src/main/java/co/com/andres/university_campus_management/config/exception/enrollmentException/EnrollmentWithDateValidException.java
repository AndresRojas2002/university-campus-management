package co.com.andres.university_campus_management.config.exception.enrollmentException;

/**
 * Excepción personalizada que se lanza cuando se intenta crear una matrícula
 * con una fecha de matrícula inválida.
 * 
 * Esta excepción extiende de RuntimeException para indicar un error en tiempo
 * de ejecución relacionado con la validación de datos de entrada.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class EnrollmentWithDateValidException extends RuntimeException {
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error.
     */
    public EnrollmentWithDateValidException() {
        super("NO SE PUEDE CREAR UNA MATRÍCULA CON UNA FECHA INVÁLIDA");
    }
}
    

