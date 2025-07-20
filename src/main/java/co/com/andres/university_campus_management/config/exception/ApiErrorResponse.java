package co.com.andres.university_campus_management.config.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Clase que representa la respuesta de error estandarizada para la API.
 * 
 * Esta clase se utiliza para encapsular información detallada sobre errores
 * que ocurren durante el procesamiento de solicitudes HTTP. Proporciona
 * una estructura consistente para el manejo de errores en toda la aplicación.
 * 
 * La clase utiliza anotaciones de Jackson para el formato JSON y Lombok
 * para la generación automática de getters, setters y otros métodos.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Data
public class ApiErrorResponse {

    /**
     * Marca de tiempo que indica cuándo ocurrió el error.
     * Se formatea como string en formato ISO 8601.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Código de estado HTTP que indica el tipo de error.
     * Ejemplos: 400 (Bad Request), 404 (Not Found), 500 (Internal Server Error)
     */
    private Integer status;

    /**
     * Descripción del tipo de error basada en el código de estado HTTP.
     * Ejemplos: "Bad Request", "Not Found", "Internal Server Error"
     */
    private String error;

    /**
     * Mensaje descriptivo y específico del error que ocurrió.
     * Proporciona información útil para el desarrollador o usuario.
     */
    private String message;

    /**
     * Ruta de la solicitud HTTP que causó el error.
     * Útil para identificar el endpoint que generó el problema.
     */
    private String path;

    /**
     * Constructor que inicializa una respuesta de error con la información básica.
     * 
     * @param status Código de estado HTTP que representa el tipo de error
     * @param message Mensaje descriptivo del error específico
     * @param path Ruta de la solicitud que causó el error
     */
    public ApiErrorResponse(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now(); // Obtiene la fecha y hora actual
        this.status = status.value(); // Convierte el enum HttpStatus a su valor numérico
        this.error = status.getReasonPhrase(); // Obtiene la descripción del código de estado HTTP
        this.message = message; // Inicializa el mensaje descriptivo del error
        this.path = path; // Inicializa la ruta de la solicitud que causó el error
    }




    
}
