package co.com.andres.university_campus_management.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.andres.university_campus_management.config.exception.couseException.CourseByIdException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseCodeValidException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseMaxCapacityValidException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseWithCodeExistException;
import co.com.andres.university_campus_management.config.exception.studentException.EmailValidException;
import co.com.andres.university_campus_management.config.exception.studentException.NumberValidExeption;
import co.com.andres.university_campus_management.config.exception.studentException.PhoneValidException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentByIdException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentNumberExistException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentWithEmailExistException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Manejador global de excepciones para la aplicación de gestión universitaria.
 * 
 * Esta clase centraliza el manejo de todas las excepciones personalizadas y excepciones
 * del sistema, proporcionando respuestas HTTP consistentes y estructuradas.
 * 
 * Utiliza la anotación @ControllerAdvice para interceptar excepciones en toda la aplicación
 * y devolver respuestas JSON estandarizadas con códigos de estado HTTP apropiados.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@ControllerAdvice
public class GlobalExeptionHandler {

    /**
     * Maneja excepciones de validación de formato de email.
     * 
     * Se activa cuando el email proporcionado no cumple con el formato requerido
     * de dominio universitario (@universidad.com).
     * 
     * @param ex La excepción EmailValidException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 400 (BAD_REQUEST) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> HandlerEmailValidException(EmailValidException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones de validación de formato de número de estudiante.
     * 
     * Se activa cuando el número de estudiante no cumple con el formato requerido
     * (entre 8 y 10 dígitos numéricos).
     * 
     * @param ex La excepción NumberValidExeption capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 400 (BAD_REQUEST) y detalles del error
     */
    @ExceptionHandler 
    public ResponseEntity <ApiErrorResponse> HandlerNumberValidException(NumberValidExeption ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),request.getRequestURI()));
    }

    /**
     * Maneja excepciones de validación de formato de teléfono.
     * 
     * Se activa cuando el número de teléfono no cumple con el formato requerido
     * (7-15 dígitos, opcionalmente precedidos por '+').
     * 
     * @param ex La excepción PhoneValidException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 400 (BAD_REQUEST) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> HandlerPhoneValidException(PhoneValidException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones cuando no se encuentra un estudiante por ID.
     * 
     * Se activa cuando se intenta acceder, actualizar o eliminar un estudiante
     * con un ID que no existe en la base de datos.
     * 
     * @param ex La excepción StudentByIdException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 404 (NOT_FOUND) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> HandlerStudentByIdException(StudentByIdException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones cuando ya existe un estudiante con el mismo número.
     * 
     * Se activa cuando se intenta crear o actualizar un estudiante con un número
     * de estudiante que ya está registrado en el sistema.
     * 
     * @param ex La excepción StudentNumberExistException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 409 (CONFLICT) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> HandlerStudentNumberExistException(StudentNumberExistException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones cuando ya existe un estudiante con el mismo email.
     * 
     * Se activa cuando se intenta crear o actualizar un estudiante con un email
     * que ya está registrado en el sistema.
     * 
     * @param ex La excepción StudentWithEmailExistException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 409 (CONFLICT) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> HandlerStudentWithEmailExistException(StudentWithEmailExistException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones RuntimeException no capturadas específicamente.
     * 
     * Captura cualquier RuntimeException que no haya sido manejada por los
     * handlers específicos anteriores. Sirve como fallback para errores
     * de tiempo de ejecución no anticipados.
     * 
     * @param ex La excepción RuntimeException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 500 (INTERNAL_SERVER_ERROR) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerRuntimeExeption(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones Exception generales no capturadas.
     * 
     * Captura cualquier excepción que no haya sido manejada por los handlers
     * específicos anteriores. Sirve como último recurso para garantizar que
     * todas las excepciones sean manejadas y se devuelva una respuesta estructurada.
     * 
     * @param ex La excepción Exception capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 500 (INTERNAL_SERVER_ERROR) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerExeption(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones cuando no se encuentra un curso por ID.
     * 
     * Se activa cuando se intenta acceder a un curso con un ID que no existe
     * en el sistema.
     * 
     * @param ex La excepción CourseByIdException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 404 (NOT_FOUND) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerCourseByIdException(CourseByIdException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones cuando ya existe un curso con el mismo código.
     * 
     * Se activa cuando se intenta crear o actualizar un curso con un código
     * que ya está registrado en el sistema.
     * 
     * @param ex La excepción CourseWithCodeExistException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 409 (CONFLICT) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerCourseWithCodeExistException(CourseWithCodeExistException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones de validación del código del curso.
     * 
     * Se activa cuando el código del curso no cumple con el formato requerido
     * (3-4 letras mayúsculas seguido de un guión y 3 dígitos).
     * 
     * @param ex La excepción CourseCodeValidException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 400 (BAD_REQUEST) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerCourseCodeValidException(CourseCodeValidException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    /**
     * Maneja excepciones de validación de la capacidad máxima del curso.
     * 
     * Se activa cuando la capacidad máxima del curso no está dentro del rango
     * permitido (entre 1 y 50 estudiantes).
     * 
     * @param ex La excepción CourseMaxCapacityValidException capturada
     * @param request La solicitud HTTP que generó la excepción
     * @return ResponseEntity con código 400 (BAD_REQUEST) y detalles del error
     */
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlerCourseMaxCapacityValidException(CourseMaxCapacityValidException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }
}
