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

#### Ejemplo de curl:

```
curl --location 'http://localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE' \
--data '{
  "username": "testuser",
  "password": "user123"
}'
```

```
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTcyMTAxNDYxMSwiZXhwIjoxNzIxMDUwNjExfQ.1MFtHwn7ntiDGxzU_-LyGi4L5uqsVGyUppbnuHnOgWY' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
        
    ]
}'
```

```
curl --location --request GET 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTcyMTAxNDYxMSwiZXhwIjoxNzIxMDUwNjExfQ.1MFtHwn7ntiDGxzU_-LyGi4L5uqsVGyUppbnuHnOgWY' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'
```

```
curl --location --request GET 'http://localhost:8080/users/5a6924f9-8741-417a-adef-858ff9653f5c' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTcyMTAxMzgwNSwiZXhwIjoxNzIxMDQ5ODA1fQ.ZLgux-GGF4eXC_q4W3QQTHYbeDuUgQS_bMp5_huF-a0' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'
```

```
curl --location --request PUT 'http://localhost:8080/users/b04253d5-f110-4ced-bfba-581f8d7064f8' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTcyMTAxNDYxMSwiZXhwIjoxNzIxMDUwNjExfQ.1MFtHwn7ntiDGxzU_-LyGi4L5uqsVGyUppbnuHnOgWY' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter21",
    "phones": [
        {
            "number": "981408316",
            "citycode": "1",
            "contrycode": "56"
        }
        
    ]
}'
```

```
curl --location --request DELETE 'http://localhost:8080/users/fdf507c9-bebf-4b45-96a9-0e88b16aae6a' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTcyMTAxNDYxMSwiZXhwIjoxNzIxMDUwNjExfQ.1MFtHwn7ntiDGxzU_-LyGi4L5uqsVGyUppbnuHnOgWY' \
--header 'Cookie: JSESSIONID=9E4AD01C62814139BF113C14D36309EE'
```

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

![Arquitectura ms-usuario](https://github.com/user-attachments/assets/c1c030d4-56fb-4a1a-b952-54af0a96da3d)


 
 
 
 
