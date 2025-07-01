package co.com.andres.university_campus_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa un curso en el sistema de gestión universitaria.
 * Esta clase mapea la tabla "Courses" en la base de datos y contiene
 * la información académica del curso, incluyendo su capacidad máxima
 * y la relación con el profesor asignado.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
@Data
@Table(name = "Courses")
@Entity
public class Course {

    /**
     * Identificador único del curso.
     * Se genera automáticamente como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    /**
     * Nombre del curso.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "Name", nullable = false)
    private String name;

    /**
     * Código único del curso.
     * Campo obligatorio, único y no puede ser nulo.
     * Se utiliza para identificación oficial del curso.
     */
    @Column(name = "Course_code", nullable = false, unique = true)
    private String courseCode;

    /**
     * Descripción detallada del curso.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "Description", nullable = false)
    private String description;

    /**
     * Capacidad máxima de estudiantes que pueden inscribirse al curso.
     * Campo obligatorio que no puede ser nulo.
     */
    @Column(name = "Maximun_capacity", nullable = false)
    private Integer maxCapacity;

    /**
     * Identificador del profesor asignado al curso.
     * Relación uno a muchos con la entidad Professor.
     */
    @OneToMany
    @JoinColumn(name = "professor")
    private Long professor;
    
}
