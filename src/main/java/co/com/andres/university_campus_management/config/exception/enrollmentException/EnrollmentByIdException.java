package co.com.andres.university_campus_management.config.exception.enrollmentException;

/**
 * Excepción personalizada que se lanza cuando se intenta buscar una matrícula
 * con un ID que no existe en la base de datos.
 * 
 * Esta excepción extiende de RuntimeException para indicar un error en tiempo
 * de ejecución relacionado con la búsqueda de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class EnrollmentByIdException extends RuntimeException {
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error.
     */
    public EnrollmentByIdException() {
        super("NO SE ENCONTRÓ UNA MATRÍCULA CON EL ID ESPECIFICADO");
    }
}
