# Microservicio CRUD spring boot

Este es un CRUD de usuario implementado en spring boot con una conexión a base de datos h2 y documentación de API con swagger

### Características:

- **Base de datos H2** en memoria para el almacenamiento de datos.
- **Swagger** para la documentación de la API.
- **Junit** para las pruebas unitarias.
- **Sprint Security** para la autorización y autenticación.

### Requisitos:
 
 - JDK 17
 
### Instalación:
 
 1. Clona el repositorio:
 https://github.com/carelys/ms-usuario.git
 
 2. Construye el proyecto con maven:
	```bash
	mvn clean install
	```
3. Ejecuta la aplicación:
	```bash
	mvn spring-boot:run
	```
	
### Los siguientes endpoints están disponibles:

 - `POST /authenticate` - auténtica y autoriza usuarios para usar las apis del CRUD
 - `GET /users` - Obtener todos los usuarios
 - `GET /users/{id}` - Obtener un usuario por id
 - `POST /users` - Crea un usuario
 - `PUT /users/{id}` - Actualiza un usuario
 - `DELETE /users/{id}` - Elimina un usuario
 
### Credenciales para acceder a la base de datos H2
 - JDBC URL: `jdbc:h2:mem:userdb`
 - Usuario: `sa`
 - Contraseña: 
 
### La documentación de las apis están en:
   `http://localhost:8080/swagger-ui/index.html`
 
### Ejecuta las pruebas unitarias con:

	mvn test

	
### Spring Security
 - Obtener token usando el siguiente body
	
```json
	{
	  "username": "testuser",
	  "password": "user123"
	}
```

### Diagrama de de solución:

![MS-User](https://github.com/user-attachments/assets/5be8ebcf-aaad-40a2-b4dc-c4d5abf0df1e)

 
 
 
 
