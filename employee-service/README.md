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

## Seguridad

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

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   git clone https://github.com/armando-merida-dev/employee-service.git


