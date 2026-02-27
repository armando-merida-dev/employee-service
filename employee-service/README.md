# Employee Service API

Microservicio desarrollado con Spring Boot para la gestión de empleados.
Permite realizar operaciones CRUD.
---

## Tecnologías Utilizadas
- Java 11
- Spring Boot 2.7.18
- Spring Web
- Spring Data JPA
- Spring Security
- H2
- Maven
- JUnit & Mockito
- GitFlow
---

## Arquitectura
El proyecto sigue una arquitectura en capas:

- Controller → Exposición de endpoints
- DTO → Objetos de transferencia
- Entity → Modelos JPA
- Exception → Manejo global y particular de errores
- Mapper → Manejo de mapeos 
- Repository → Acceso a datos
- Service → Lógica de negocio
- Utils → Clases utilitarias reutilizables
---

## Endpoints Principales

| Método | Endpoint          | Descripción                 |
|--------|-------------------|-----------------------------|
| GET    | /employees        | Obtener todos los empleados |
| GET    | /employees/{id}   | Obtener empleado por ID     |
| POST   | /employees        | Crear empleado              |
| PUT    | /employees/{id}   | Actualizar empleado         |
| DELETE | /employees/{id}   | Eliminar empleado           |
| GET    | /employees/search | Busqueda de empleado        |
---

## Ejemplo de JSON (Crear Empleado)

```json
{
  "firstName": "Armando",
  "middleName": "Armando",
  "lastName": "Merida",
  "secondLastName": "Merida",
  "age": 30,
  "gender": "Male",
  "birthDate": "10-02-1990",
  "position": "Backend Developer",
  "active": true
}
```
---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   git clone https://github.com/armando-merida-dev/employee-service.git

2. Integrarlo al workspace del IDE:

3. Construir el proyecto:

4. Ejecutar la aplicación:

La aplicación iniciará en:
http://localhost:8080
---

## Consola de Base de Datos H2

El proyecto utiliza una base de datos en memoria H2 para desarrollo y pruebas.
Puedes validar e inspeccionar la base de datos accediendo a:
http://localhost:8080/h2-console

### Configuración de H2
Utiliza los siguientes datos:

- JDBC URL: jdbc:h2:mem:testdb
- Usuario: sa
- Contraseña: (dejar vacío)
- Driver Class: org.h2.Driver

Desde la consola H2 podrás:

- Visualizar las tablas creadas
- Ejecutar consultas SQL
- Validar registros insertados
- Inspeccionar datos después de consumir los endpoints
---

## Documentación de la API (Swagger)
Una vez que la aplicación esté en ejecución, puedes acceder a la documentación en:

http://localhost:8080/swagger-ui/index.html

La documentación incluye:
- Descripción de endpoints
- Esquemas de entrada y salida
---

## By Armando Merida