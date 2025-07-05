package co.com.andres.university_campus_management.config.exception.professorException;

/**
 * Excepción personalizada que se lanza cuando se intenta crear o actualizar
 * un profesor con un teléfono que ya existe en el sistema.
 * 
 * Esta excepción extiende RuntimeException para indicar que es una excepción
 * no verificada que puede ser manejada de forma opcional.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class ProfessorWithPhoneValidException extends RuntimeException {

    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error.
     */
    public ProfessorWithPhoneValidException() {
        super("EL PROFESOR CON ESTE TELÉFONO YA EXISTE EN EL SISTEMA");
    }
    
}
