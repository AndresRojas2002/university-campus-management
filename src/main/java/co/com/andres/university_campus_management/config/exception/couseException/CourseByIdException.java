package co.com.andres.university_campus_management.config.exception.couseException;

/**
 * Excepción personalizada que se lanza cuando se intenta acceder a un curso
 * con un ID que no existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para manejar casos donde
 * se solicita información de un curso que no se encuentra registrado en la base de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class CourseByIdException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el error de curso no encontrado.
     */
    public CourseByIdException() {
        super("EL CURSO CON ESTE ID, NO SE ENCUENTRA REGISTRADO");
    }

}
