package co.com.andres.university_campus_management.config.exception.studentException;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar un estudiante
 * con un correo electrónico que ya existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para manejar casos donde
 * se viola la restricción de unicidad del correo electrónico en la base de datos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentWithEmailExistException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el error de duplicación de correo electrónico.
     */
    public StudentWithEmailExistException() {
        super("ESTE CORREO ELECTRONICO YA SE ENCUENTRA REGISTRADO");
    }

}
