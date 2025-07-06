package co.com.andres.university_campus_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para la gestión de estudiantes en el sistema universitario.
 * 
 * Esta clase proporciona endpoints HTTP para realizar operaciones CRUD completas
 * sobre la entidad Student, incluyendo creación, lectura, actualización y eliminación
 * de estudiantes, así como búsquedas personalizadas.
 * 
 * Los endpoints están documentados con Swagger/OpenAPI y manejan validaciones
 * de entrada, códigos de estado HTTP apropiados y respuestas estructuradas.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
@Tag(name = "Estudiantes", description = "API para la gestión de estudiantes universitarios")
public class StudentController {

    private final StudentService studentService;

    /**
     * Crea un nuevo estudiante en el sistema.
     * 
     * @param studentRequest Datos del estudiante a crear
     * @return StudentResponse con la información del estudiante creado
     */
    @Operation(summary = "Crear estudiante", description = "Crea un nuevo estudiante en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos"),
            @ApiResponse(responseCode = "409", description = "Número de estudiante o email ya existentes en el sistema"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasRole('ADMIN', 'PROFESOR')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    /**
     * Obtiene la lista completa de estudiantes registrados en el sistema.
     * 
     * @return Lista de estudiantes
     */
    @Operation(summary = "Obtener todos los estudiantes", description = "Obtiene la lista completa de estudiantes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay estudiantes registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAllStudent();
    }

    /**
     * Busca un estudiante específico por su identificador único.
     * 
     * @param id Identificador único del estudiante
     * @return StudentResponse con la información del estudiante encontrado
     */
    @Operation(summary = "Obtener estudiante por ID", description = "Busca un estudiante específico por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StudentResponse getByIdStunt(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    /**
     * Actualiza la información de un estudiante existente.
     * 
     * @param id Identificador único del estudiante a actualizar
     * @param studentRequest Nuevos datos del estudiante
     * @return StudentResponse con la información actualizada del estudiante
     */
    @Operation(summary = "Actualizar estudiante", description = "Actualiza la información de un estudiante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos"),
            @ApiResponse(responseCode = "409", description = "Número de estudiante o email ya existente en el sistema"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public StudentResponse update(@Valid @PathVariable("id") Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    /**
     * Elimina un estudiante del sistema por su identificador único.
     * 
     * @param id Identificador único del estudiante a eliminar
     */
    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante del sistema por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    /**
     * Busca estudiantes cuyo nombre o apellido contenga el texto especificado.
     * 
     * @param text Texto a buscar en nombre o apellido
     * @return Lista de estudiantes que coinciden con la búsqueda
     */
    @Operation(summary = "Buscar estudiantes por nombre o apellido", description = "Busca estudiantes cuyo nombre o apellido contenga el texto especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    @GetMapping("/buscar")
    public List<StudentResponse> getByNameOrLastName(@RequestParam("b") String text) {
        return studentService.getByNameOrLastName(text);
    }
}
