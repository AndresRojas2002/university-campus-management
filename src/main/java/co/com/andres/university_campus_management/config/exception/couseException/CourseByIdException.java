package co.com.andres.university_campus_management.config.exception.couseException;

public class CourseByIdException extends RuntimeException{

    public CourseByIdException(){
        super("EL CURSO CON ESTE ID, NO SE ENCUENTRA REGISTRADO");
    }
    
}
