package co.com.andres.university_campus_management.model.entity;


import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa a un estudiante en el sistema de gestión universitaria.
 * Esta clase mapea la tabla "Student" en la base de datos y contiene
 * la información personal y académica básica del estudiante.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Entity
@Data
@Table(name = "Student")
public class Student {

    /**
     * Identificador único del estudiante.
     * Se genera automáticamente como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    /**
     * Nombre del estudiante.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Apellido del estudiante.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Correo electrónico del estudiante.
     * Campo obligatorio, único y no puede ser nulo.
     * Se utiliza para comunicación oficial con el estudiante.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Dirección de residencia del estudiante.
     * Campo opcional que no puede ser nulo.
     */
    @Column(name = "address", nullable = false)
    private String address;
    
    /**
     * Número de teléfono del estudiante.
     * Campo opcional para contacto directo.
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * Número de estudiante asignado por la universidad.
     * Campo obligatorio y único que identifica al estudiante
     * en el sistema académico.
     */
    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    /**
     * Roles asignados al estudiante en el sistema.
     * Colección de roles que define los permisos y accesos
     * que tiene el estudiante en la plataforma.
     * Se carga de forma eager para optimizar consultas de autenticación y autorización.
     */
    @ElementCollection(fetch = FetchType.EAGER) 
    @CollectionTable(
        name = "student_roles", 
        joinColumns = @JoinColumn(name = "student_id")
    )
    
    @Column(name = "rol")
    private Set<String> roles;



   
}
