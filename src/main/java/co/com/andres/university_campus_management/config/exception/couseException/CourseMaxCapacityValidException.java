package co.com.andres.university_campus_management.config.exception.couseException;


/**
 * Excepción personalizada que se lanza cuando la capacidad máxima del curso
 * no cumple con los requisitos establecidos.
 * 
 * Esta excepción extiende RuntimeException y se utiliza para validar que la
 * capacidad máxima del curso esté dentro del rango permitido (entre 1 y 50 estudiantes).
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public class CourseMaxCapacityValidException extends RuntimeException {
    
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo sobre el rango válido para la capacidad máxima del curso.
     */
    public CourseMaxCapacityValidException() {
        super("LA CAPACIDAD MÁXIMA DEL CURSO NO ES VÁLIDA. DEBE ESTAR ENTRE 1 Y 50 ESTUDIANTES");
    }
}

