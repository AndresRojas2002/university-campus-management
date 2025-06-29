package co.com.andres.university_campus_management.config.exception;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar un estudiante
 * con un número de estudiante que ya existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para manejar casos donde
 * se viola la restricción de unicidad del número de estudiante en la base de datos,
 * garantizando que cada estudiante tenga un número único en el sistema.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentNumberExistException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el error de duplicación del número de estudiante.
     */
    public StudentNumberExistException() {
        super("ESTE NUMERO DE ESTUDIANTE YA SE ENCUENTRA REGISTRADO");
    }

}

