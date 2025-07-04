package co.com.andres.university_campus_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa un profesor en el sistema de gestión universitaria.
 * Esta clase mapea la tabla "Professors" en la base de datos y contiene
 * la información personal y de contacto del profesor.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Data
@Table(name = "Professors")
@Entity
public class Professor {

    /**
     * Identificador único del profesor.
     * Se genera automáticamente como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
    private Long idProfessor;

    /**
     * Nombre del profesor.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Apellido del profesor.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Correo electrónico del profesor.
     * Campo obligatorio, único y no puede ser nulo.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Número de teléfono del profesor.
     * Campo opcional para contacto.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Dirección del profesor.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Ciudad donde reside el profesor.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "city", nullable = false)
    private String city;
}
