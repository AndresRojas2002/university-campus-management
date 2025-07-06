package co.com.andres.university_campus_management.config.exception.studentException;

/**
 * Excepción personalizada que se lanza cuando el número de estudiante no cumple
 * con el formato requerido de validación.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para validar que el
 * número de estudiante tenga entre 8 y 10 dígitos numéricos como se especifica
 * en los requisitos del sistema.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class StudentWintNumberValidExeption extends RuntimeException{
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el formato requerido para el número de estudiante.
     */
    public StudentWintNumberValidExeption (){
        super("EL NÚMERO DE ESTUDIANTE DEBE TENER ENTRE 8 Y 10 DÍGITOS NUMÉRICOS");
    }
    
}
