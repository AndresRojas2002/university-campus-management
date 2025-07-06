package co.com.andres.university_campus_management.model.entity;

/**
 * Enum que representa los diferentes estados de una matrícula estudiantil.
 * 
 * Este enum define todos los posibles estados que puede tener una matrícula
 * durante el ciclo académico del estudiante en la universidad.
 * 
 * @author Andres
 * @version 1.0
 * @since 2024
 */
public enum EnrollmentState {
    
    /**
     * Matrícula activa y vigente.
     * El estudiante está cursando materias actualmente.
     */
    ACTIVE,
    
    
    /**
     * Matrícula cancelada.
     * El estudiante ha cancelado su matrícula voluntariamente.
     */
    CANCELLED,
    
    /**
     * Estudiante graduado.
     * Ha completado todos los requisitos de su programa académico.
     */
    GRADUATED,
    
    
    
   
}
