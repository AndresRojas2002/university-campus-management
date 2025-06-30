package co.com.andres.university_campus_management.config.exception;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un estudiante
 * con el ID especificado en el sistema.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para manejar casos donde
 * se intenta acceder, actualizar o eliminar un estudiante que no existe en la
 * base de datos con el identificador proporcionado.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentByIdException  extends RuntimeException{
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre la no existencia del estudiante con el ID especificado.
     */
    public StudentByIdException(){
        super("ESTUDIANTE CON ESE ID, NO ENCONTRADO");
    }
}
