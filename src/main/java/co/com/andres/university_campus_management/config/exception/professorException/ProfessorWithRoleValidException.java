package co.com.andres.university_campus_management.config.exception.professorException;

/**
 * Excepción personalizada que se lanza cuando los roles asignados a un profesor
 * no son válidos según las reglas de negocio del sistema.
 * 
 * Esta excepción se utiliza para validar que solo se asignen roles permitidos
 * (ROLE_PROFESOR y ROLE_ADMIN) a los profesores en el sistema.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class ProfessorWithRoleValidException extends RuntimeException {
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre los roles válidos permitidos.
     */
    public ProfessorWithRoleValidException() {
        super("LOS ROLES ASIGNADOS AL PROFESOR NO SON VÁLIDOS. SOLO SE PERMITEN: ROLE_PROFESOR Y ROLE_ADMIN");
    }
    
}
