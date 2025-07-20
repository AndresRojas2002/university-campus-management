# 🎓 Sistema de Gestión Universitaria

## 📋 Descripción

Sistema de gestión universitaria desarrollado con Spring Boot que permite administrar estudiantes, profesores, cursos y matrículas. El sistema incluye autenticación JWT, autorización basada en roles, validaciones robustas y documentación completa con Swagger.

## 🚀 Características Principales

- **Autenticación JWT**: Sistema seguro de autenticación con tokens JWT
- **Autorización por Roles**: Control de acceso basado en roles (ADMIN, PROFESSOR, STUDENT)
- **Validaciones Robustas**: Validaciones de datos con Bean Validation
- **Documentación API**: Documentación completa con Swagger/OpenAPI 3
- **Mapeo de Objetos**: Uso de MapStruct para mapeo eficiente entre DTOs y entidades
- **Manejo de Excepciones**: Sistema centralizado de manejo de errores
- **Encriptación de Contraseñas**: Contraseñas encriptadas con BCrypt
- **Base de Datos H2**: Base de datos en memoria para desarrollo

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21**: Lenguaje de programación principal
- **Spring Boot 3.5.3**: Framework principal
- **Spring Security**: Seguridad y autenticación
- **Spring Data JPA**: Persistencia de datos
- **H2 Database**: Base de datos en memoria
- **MapStruct**: Mapeo de objetos
- **JWT**: Autenticación con tokens
- **Bean Validation**: Validaciones de datos
- **Swagger/OpenAPI 3**: Documentación de API

### Herramientas de Desarrollo
- **Maven**: Gestión de dependencias y build
- **Git**: Control de versiones
- **IntelliJ IDEA / Eclipse**: IDE recomendado

## 📁 Estructura del Proyecto

```
university-campus-management/
├── src/
│   ├── main/
│   │   ├── java/co/com/andres/university_campus_management/
│   │   │   ├── config/                    # Configuraciones
│   │   │   │   ├── exception/             # Manejo de excepciones
│   │   │   │   ├── SecurityConfiguration.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controller/                # Controladores REST
│   │   │   │   ├── AuthenticateController.java
│   │   │   │   ├── StudentController.java
│   │   │   │   ├── ProfessorController.java
│   │   │   │   ├── CourseController.java
│   │   │   │   └── EnrollmentController.java
│   │   │   ├── model/                     # Modelos de datos
│   │   │   │   ├── DTO/                   # Data Transfer Objects
│   │   │   │   └── entity/                # Entidades JPA
│   │   │   ├── repository/                # Repositorios JPA
│   │   │   ├── service/                   # Lógica de negocio
│   │   │   │   └── impl/                  # Implementaciones
│   │   │   ├── mapper/                    # Mapeadores MapStruct
│   │   │   └── utils/                     # Utilidades
│   │   │       └── JwtUtil.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── diagram.puml
│   └── test/                              # Pruebas unitarias
├── http/                                 # Archivos de prueba HTTP
├── pom.xml                               # Configuración Maven
└── README.md
```

## 🚀 Instalación y Configuración

### Prerrequisitos
- Java 21 o superior
- Maven 3.6 o superior
- Git

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd university-campus-management
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - **API**: http://localhost:8080
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **H2 Console**: http://localhost:8080/h2-console

### Configuración de Base de Datos

La aplicación utiliza H2 Database en memoria. La configuración se encuentra en `application.properties`:

```properties
# Configuración de base de datos H2
spring.datasource.url=jdbc:h2:mem:universitydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuración JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## 🔐 Autenticación y Autorización

### Roles del Sistema
- **ROLE_ADMIN**: Acceso completo al sistema
- **ROLE_PROFESSOR**: Gestión de cursos y matrículas
- **ROLE_STUDENT**: Consulta de información personal

### Proceso de Autenticación

1. **Registro de Usuario**: Crear cuenta con email y contraseña
2. **Login**: Obtener token JWT
3. **Autorización**: Usar token en headers de requests

### Ejemplo de Autenticación

```bash
# 1. Crear un profesor (requiere token de admin)
POST /api/v1/professors
Authorization: Bearer <admin-token>
Content-Type: application/json

{
  "name": "Luis Andres",
  "lastName": "Rojas Acevedo",
  "email": "andres.rojas@universidad.com",
  "phone": "3001234567",
  "address": "Calle 123 #45-67, Barrio Centro, Ciudad",
  "password": "Profesor2024!",
  "roles": ["ROLE_PROFESSOR"]
}

# 2. Autenticarse
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "andres.rojas@universidad.com",
  "password": "Profesor2024!"
}

# 3. Usar el token en requests posteriores
GET /api/v1/courses
Authorization: Bearer <jwt-token>
```

## 📚 API Endpoints

### Autenticación
- `POST /api/v1/auth/login` - Iniciar sesión
- `POST /api/v1/auth/register` - Registrarse

### Estudiantes
- `GET /api/v1/students` - Obtener todos los estudiantes
- `GET /api/v1/students/{id}` - Obtener estudiante por ID
- `POST /api/v1/students` - Crear estudiante
- `PUT /api/v1/students/{id}` - Actualizar estudiante
- `DELETE /api/v1/students/{id}` - Eliminar estudiante
- `GET /api/v1/students/buscar?b={texto}` - Buscar estudiantes

### Profesores
- `GET /api/v1/professors` - Obtener todos los profesores
- `GET /api/v1/professors/{id}` - Obtener profesor por ID
- `POST /api/v1/professors` - Crear profesor
- `PUT /api/v1/professors/{id}` - Actualizar profesor
- `DELETE /api/v1/professors/{id}` - Eliminar profesor
- `GET /api/v1/professors/buscar?b={texto}` - Buscar profesores

### Cursos
- `GET /api/v1/courses` - Obtener todos los cursos
- `GET /api/v1/courses/{id}` - Obtener curso por ID
- `POST /api/v1/courses` - Crear curso
- `PUT /api/v1/courses/{id}` - Actualizar curso
- `DELETE /api/v1/courses/{id}` - Eliminar curso

### Matrículas
- `GET /api/v1/enrollments` - Obtener todas las matrículas
- `GET /api/v1/enrollments/{id}` - Obtener matrícula por ID
- `POST /api/v1/enrollments` - Crear matrícula
- `PUT /api/v1/enrollments/{id}` - Actualizar matrícula
- `DELETE /api/v1/enrollments/{id}` - Eliminar matrícula
- `GET /api/v1/enrollments/estado/{estado}` - Filtrar por estado

## 🔧 Configuración de Seguridad

### JWT Configuration
```properties
# Configuración JWT
jwt.secret=tu-clave-secreta-muy-segura-aqui
jwt.expiration=86400000
```

### Roles y Permisos
```java
// Ejemplo de configuración de roles
@PreAuthorize("hasRole('ADMIN')")           // Solo administradores
@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")  // Admin o profesores
@PreAuthorize("hasRole('STUDENT')")         // Solo estudiantes
```

## 📊 Modelo de Datos

### Entidades Principales

#### Student (Estudiante)
- `idStudent`: Identificador único
- `name`: Nombre
- `lastName`: Apellido
- `email`: Correo electrónico (@universidad.com)
- `phone`: Teléfono
- `address`: Dirección
- `studentNumber`: Número de estudiante
- `password`: Contraseña encriptada
- `roles`: Roles del sistema

#### Professor (Profesor)
- `idProfessor`: Identificador único
- `name`: Nombre
- `lastName`: Apellido
- `email`: Correo electrónico (@universidad.com)
- `phone`: Teléfono
- `address`: Dirección
- `password`: Contraseña encriptada
- `roles`: Roles del sistema

#### Course (Curso)
- `idCourse`: Identificador único
- `name`: Nombre del curso
- `courseCode`: Código del curso (formato: XXX-000)
- `description`: Descripción
- `maxCapacity`: Capacidad máxima
- `professor`: Profesor asignado

#### Enrollment (Matrícula)
- `idEnrollment`: Identificador único
- `student`: Estudiante matriculado
- `course`: Curso
- `enrollmentDate`: Fecha de matrícula
- `enrollmentState`: Estado (ACTIVE, INACTIVE, CANCELLED)

## 🧪 Pruebas

### Archivos de Prueba HTTP
El proyecto incluye archivos `.http` para probar los endpoints:

- `Authenticate.http` - Pruebas de autenticación
- `Student.http` - Pruebas de estudiantes
- `Professor.http` - Pruebas de profesores
- `Course.http` - Pruebas de cursos
- `Enrollment.http` - Pruebas de matrículas

### Ejecutar Pruebas
```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con cobertura
mvn test jacoco:report
```

## 🔍 Validaciones Implementadas

### Validaciones de Email
- Formato válido de email
- Dominio @universidad.com requerido

### Validaciones de Teléfono
- Formato: 7-20 dígitos
- Opcionalmente precedido por '+'

### Validaciones de Código de Curso
- Formato: 3-4 letras mayúsculas + guión + 3 dígitos
- Ejemplo: "PROG-101", "MATH-202"

### Validaciones de Contraseña
- Mínimo 8 caracteres
- Encriptación con BCrypt

### Validaciones de Capacidad
- Rango: 1-50 estudiantes por curso

## 🚨 Manejo de Excepciones

El sistema incluye un manejo centralizado de excepciones con:

- **GlobalExceptionHandler**: Manejo global de errores
- **Excepciones Personalizadas**: Para cada tipo de validación
- **Respuestas Estructuradas**: Formato consistente de errores

### Tipos de Excepciones
- `StudentByIdException` - Estudiante no encontrado
- `ProfessorWithEmailExistException` - Email de profesor duplicado
- `CourseWithCodeExistException` - Código de curso duplicado
- `EnrollmentWithDateValidException` - Fecha de matrícula inválida

## 📈 Características de Rendimiento

- **MapStruct**: Mapeo eficiente entre DTOs y entidades
- **Lazy Loading**: Carga diferida de relaciones JPA
- **Caché de Consultas**: Optimización de consultas frecuentes
- **Validaciones Eficientes**: Validaciones en tiempo de compilación

## 🔧 Configuración de Desarrollo

### Variables de Entorno
```bash
# Configuración de desarrollo
export SPRING_PROFILES_ACTIVE=dev
export JWT_SECRET=tu-clave-secreta-desarrollo
export JWT_EXPIRATION=86400000
```

### Logs
```properties
# Configuración de logs
logging.level.co.com.andres=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## 📝 Documentación Adicional

### Swagger UI
Accede a la documentación interactiva en: http://localhost:8080/swagger-ui.html

### H2 Console
Accede a la consola de base de datos en: http://localhost:8080/h2-console

### Diagrama de Base de Datos
El archivo `diagram.puml` contiene el diagrama de la base de datos.

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**Andres Rojas Acevedo**
- Email: rojasandres2002.24@gmail.com
- GitHub: [AndresRojas2002](https://github.com/AndresRojas2002)

## 🙏 Agradecimientos

- Spring Boot Team por el excelente framework
- MapStruct por la herramienta de mapeo
- La comunidad de Java por el soporte continuo

---

**Nota**: Este proyecto es parte del módulo 7 de programación Java y está diseñado para fines educativos y de demostración. 