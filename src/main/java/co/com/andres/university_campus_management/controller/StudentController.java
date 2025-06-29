package co.com.andres.university_campus_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Crear estudiante", description = "Crea un nuevo estudiante en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos")
    })
    @PostMapping
    public StudentResponse create (@Valid @RequestBody StudentRequest studentRequest){
        return studentService.createStudent(studentRequest);
    }

    @Operation(summary = "Obtener todos los estudiantes", description = "Obtiene la lista completa de estudiantes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida exitosamente")
    })
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAllStudent();
    }

    @Operation(summary = "Obtener estudiante por ID", description = "Busca un estudiante específico por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{id}")
    public StudentResponse getId(@PathVariable ("id") Long id){
        return studentService.getById(id);
    }

    @Operation(summary = "Actualizar estudiante", description = "Actualiza la información de un estudiante existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos")
    })
    @PutMapping("/{id}")
    public StudentResponse update(@Valid @PathVariable("id") Long id, @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(id, studentRequest);
    }

    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante del sistema por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.deleteById(id);
    }

    @Operation(summary = "Buscar estudiantes por nombre o apellido", description = "Busca estudiantes cuyo nombre o apellido contenga el texto especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    })
    @GetMapping("/buscar")
    public List<StudentResponse> getByNameOrLastName(@RequestParam("b") String text){
        return studentService.getByNameOrLastName(text);
    }

    
}
