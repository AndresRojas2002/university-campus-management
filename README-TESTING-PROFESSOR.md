# Testing del Endpoint de Profesores

## Problema Resuelto

El error original era un código de estado 500 (Internal Server Error) cuando debería ser 400 (Bad Request) para errores de validación. Esto se debía a que faltaba un manejador específico para excepciones de Bean Validation en el `GlobalExceptionHandler`.

## Cambios Realizados

1. **Agregado manejador de validaciones**: Se agregó el método `handleValidationExceptions` en `GlobalExeptionHandler` para manejar `MethodArgumentNotValidException`.

2. **Corregido import en controlador**: Se cambió el import incorrecto de `@RequestBody` de Swagger a Spring.

3. **Corregida anotación @Valid**: Se movió la anotación `@Valid` al lugar correcto en el método `update`.

4. **Corregido mapeo JSON**: Se cambió `last_name` a `lastName` en el `@JsonProperty` para consistencia.

## Cómo Probar

### 1. Ejemplo Válido
```bash
curl -X POST http://localhost:8080/api/profesor \
  -H "Content-Type: application/json" \
  -d @test-professor-request.json
```

### 2. Ejemplos Inválidos para Probar Validaciones

#### Campos vacíos o null:
```bash
curl -X POST http://localhost:8080/api/profesor \
  -H "Content-Type: application/json" \
  -d '{
    "name": "",
    "lastName": null,
    "email": "",
    "address": null
  }'
```

#### Email con dominio incorrecto:
```bash
curl -X POST http://localhost:8080/api/profesor \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Luis Andres",
    "lastName": "Rojas Acevedo",
    "email": "andres.rojas@gmail.com",
    "phone": "3001234567",
    "address": "Calle 123 #45-67, Barrio Centro, Ciudad"
  }'
```

#### Teléfono inválido:
```bash
curl -X POST http://localhost:8080/api/profesor \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Luis Andres",
    "lastName": "Rojas Acevedo",
    "email": "andres.rojas@universidad.com",
    "phone": "abc123",
    "address": "Calle 123 #45-67, Barrio Centro, Ciudad"
  }'
```

## Respuestas Esperadas

### Para datos válidos:
- **Código**: 201 (Created)
- **Cuerpo**: JSON con los datos del profesor creado

### Para errores de validación:
- **Código**: 400 (Bad Request)
- **Cuerpo**: JSON con mensaje de error estructurado
- **Ejemplo**:
```json
{
  "status": "BAD_REQUEST",
  "message": "Errores de validación: {lastName=EL APELLIDO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO, name=EL NOMBRE ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO, address=LA DIRECCIÓN ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO, email=EL CORREO ELECTRÓNICO ES UN CAMPO OBLIGATORIO Y NO PUEDE ESTAR VACÍO}",
  "path": "/api/profesor",
  "timestamp": "2025-07-06T00:06:06"
}
```

## Campos Obligatorios

- `name`: Nombre del profesor (no puede estar vacío)
- `lastName`: Apellido del profesor (no puede estar vacío)
- `email`: Correo electrónico (debe terminar en @universidad.com)
- `address`: Dirección del profesor (no puede estar vacío)
- `phone`: Teléfono (opcional, formato: 7-20 dígitos, opcionalmente precedido por '+') 