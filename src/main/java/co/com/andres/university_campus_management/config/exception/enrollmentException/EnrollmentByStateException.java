package co.com.andres.university_campus_management.config.exception.enrollmentException;

/**
 * Excepción lanzada cuando no se encuentran matrículas con un estado específico.
 * 
 * Esta excepción se utiliza para indicar que no existen matrículas en el sistema
 * que coincidan con el estado de matrícula especificado en la búsqueda.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class EnrollmentByStateException extends RuntimeException {
    
    /**
     * Constructor por defecto con mensaje de error.
     */
    public EnrollmentByStateException() {
        super("NO SE ENCONTRARON MATRÍCULAS CON EL ESTADO ESPECIFICADO");
    }
    
    
}
