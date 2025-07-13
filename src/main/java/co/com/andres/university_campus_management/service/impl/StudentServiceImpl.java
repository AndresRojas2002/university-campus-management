package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.config.exception.studentException.StudentWintEmailValidException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentWintNumberValidExeption;
import co.com.andres.university_campus_management.config.exception.studentException.StudentWintPhoneValidException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentByIdException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentNumberExistException;
import co.com.andres.university_campus_management.config.exception.studentException.StudentWithEmailExistException;
import co.com.andres.university_campus_management.mapper.StudentMapper;
import co.com.andres.university_campus_management.model.DTO.StudentRequest;
import co.com.andres.university_campus_management.model.DTO.StudentResponse;
import co.com.andres.university_campus_management.repository.StudentRepository;
import co.com.andres.university_campus_management.service.StudentService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de gestión de estudiantes.
 * 
 * Esta clase proporciona la lógica de negocio para todas las operaciones
 * relacionadas con estudiantes, incluyendo validaciones, transformaciones
 * de datos y manejo de excepciones personalizadas.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * Crea un nuevo estudiante en el sistema con validaciones completas.
     * 
     * Realiza validaciones de formato para email, teléfono y número de estudiante,
     * verifica la unicidad de email y número de estudiante, y persiste la entidad.
     * 
     * @param studentRequest Datos del estudiante a crear
     * @return StudentResponse con la información del estudiante creado
     * @throws StudentWintEmailValidException si el formato del email no es válido
     * @throws StudentWintPhoneValidException si el formato del teléfono no es válido
     * @throws StudentWintNumberValidExeption si el formato del número de estudiante no es válido
     * @throws StudentWithEmailExistException si el email ya existe en el sistema
     * @throws StudentNumberExistException si el número de estudiante ya existe en el sistema
     */
    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        // Validar formato del email
        if (!studentRequest.isValidEmail()) {
            throw new StudentWintEmailValidException();
        }

        // Validar formato del teléfono si está presente
        if (studentRequest.phone() != null && !studentRequest.phone().equals("No especificado")
                && !studentRequest.isValidPhone()) {
            throw new StudentWintPhoneValidException();
        }

        // Validar formato del número de estudiante
        if (!studentRequest.isValidStudentNumber()) {
            throw new StudentWintNumberValidExeption();
        }

        // Verificar si el email ya existe
        var emailStudent = studentRepository.findByEmail(studentRequest.email());
        if (emailStudent.isPresent()) {
            throw new StudentWithEmailExistException();
        }

        // Verificar si el número de estudiante ya existe
        var studentNumber = studentRepository.findByStudentNumber(studentRequest.studentNumber());
        if (studentNumber.isPresent()) {
            throw new StudentNumberExistException();
        }

        var entity = studentMapper.toEntity(studentRequest);
        var newStudent = studentRepository.save(entity);

        return studentMapper.toResponse(newStudent);
    }

    /**
     * Obtiene la lista completa de estudiantes registrados en el sistema.
     * 
     * @return Lista de estudiantes transformada a DTOs de respuesta
     */
    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    /**
     * Busca un estudiante específico por su identificador único.
     * 
     * @param id Identificador único del estudiante
     * @return StudentResponse con la información del estudiante encontrado
     * @throws StudentByIdException si no se encuentra un estudiante con el ID especificado
     */
    @Override
    public StudentResponse getById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse)
                .orElseThrow(() -> new StudentByIdException());
    }

    /**
     * Actualiza la información de un estudiante existente.
     * 
     * Realiza validaciones de formato y verifica la existencia del estudiante
     * antes de proceder con la actualización.
     * 
     * @param id Identificador único del estudiante a actualizar
     * @param studentRequest Nuevos datos del estudiante
     * @return StudentResponse con la información actualizada del estudiante
     * @throws StudentByIdException si no se encuentra un estudiante con el ID especificado
     * @throws StudentWintEmailValidException si el formato del email no es válido
     * @throws StudentWintPhoneValidException si el formato del teléfono no es válido
     * @throws StudentWintNumberValidExeption si el formato del número de estudiante no es válido
     */
    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {

        var idStudent = studentRepository.findById(id);
        if (!idStudent.isPresent()) {
            throw new StudentByIdException();
        }

        // Validar formato del email
        if (!studentRequest.isValidEmail()) {
            throw new StudentWintEmailValidException();
        }

        // Validar formato del teléfono si está presente
        if (studentRequest.phone() != null && !studentRequest.phone().equals("No especificado")
                && !studentRequest.isValidPhone()) {
            throw new StudentWintPhoneValidException();
        }

        // Validar formato del número de estudiante
        if (!studentRequest.isValidStudentNumber()) {
            throw new StudentWintNumberValidExeption();
        }

        // Se mapea el DTO a la entidad y se conservan el ID y la contraseña originales
        var entity = studentMapper.toEntity(studentRequest);
        entity.setPassword(idStudent.get().getPassword());
        entity.setIdStudent(idStudent.get().getIdStudent());

        var update = studentRepository.save(entity);
        return studentMapper.toResponse(update);
    }

    /**
     * Elimina un estudiante del sistema por su identificador único.
     * 
     * @param id Identificador único del estudiante a eliminar
     * @throws StudentByIdException si no se encuentra un estudiante con el ID especificado
     */
    @Override
    public void deleteById(Long id) {
        var idStudent = studentRepository.findById(id);
        if (!idStudent.isPresent()) {
            throw new StudentByIdException();
        }
        var student = idStudent.get();
        studentRepository.delete(student);

    }

    /**
     * Busca estudiantes cuyo nombre o apellido contenga el texto especificado.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas y busca tanto en
     * el campo nombre como en el campo apellido.
     * 
     * @param text Texto a buscar en nombre o apellido
     * @return Lista de estudiantes que coinciden con la búsqueda
     */
    @Override
    public List<StudentResponse> getByNameOrLastName(String text) {

        return studentRepository.findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(text, text)
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

}
