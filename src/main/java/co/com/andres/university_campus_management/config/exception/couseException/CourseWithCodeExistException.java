package co.com.andres.university_campus_management.config.exception.couseException;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar un curso
 * con un código que ya existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para manejar casos donde
 * se viola la restricción de unicidad del código del curso en la base de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class CourseWithCodeExistException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el error de duplicación del código del curso.
     */
    public CourseWithCodeExistException() {
        super("ESTE CÓDIGO DE CURSO YA SE ENCUENTRA REGISTRADO");
    }

}
