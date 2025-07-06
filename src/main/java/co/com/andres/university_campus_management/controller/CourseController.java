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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;
import co.com.andres.university_campus_management.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para la gestión de cursos en el sistema universitario.
 * 
 * Esta clase proporciona endpoints HTTP para realizar operaciones CRUD completas
 * sobre la entidad Course, incluyendo creación, lectura, actualización y eliminación
 * de cursos, así como búsquedas personalizadas.
 * 
 * Los endpoints están documentados con Swagger/OpenAPI y manejan validaciones
 * de entrada, códigos de estado HTTP apropiados y respuestas estructuradas.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/curso")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "API para la gestión de cursos universitarios")
public class CourseController {

    private final CourseService courseService;

    /**
     * Crea un nuevo curso en el sistema.
     * 
     * @param request Datos del curso a crear
     * @return CourseResponse con la información del curso creado
     */
    @Operation(summary = "Crear curso", description = "Crea un nuevo curso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del curso inválidos"),
            @ApiResponse(responseCode = "409", description = "Código de curso ya existente en el sistema"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse create(@Valid @RequestBody CourseRequest request) {
        return courseService.createCourse(request);
    }

    /**
     * Obtiene la lista completa de cursos registrados en el sistema.
     * 
     * @return Lista de CourseResponse con todos los cursos
     */
    @Operation(summary = "Obtener todos los cursos", description = "Retorna la lista completa de cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay cursos registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CourseResponse> getAll() {
        return courseService.getAllCourse();
    }

    /**
     * Obtiene un curso específico por su ID.
     * 
     * @param id ID del curso a buscar
     * @return CourseResponse con la información del curso
     */
    @Operation(summary = "Obtener curso por ID", description = "Busca un curso específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable("id") Long id) {
        return courseService.byIdCourse(id);
    }

    /**
     * Actualiza la información de un curso existente.
     * 
     * @param id ID del curso a actualizar
     * @param courseRequest Nuevos datos del curso
     * @return CourseResponse con la información actualizada del curso
     */
    @Operation(summary = "Actualizar curso", description = "Actualiza la información de un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del curso inválidos"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "409", description = "Código de curso ya existente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable("id") Long id, @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    /**
     * Elimina un curso del sistema.
     * 
     * @param id ID del curso a eliminar
     */
    @Operation(summary = "Eliminar curso", description = "Elimina un curso del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    /**
     * Busca cursos por nombre (búsqueda parcial, ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el nombre del curso
     * @return Lista de CourseResponse con los cursos que coinciden con la búsqueda
     */
    @Operation(summary = "Buscar cursos por nombre", description = "Busca cursos cuyo nombre contenga el texto especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron cursos con ese nombre"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/buscarNombre")
    public List<CourseResponse> getByName(@RequestParam("n") String text) {
        return courseService.getByName(text);
    }

    /**
     * Busca cursos por código de curso (búsqueda parcial, ignorando mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en el código del curso
     * @return Lista de CourseResponse con los cursos que coinciden con la búsqueda
     */
    @Operation(summary = "Buscar cursos por código", description = "Busca cursos cuyo código contenga el texto especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron cursos con ese código"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/buscarCode")
    public List<CourseResponse> getByCode(@RequestParam("c") String text) {
        return courseService.getByCourseCode(text);
    }


}
