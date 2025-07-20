package co.com.andres.university_campus_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;
import co.com.andres.university_campus_management.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Controlador REST para la gestión de profesores en el sistema universitario.
 * 
 * Esta clase proporciona endpoints HTTP para realizar operaciones CRUD completas
 * sobre la entidad Professor, incluyendo creación, lectura, actualización y eliminación
 * de profesores, así como búsquedas personalizadas.
 * 
 * Los endpoints están documentados con Swagger/OpenAPI y manejan validaciones
 * de entrada, códigos de estado HTTP apropiados y respuestas estructuradas.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/profesor")
@RequiredArgsConstructor
@Tag(name = "Profesores", description = "API para la gestión de profesores universitarios")
public class ProfessorController {

    private final ProfessorService professorService;

    /**
     * Crea un nuevo profesor en el sistema.
     * 
     * @param professorRequest Datos del profesor a crear
     * @return ProfessorResponse con la información del profesor creado
     */
    @Operation(summary = "Crear profesor", description = "Crea un nuevo profesor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del profesor inválidos"),
            @ApiResponse(responseCode = "409", description = "Email ya existente en el sistema"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProfessorResponse create(@Valid @RequestBody ProfessorRequest professorRequest) {
        return professorService.createProfessor(professorRequest);
    }

    /**
     * Obtiene la lista completa de profesores registrados en el sistema.
     * 
     * @return Lista de profesores
     */
    @Operation(summary = "Obtener todos los profesores", description = "Obtiene la lista completa de profesores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de profesores obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay profesores registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProfessorResponse> getAll() {
        return professorService.getAllProfessor();
    }

    /**
     * Busca un profesor específico por su identificador único.
     * 
     * @param id Identificador único del profesor
     * @return ProfessorResponse con la información del profesor encontrado
     */
    @Operation(summary = "Obtener profesor por ID", description = "Busca un profesor específico por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProfessorResponse getByIdProfessor(@PathVariable("id") Long id) {
        return professorService.getById(id);
    }

    /**
     * Actualiza la información de un profesor existente.
     * 
     * @param id Identificador único del profesor a actualizar
     * @param professorRequest Nuevos datos del profesor
     * @return ProfessorResponse con la información actualizada del profesor
     */
    @Operation(summary = "Actualizar profesor", description = "Actualiza la información de un profesor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del profesor inválidos"),
            @ApiResponse(responseCode = "409", description = "Email ya existente en el sistema"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ProfessorResponse update(@Valid @PathVariable("id") Long id, @RequestBody ProfessorRequest professorRequest) {
        return professorService.updateProfessor(id, professorRequest);
    }

    /**
     * Elimina un profesor del sistema por su identificador único.
     * 
     * @param id Identificador único del profesor a eliminar
     */
    @Operation(summary = "Eliminar profesor", description = "Elimina un profesor del sistema por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profesor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        professorService.deleteProfessor(id);
    }

    /**
     * Busca profesores cuyo nombre o apellido contenga el texto especificado.
     * 
     * @param text Texto a buscar en nombre o apellido
     * @return Lista de profesores que coinciden con la búsqueda
     */
    @Operation(summary = "Buscar profesores por nombre o apellido", description = "Busca profesores cuyo nombre o apellido contenga el texto especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/buscar")
    public List<ProfessorResponse> getByNameOrLastName(@RequestParam("b") String text) {
        return professorService.getByNameOtByLastName(text);
    }
}
