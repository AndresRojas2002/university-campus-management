package co.com.andres.university_campus_management.config.exception.professorException;

/**
 * Excepción personalizada que se lanza cuando se intenta buscar, actualizar o eliminar
 * un profesor con un ID que no existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException para indicar que es una excepción
 * no verificada que puede ser manejada de forma opcional.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class ProfessorByIdNotExistException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error.
     */
    public ProfessorByIdNotExistException() {
        super("EL PROFESOR CON ESTE ID NO EXISTE EN EL SISTEMA");
    }
    
}
    

