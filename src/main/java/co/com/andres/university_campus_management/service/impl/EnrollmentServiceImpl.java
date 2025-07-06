package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.config.exception.couseException.CourseByIdException;
import co.com.andres.university_campus_management.config.exception.enrollmentException.EnrollmentByIdException;
import co.com.andres.university_campus_management.config.exception.enrollmentException.EnrollmentByStateException;
import co.com.andres.university_campus_management.config.exception.enrollmentException.EnrollmentWithDateValidException;
import co.com.andres.university_campus_management.config.exception.enrollmentException.EnrollmentWithIdCourseValidException;
import co.com.andres.university_campus_management.config.exception.enrollmentException.EnrollmentWithIdSudentValidException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentByIdException;
import co.com.andres.university_campus_management.mapper.EnrollmentMapper;
import co.com.andres.university_campus_management.model.DTO.EnrollmentRequest;
import co.com.andres.university_campus_management.model.DTO.EnrollmentResponse;
import co.com.andres.university_campus_management.model.entity.EnrollmentState;
import co.com.andres.university_campus_management.repository.EnrollmentRepository;
import co.com.andres.university_campus_management.service.EnrollmentService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de gestión de matrículas.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con las matrículas, incluyendo creación, actualización, eliminación
 * y consultas de matrículas en el sistema de gestión universitaria.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentRepository enrollmentRepository;

    /**
     * Crea una nueva matrícula en el sistema.
     * 
     * Realiza validaciones del ID del curso, ID del estudiante y fecha de matrícula
     * antes de proceder con la creación.
     * 
     * @param enrollmentRequest Datos de la matrícula a crear
     * @return EnrollmentResponse con la información de la matrícula creada
     * @throws EnrollmentWithIdCourseValidException si el ID del curso no es válido
     * @throws EnrollmentWithIdSudentValidException si el ID del estudiante no es válido
     * @throws EnrollmentWithDateValidException si la fecha de matrícula no es válida
     */
    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        // Validación del ID del curso
        if (!enrollmentRequest.isValidIdCourse()) {
            throw new EnrollmentWithIdCourseValidException();
        }

        // Validación del ID del estudiante
        if (!enrollmentRequest.isValidIdStudent()) {
            throw new EnrollmentWithIdSudentValidException();
        }

        // Validación de la fecha de matrícula
        if (!enrollmentRequest.isValidEnrollmentDate()) {
            throw new EnrollmentWithDateValidException();
        }

        // Conversión a entidad y guardado en base de datos
        var entity = enrollmentMapper.toEntity(enrollmentRequest);
        var newEnrollment = enrollmentRepository.save(entity);

        // Retorno de la respuesta mapeada
        return enrollmentMapper.toResponse(newEnrollment);
    }

    /**
     * Obtiene todas las matrículas del sistema.
     * 
     * @return Lista de todas las matrículas registradas
     */
    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }

    /**
     * Busca una matrícula por su ID específico.
     * 
     * @param idEnrollment ID de la matrícula a buscar
     * @return EnrollmentResponse con la información de la matrícula encontrada
     * @throws EnrollmentByIdException si no se encuentra una matrícula con el ID especificado
     */
    @Override
    public EnrollmentResponse getEnrollmentById(Long idEnrollment) {
        return enrollmentRepository.findById(idEnrollment)
                .map(enrollmentMapper::toResponse)
                .orElseThrow(() -> new EnrollmentByIdException());
    }

    /**
     * Busca una matrícula por el ID del curso.
     * 
     * @param idCourse ID del curso para buscar la matrícula
     * @return EnrollmentResponse con la información de la matrícula encontrada
     * @throws CourseByIdException si no se encuentra una matrícula para el curso especificado
     */
    @Override
    public EnrollmentResponse getEnrollmentByCourseId(Long idCourse) {
        return enrollmentRepository.findByCourse_idCourse(idCourse)
                .map(enrollmentMapper::toResponse)
                .orElseThrow(() -> new CourseByIdException());
    }

    /**
     * Busca una matrícula por el ID del estudiante.
     * 
     * @param idStudent ID del estudiante para buscar la matrícula
     * @return EnrollmentResponse con la información de la matrícula encontrada
     * @throws StudentByIdException si no se encuentra una matrícula para el estudiante especificado
     */
    @Override
    public EnrollmentResponse getEnrollmentByStudentId(Long idStudent) {
        return enrollmentRepository.findByStudent_idStudent(idStudent)
                .map(enrollmentMapper::toResponse)
                .orElseThrow(() -> new StudentByIdException());
    }

    /**
     * Actualiza una matrícula existente en el sistema.
     * 
     * Realiza validaciones del ID del curso, ID del estudiante y fecha de matrícula
     * antes de proceder con la actualización.
     * 
     * @param idEnrollment ID de la matrícula a actualizar
     * @param enrollmentRequest Nuevos datos de la matrícula
     * @return EnrollmentResponse con la información de la matrícula actualizada
     * @throws EnrollmentByIdException si no se encuentra una matrícula con el ID especificado
     * @throws EnrollmentWithIdCourseValidException si el ID del curso no es válido
     * @throws EnrollmentWithIdSudentValidException si el ID del estudiante no es válido
     * @throws EnrollmentWithDateValidException si la fecha de matrícula no es válida
     */
    @Override
    public EnrollmentResponse updateEnrollment(Long idEnrollment, EnrollmentRequest enrollmentRequest) {
        // Verificar que la matrícula existe
        var existingEnrollment = enrollmentRepository.findById(idEnrollment)
                .orElseThrow(() -> new EnrollmentByIdException());

        // Validación del ID del curso
        if (!enrollmentRequest.isValidIdCourse()) {
            throw new EnrollmentWithIdCourseValidException();
        }

        // Validación del ID del estudiante
        if (!enrollmentRequest.isValidIdStudent()) {
            throw new EnrollmentWithIdSudentValidException();
        }

        // Validación de la fecha de matrícula
        if (!enrollmentRequest.isValidEnrollmentDate()) {
            throw new EnrollmentWithDateValidException();
        }

        // Conversión a entidad y actualización
        var entity = enrollmentMapper.toEntity(enrollmentRequest);
        entity.setIdEnrollment(existingEnrollment.getIdEnrollment());

        var updatedEnrollment = enrollmentRepository.save(entity);

        return enrollmentMapper.toResponse(updatedEnrollment);
    }

    /**
     * Elimina una matrícula del sistema.
     * 
     * @param idEnrollment ID de la matrícula a eliminar
     * @throws EnrollmentByIdException si no se encuentra una matrícula con el ID especificado
     */
    @Override
    public void deleteEnrollment(Long idEnrollment) {
        var existingEnrollment = enrollmentRepository.findById(idEnrollment)
                .orElseThrow(() -> new EnrollmentByIdException());

        enrollmentRepository.delete(existingEnrollment);
    }

    /**
     * Busca matrículas por estado específico.
     * 
     * @param state Estado de la matrícula a buscar (ACTIVE, CANCELLED, GRADUATED)
     * @return Lista de matrículas con el estado especificado
     */
    @Override
    public List<EnrollmentResponse> getEnrollmentByState(EnrollmentState state) {
        var enrollments = enrollmentRepository.findByEnrollmentState(state);
        
        if (enrollments.isEmpty()) {
            throw new EnrollmentByStateException();
        }
        
        return enrollments.stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }
    }


