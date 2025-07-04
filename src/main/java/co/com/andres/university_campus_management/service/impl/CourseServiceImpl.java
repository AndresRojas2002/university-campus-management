package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.config.exception.couseException.CourseByIdException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseCodeValidException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseMaxCapacityValidException;
import co.com.andres.university_campus_management.config.exception.couseException.CourseWithCodeExistException;
import co.com.andres.university_campus_management.mapper.CourseMapper;
import co.com.andres.university_campus_management.model.DTO.CourseRequest;
import co.com.andres.university_campus_management.model.DTO.CourseResponse;
import co.com.andres.university_campus_management.repository.CourseRepository;
import co.com.andres.university_campus_management.service.CourseService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de gestión de cursos.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con los cursos, incluyendo creación, actualización, eliminación
 * y consultas de cursos en el sistema de gestión universitaria.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /**
     * Crea un nuevo curso en el sistema.
     * 
     * Realiza validaciones del código del curso y la capacidad máxima
     * antes de proceder con la creación.
     * 
     * @param courseRequest Datos del curso a crear
     * @return CourseResponse con la información del curso creado
     * @throws CourseCodeValidException si el formato del código del curso no es válido
     * @throws CourseMaxCapacityValidException si la capacidad máxima no está en el rango permitido
     */
    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        // Validar formato del código del curso
        if (!courseRequest.isValidCourseCode()) {
            throw new CourseCodeValidException();
        }

        // Validar capacidad máxima del curso
        if (!courseRequest.isValidMaxCapacity()) {
            throw new CourseMaxCapacityValidException();
        }

        var entity = courseMapper.toEntity(courseRequest);
        var newCourse = courseRepository.save(entity);

        return courseMapper.toResponse(newCourse);
    }

    /**
     * Obtiene todos los cursos registrados en el sistema.
     * 
     * @return Lista de todos los cursos disponibles
     */
    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    /**
     * Busca un curso por su identificador único.
     * 
     * @param id Identificador único del curso
     * @return CourseResponse con la información del curso encontrado
     * @throws CourseByIdException si no se encuentra un curso con el ID especificado
     */
    @Override
    public CourseResponse byIdCourse(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toResponse)
                .orElseThrow(() -> new CourseByIdException());
    }

    /**
     * Actualiza la información de un curso existente.
     * 
     * Realiza validaciones de formato y verifica la existencia del curso
     * antes de proceder con la actualización.
     * 
     * @param id Identificador único del curso a actualizar
     * @param courseRequest Nuevos datos del curso
     * @return CourseResponse con la información actualizada del curso
     * @throws CourseByIdException si no se encuentra un curso con el ID especificado
     * @throws CourseCodeValidException si el formato del código del curso no es válido
     * @throws CourseMaxCapacityValidException si la capacidad máxima no está en el rango permitido
     */
    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        var getId = courseRepository.findById(id);

        if (!getId.isPresent()) {
            throw new CourseByIdException();
        }

        // Validar formato del código del curso
        if (!courseRequest.isValidCourseCode()) {
            throw new CourseCodeValidException();
        }

        // Validar capacidad máxima del curso
        if (!courseRequest.isValidMaxCapacity()) {
            throw new CourseMaxCapacityValidException();
        }

        // Validar si el curso ya está registrado 
        var existingCourse = courseRepository.findByCourseCode(courseRequest.courseCode());
        if (existingCourse.isPresent()) {
            throw new CourseWithCodeExistException();
        }

        var entity = courseMapper.toEntity(courseRequest);
        entity.setIdCourse(getId.get().getIdCourse());

        var update = courseRepository.save(entity);

        return courseMapper.toResponse(update);
    }

    /**
     * Elimina un curso del sistema por su identificador único.
     * 
     * @param id Identificador único del curso a eliminar
     * @throws CourseByIdException si no se encuentra un curso con el ID especificado
     */
    @Override
    public void deleteCourse(Long id) {
        var getId = courseRepository.findById(id);
        if (!getId.isPresent()) {
            throw new CourseByIdException();
        }
        var course = getId.get();
        courseRepository.delete(course);
    }

    /**
     * Busca cursos cuyo nombre contenga el texto especificado.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param text Texto a buscar en el nombre del curso
     * @return Lista de cursos que coinciden con la búsqueda
     */
    @Override
    public List<CourseResponse> getByName(String text) {
        return courseRepository.findByNameContainingIgnoreCase(text).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    /**
     * Busca cursos cuyo código contenga el texto especificado.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param text Texto a buscar en el código del curso
     * @return Lista de cursos que coinciden con la búsqueda
     */
    @Override
    public List<CourseResponse> getByCourseCode(String text) {
        return courseRepository.findByCourseCodeContainingIgnoreCase(text).stream()
                .map(courseMapper::toResponse)
                .toList();
    }
}
