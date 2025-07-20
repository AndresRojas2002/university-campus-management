package co.com.andres.university_campus_management.config.exception.professorException;

/**
 * Excepción personalizada que se lanza cuando se intenta asignar roles inválidos
 * (ROLE_PROFESSOR y ROLE_ADMIN) a los profesores en el sistema.
 * 
 * Esta excepción se utiliza para validar que solo se asignen roles válidos
 * según las reglas de negocio del sistema de gestión universitaria.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class ProfessorWithRoleValidException extends RuntimeException {

    /**
     * Constructor que establece el mensaje de error específico para roles inválidos.
     */
    public ProfessorWithRoleValidException() {
        super("LOS ROLES ASIGNADOS AL PROFESOR NO SON VÁLIDOS. SOLO SE PERMITEN: ROLE_PROFESSOR Y ROLE_ADMIN");
    }
}
