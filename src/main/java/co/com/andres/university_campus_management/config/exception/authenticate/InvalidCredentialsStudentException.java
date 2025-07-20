package co.com.andres.university_campus_management.config.exception.authenticate;

public class InvalidCredentialsStudentException extends RuntimeException {
    /**
     * Constructor por defecto con mensaje predefinido en mayúsculas.
     * Se utiliza cuando las credenciales del estudiante son incorrectas.
     */
    public InvalidCredentialsStudentException() {
        super("EL CORREO ELECTRÓNICO O LA CONTRASEÑA DEL ESTUDIANTE SON INCORRECTOS");
    }
}
