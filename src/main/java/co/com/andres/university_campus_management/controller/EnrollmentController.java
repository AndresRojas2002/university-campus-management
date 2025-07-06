package co.com.andres.university_campus_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.EnrollmentRequest;
import co.com.andres.university_campus_management.model.DTO.EnrollmentResponse;
import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import co.com.andres.university_campus_management.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para la gestión de matrículas en el sistema universitario.
 * 
 * Esta clase proporciona endpoints HTTP para realizar operaciones CRUD
 * completas sobre la entidad Enrollment, incluyendo creación, lectura, actualización y
 * eliminación de matrículas, así como búsquedas personalizadas.
 * 
 * Los endpoints están documentados con Swagger/OpenAPI y manejan validaciones
 * de entrada, códigos de estado HTTP apropiados y respuestas estructuradas.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "API para la gestión de matrículas universitarias")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * Crea una nueva matrícula en el sistema.
     * 
     * @param enrollmentRequest Datos de la matrícula a crear
     * @return EnrollmentResponse con la información de la matrícula creada
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear matrícula", description = "Crea una nueva matrícula en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EnrollmentResponse create(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        return enrollmentService.createEnrollment(enrollmentRequest);
    }

    /**
     * Obtiene todas las matrículas del sistema.
     * 
     * @return Lista de EnrollmentResponse con todas las matrículas
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener todas las matrículas", description = "Retorna una lista con todas las matrículas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de matrículas obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<EnrollmentResponse> getAll() {
        return enrollmentService.getAllEnrollments();
    }

    /**
     * Obtiene una matrícula por su identificador único.
     * 
     * @param idEnrollment Identificador único de la matrícula
     * @return EnrollmentResponse con la información de la matrícula
     */
    @GetMapping("/{idEnrollment}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener matrícula por ID", description = "Busca una matrícula específica por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EnrollmentResponse getById(@PathVariable Long idEnrollment) {
        return enrollmentService.getEnrollmentById(idEnrollment);
    }

    /**
     * Obtiene una matrícula por el ID del estudiante.
     * 
     * @param idStudent ID del estudiante
     * @return EnrollmentResponse con la información de la matrícula
     */
    @GetMapping("/student/{idStudent}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener matrícula por estudiante", description = "Busca una matrícula específica por el ID del estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EnrollmentResponse getByStudentId(@PathVariable Long idStudent) {
        return enrollmentService.getEnrollmentByStudentId(idStudent);
    }

    /**
     * Obtiene una matrícula por el ID del curso.
     * 
     * @param idCourse ID del curso
     * @return EnrollmentResponse con la información de la matrícula
     */
    @GetMapping("/course/{idCourse}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener matrícula por curso", description = "Busca una matrícula específica por el ID del curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EnrollmentResponse getByCourseId(@PathVariable Long idCourse) {
        return enrollmentService.getEnrollmentByCourseId(idCourse);
    }

    /**
     * Actualiza la información de una matrícula existente.
     * 
     * @param id Identificador único de la matrícula a actualizar
     * @param enrollmentRequest Datos actualizados de la matrícula
     * @return EnrollmentResponse con la información de la matrícula actualizada
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualizar matrícula", description = "Actualiza la información de una matrícula existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public EnrollmentResponse update(@PathVariable Long id, @Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        return enrollmentService.updateEnrollment(id, enrollmentRequest);
    }

    /**
     * Elimina una matrícula del sistema.
     * 
     * @param id Identificador único de la matrícula a eliminar
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar matrícula", description = "Elimina una matrícula del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public void delete(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }

    /**
     * Busca matrículas por estado específico.
     * 
     * @param state Estado de la matrícula a buscar (ACTIVE, CANCELLED, GRADUATED)
     * @return Lista de matrículas con el estado especificado
     */
    @GetMapping("/state/{state}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar matrículas por estado", description = "Busca todas las matrículas que tengan un estado específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrículas encontradas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Estado inválido"),
            @ApiResponse(responseCode = "404", description = "No se encontraron matrículas con ese estado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<EnrollmentResponse> getByState(@PathVariable EnrollmentState state) {
        return enrollmentService.getEnrollmentByState(state);
    }
}
