# API REST — Red Social Universitaria (JAX-RS)

Este proyecto implementa un conjunto de Web Services REST utilizando JAX-RS sin frameworks externos como Maven o Spring Boot.  
La API permite gestionar usuarios, publicaciones, comentarios, seguidores, notificaciones y reportes.

---

## 1. Publicación / despliegue de la API

La API se ejecuta con un servidor HTTP embebido (Grizzly) configurado en la clase app.App. No usamos Maven ni Spring Boot, todo se resuelve con los .jar que están en la carpeta lib.

### 1.1. Requisitos previos

Para poder levantar los servicios:

Tener instalado JDK 25.

Descargar o descomprimir el proyecto RedSocialUniversitaria-main.

Verificar que la carpeta lib esté junto al proyecto, ya que ahí están las dependencias (Grizzly, Jersey, Jackson, Jakarta, etc.).

### 1.2. Ejecución desde un IDE (lo más sencillo)

Es la forma más simple para el informe y para las pruebas:

Abrir el proyecto en el IDE que uses (por ejemplo IntelliJ IDEA, NetBeans o VS Code con la extensión de Java).

Asegurarse de que la carpeta src esté marcada como Source y que la carpeta lib esté agregada al Classpath del proyecto (en la mayoría de los IDE basta con agregar los .jar como librerías externas).

Buscar la clase app.App.

Ejecutar el método main de App.

La clase App crea un HttpServer con esta URL base:

URI.create("http://localhost:8080/api/")

Si todo está bien, el servidor queda escuchando en:

http://localhost:8080/api/

y a partir de ahí se accede a los distintos controladores (/usuarios, /publicacion, /reporte).

---

## 2. Verificación básica de que el servidor está funcionando

Una vez levantado el servidor:

Abrir un navegador y probar, por ejemplo:

http://localhost:8080/api/usuarios/listar

Si no hay datos de demo cargados, probablemente devuelva un arreglo vacío [].

También se puede probar el endpoint que carga datos de ejemplo (desde el controlador de reportes):

GET http://localhost:8080/api/reporte/cargar

Si funciona, la respuesta es un mensaje de texto parecido a:

"Datos de usuarios y relaciones cargados correctamente."

Después de eso, al volver a llamar a /usuarios/listar ya deberían aparecer varios usuarios generados automáticamente.

Con eso ya comprobás que el servidor está arriba y que los servicios responden.

---

## 3. Pruebas detalladas de los Web Services (Postman)

La base para todos los endpoints es:

http://localhost:8080/api

### 3.1. Servicios de usuarios

a) Listar usuarios

Método: GET

URL: http://localhost:8080/api/usuarios/listar

Request: sin body.

Response (ejemplo):
```json
[
  {
    "id": 1,
    "nombre": "Ana Pérez",
    "email": "ana@example.com",
    "genero": "F",
    "nacionalidad": "Uruguaya",
    "fechaRegistro": "2025-11-21T10:30:00",
    "rangoDePopularidad": 5
  },
  {
    "id": 2,
    "nombre": "Juan López",
    "email": "juan@example.com",
    "genero": "M",
    "nacionalidad": "Uruguaya",
    "fechaRegistro": "2025-11-18T16:00:00",
    "rangoDePopularidad": 3
  }
]

b) Crear usuario

Método: POST

URL: http://localhost:8080/api/usuarios/crear

Headers: Content-Type: application/json

Body (raw, JSON), por ejemplo:

{
  "id": 0,
  "nombre": "Carlos Gómez",
  "email": "carlos@example.com",
  "genero": "M" o "F",
  "nacionalidad": "Argentino"
}

Response: texto indicando si se creó o no el usuario, por ejemplo:

Se agregó el usuario con ID: 12345
Para identificar los generos, solo manejamos dos opciones M o F haciendo referiencia a masculino o femenino.

c) Editar usuario

Método: PUT

URL: http://localhost:8080/api/usuarios/editar/1

Body (JSON):

{
  "nombre": "Ana Actualizada",
  "email": "ana.actualizada@example.com",
  "genero": "F",
  "nacionalidad": "Uruguaya",
  "rangoDePopularidad": 10
}

Response: objeto Usuario actualizado en JSON.

d) Eliminar usuario

Método: DELETE

URL: http://localhost:8080/api/usuarios/eliminar/1

Response: texto indicando el resultado, por ejemplo:

Se eliminó el usuario con id: 1

e) Seguir a otro usuario

Método: POST

URL: http://localhost:8080/api/usuarios/2/seguir/3
(el usuario 2 pasa a seguir al 3).

Response: texto con el mensaje generado por la lógica de la red.

f) Ver seguidos / seguidores

Seguidos:
GET http://localhost:8080/api/usuarios/2/seguidos

Seguidores:
GET http://localhost:8080/api/usuarios/3/seguidores

Ambos devuelven arrays de Usuario en formato JSON.

g) Recomendación de amigos y amigos en común

Amigos en común:
GET http://localhost:8080/api/usuarios/1/amigosEnComun/2

Recomendación de amigos (amigos de amigos):
GET http://localhost:8080/api/usuarios/recomendarAmigos/1

Devuelven listas de usuarios recomendados o en común.

h) Notificaciones

Método: GET

URL: http://localhost:8080/api/usuarios/1/notificaciones

Response: Notificacion[] en JSON.

---

### 3.2. Servicios de publicaciones

Base URL de publicaciones:

http://localhost:8080/api/publicacion

a) Crear publicación

Método: POST

URL: http://localhost:8080/api/publicacion/crear/1/Hola%20mundo%20universitario

Acá tanto el idUsuario como el contenido se pasan en la URL como PathParam.

Response: objeto Publicacion creado.

b) Obtener publicación

Método: GET

URL: http://localhost:8080/api/publicacion/101

Response: Publicacion en JSON.

c) Comentar una publicación

Método: POST

URL:
http://localhost:8080/api/publicacion/101/Éxitos%20en%20la%20facultad/2

(usuario 2 comenta “Éxitos en la facultad” en la publicación 101).

Response: objeto Comentario con autor, contenido y fecha.

d) Listar comentarios de una publicación

Método: GET

URL: http://localhost:8080/api/publicacion/comentarios/101

Response: array de Comentario en JSON.

---

### 3.3. Servicios de reportes

Base URL de reportes:

http://localhost:8080/api/reporte

a) Cargar datos de prueba

Método: GET

URL: http://localhost:8080/api/reporte/cargar

Response: mensaje de texto confirmando la carga.

b) Usuarios registrados por rango de fechas

Método: GET

URL:
http://localhost:8080/api/reporte/usuariosporfecha/2025-11-01/2025-11-30

Formato de fecha: yyyy-MM-dd.

Response: Usuario[] con los usuarios registrados en ese rango.

c) Usuarios por género y nacionalidad

Método: GET

URL:
http://localhost:8080/api/reporte/usuariosporGeneroYNacionalidad/Femenino/Uruguaya

Response: Usuario[] filtrados.

d) Top 10 y ranking de publicaciones

Top 10:
GET http://localhost:8080/api/reporte/top10publicaciones

Ranking general:
GET http://localhost:8080/api/reporte/rankingPublicaciones

En ambos casos la respuesta es un array de Publicacion ordenado según la lógica definida en RedSocial.

e) Nivel de popularidad de un usuario

Método: GET

URL: http://localhost:8080/api/reporte/nivelPopularidad/1

Response: texto con la interpretación del nivel de popularidad del usuario (bajo, normal, medio, máximo, etc.).
