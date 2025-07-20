package co.com.andres.university_campus_management.config.exception.professorException;

public class CourseWithIdProfessorValidException  extends RuntimeException{
    /**
     * Constructor por defecto que inicializa la excepción con un mensaje
     * descriptivo del error relacionado con el ID del profesor.
     */
    public CourseWithIdProfessorValidException() {
        super("NO SE PUEDE CREAR O ACTUALIZAR UN CURSO CON UN ID DE PROFESOR INVÁLIDO");
    }
}
