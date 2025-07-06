package co.com.andres.university_campus_management.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.andres.university_campus_management.config.exception.professorException.ProfessorByIdException;
import co.com.andres.university_campus_management.config.exception.professorException.ProfessorWithEmailExistException;
import co.com.andres.university_campus_management.config.exception.professorException.ProfessorWithEmailValidException;
import co.com.andres.university_campus_management.config.exception.professorException.ProfessorWithPhoneValidException;
import co.com.andres.university_campus_management.mapper.ProfessorMapper;
import co.com.andres.university_campus_management.model.DTO.ProfessorRequest;
import co.com.andres.university_campus_management.model.DTO.ProfessorResponse;
import co.com.andres.university_campus_management.repository.ProfessorRepository;
import co.com.andres.university_campus_management.service.ProfessorService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio para la gestión de profesores.
 * Proporciona operaciones CRUD y búsqueda para entidades Professor.
 */
@RequiredArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    /**
     * Crea un nuevo profesor después de validar sus datos.
     * 
     * @param professorRequest Datos del profesor a crear
     * @return ProfessorResponse con los datos del profesor creado
     * @throws ProfessorWithEmailValidException si el email no es válido
     * @throws ProfessorWithPhoneValidException si el teléfono no es válido
     * @throws ProfessorWithEmailExistException si el email ya existe
     */
    @Override
    public ProfessorResponse createProfessor(ProfessorRequest professorRequest) {
        // Validar formato del email
        if (!professorRequest.isValidEmail()) {
            throw new ProfessorWithEmailValidException();
        }

        // Validar formato del teléfono
        if (!professorRequest.isValidPhone()) {
            throw new ProfessorWithPhoneValidException();
        }

        // Verificar que el email no exista en la base de datos
        var emailProfessor = professorRepository.findByEmail(professorRequest.email());
        if (emailProfessor.isPresent()) {
            throw new ProfessorWithEmailExistException();
        }

        // Crear y guardar la entidad
        var entity = professorMapper.toEntity(professorRequest);
        var newProfessor = professorRepository.save(entity);

        return professorMapper.toResponse(newProfessor);
    }

    /**
     * Obtiene todos los profesores de la base de datos.
     * 
     * @return Lista de todos los profesores
     */
    @Override
    public List<ProfessorResponse> getAllProfessor() {
        return professorRepository.findAll().stream()
                .map(professorMapper::toResponse)
                .toList();
    }

    /**
     * Busca un profesor por su ID.
     * 
     * @param id ID del profesor a buscar
     * @return ProfessorResponse con los datos del profesor
     * @throws ProfessorByIdException si el profesor no existe
     */
    @Override
    public ProfessorResponse getById(Long id) {
        return professorRepository.findById(id)
                .map(professorMapper::toResponse)
                .orElseThrow(() -> new ProfessorByIdException());
    }

    /**
     * Elimina un profesor por su ID.
     * 
     * @param id ID del profesor a eliminar
     * @throws ProfessorByIdException si el profesor no existe
     */
    @Override
    public void deleteProfessor(Long id) {
        var professorId = professorRepository.findById(id);
        if (!professorId.isPresent()) {
            throw new ProfessorByIdException();
        }
        var professor = professorId.get();

        professorRepository.delete(professor);
    }

    /**
     * Actualiza los datos de un profesor existente.
     * 
     * @param id ID del profesor a actualizar
     * @param professorRequest Nuevos datos del profesor
     * @return ProfessorResponse con los datos actualizados
     * @throws ProfessorByIdException si el profesor no existe
     * @throws ProfessorWithEmailValidException si el email no es válido
     * @throws ProfessorWithPhoneValidException si el teléfono no es válido
     * @throws ProfessorWithEmailExistException si el email ya existe
     */
    @Override
    public ProfessorResponse updateProfessor(Long id, ProfessorRequest professorRequest) {
        // Verificar que el profesor existe
        var idExist = professorRepository.findById(id);
        if (!idExist.isPresent()) {
            throw new ProfessorByIdException();
        }
        
        // Validar formato del email
        if (!professorRequest.isValidEmail()) {
            throw new ProfessorWithEmailValidException();
        }

        // Validar formato del teléfono
        if (!professorRequest.isValidPhone()) {
            throw new ProfessorWithPhoneValidException();
        }

        // Verificar que el email no exista en otro profesor (excluyendo el actual)
        var emailProfessor = professorRepository.findByEmail(professorRequest.email());
        if (emailProfessor.isPresent() && !emailProfessor.get().getIdProfessor().equals(id)) {
            throw new ProfessorWithEmailExistException();
        }

        // Actualizar la entidad manteniendo el ID original
        var entity = professorMapper.toEntity(professorRequest);
        entity.setIdProfessor(idExist.get().getIdProfessor());

        var update = professorRepository.save(entity);

        return professorMapper.toResponse(update);
    }

    /**
     * Busca profesores por nombre o apellido (búsqueda insensible a mayúsculas/minúsculas).
     * 
     * @param text Texto a buscar en nombre o apellido
     * @return Lista de profesores que coinciden con la búsqueda
     */
    @Override
    public List<ProfessorResponse> getByNameOtByLastName(String text) {
        return professorRepository.findByNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(text, text)
                .stream()
                .map(professorMapper::toResponse)
                .toList();
    }
}
