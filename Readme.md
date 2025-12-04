# API REST — Red Social Universitaria (JAX-RS)

Este proyecto implementa un conjunto de Web Services REST utilizando JAX-RS sin frameworks externos como Maven o Spring Boot.  
La API permite gestionar usuarios, publicaciones, comentarios, seguidores, notificaciones y reportes.

---

## 1. Compilación y Ejecución del Servidor

La API se ejecuta mediante Java + JAX-RS utilizando archivos `.jar` que levantan el servidor embebido.

**Para ejecutar la API:**

1. Tener instalado **JDK 25**.
2. Abrir el proyecto.
3. Ejecutar la clase:


app.App
El servidor se inicia automáticamente en:


http://localhost:8080/api/
2. Pruebas de la API
Para testear los servicios se utilizó Postman, enviando peticiones HTTP a los distintos endpoints de la API.

3. Endpoints de Usuarios
Base URL:


http://localhost:8080/api/usuarios
Crear Usuario
POST /usuarios/crear

Body JSON obligatorio:
*Para identificar el genero utilizamos las siglas masculino (M) y el feminino (F). 
{
  "nombre": "juli",
  "email": "juli@gmail.com",
  "genero": "M", //O "F" 
  "nacionalidad": "uruguay"
}
Respuesta (ejemplo):


Se agregó el usuario con ID: X
Eliminar Usuario
DELETE /usuarios/eliminar/{idUsuario}

Editar Usuario
PUT /usuarios/editar/{idUsuario}

Body: JSON con los campos a modificar.

Listar Usuarios
GET /usuarios/listar

Obtener Usuario
GET /usuarios/{idUsuario}

Seguir Usuario
POST /usuarios/{idSeguidor}/seguir/{idSeguido}

Obtener Seguidos
GET /usuarios/{id}/seguidos

Obtener Seguidores
GET /usuarios/{id}/seguidores

Amigos en Común
GET /usuarios/{id1}/amigosEnComun/{id2}

Recomendación de Amigos
GET /usuarios/recomendarAmigos/{id}

Notificaciones
GET /usuarios/{id}/notificaciones

4. Endpoints de Publicaciones
Base URL:


http://localhost:8080/api/publicacion
Crear Publicación
POST /publicacion/crear/{idUsuario}/{contenido}

Obtener Publicación
GET /publicacion/{idPublicacion}

Comentar Publicación
POST /publicacion/{idPub}/{texto}/{idUsuario}

Obtener Comentarios
GET /publicacion/comentarios/{idPublicacion}

5. Endpoints de Reportes
Base URL:


http://localhost:8080/api/reporte
Cargar Datos de Prueba
GET /reporte/cargar

Usuarios por Fecha
GET /reporte/usuariosporfecha/{fechaInicio}/{fechaFin}

Formato de fecha: yyyy-MM-dd.

Filtrar por Género y Nacionalidad
GET /reporte/usuariosporGeneroYNacionalidad/{genero}/{nacionalidad}

Top 10 Publicaciones
GET /reporte/top10publicaciones

Ranking de Publicaciones
GET /reporte/rankingPublicaciones

Nivel de Popularidad
GET /reporte/nivelPopularidad/{idUsuario}

6. Validaciones Implementadas
Campos vacíos → retornan [] o mensajes claros.

IDs negativos → no se procesan.

JSON inválido → no se crea el usuario.

Se evitan operaciones incoherentes (seguirse a sí mismo, publicaciones vacías, etc.).

7. Herramientas Utilizadas
Java

JAX-RS

Postman

8. Tabla General de Endpoints


| Método | Endpoint                                                          | Descripción                             |
| ------ | ----------------------------------------------------------------- | --------------------------------------- |
| GET    | `/usuarios/listar`                                                | Lista todos los usuarios                |
| POST   | `/usuarios/crear`                                                 | Crea un nuevo usuario (requiere JSON)   |
| DELETE | `/usuarios/eliminar/{id}`                                         | Elimina usuario por ID                  |
| PUT    | `/usuarios/editar/{id}`                                           | Edita usuario                           |
| GET    | `/usuarios/{id}`                                                  | Obtiene un usuario                      |
| POST   | `/usuarios/{idSeguidor}/seguir/{idSeguido}`                       | Un usuario sigue a otro                 |
| GET    | `/usuarios/{id}/seguidos`                                         | Lista seguidos                          |
| GET    | `/usuarios/{id}/seguidores`                                       | Lista seguidores                        |
| GET    | `/usuarios/{id1}/amigosEnComun/{id2}`                             | Amigos en común                         |
| GET    | `/usuarios/recomendarAmigos/{id}`                                 | Recomendación de amigos                 |
| GET    | `/usuarios/{id}/notificaciones`                                   | Notificaciones del usuario              |
| POST   | `/publicacion/crear/{idUsuario}/{contenido}`                      | Crear publicación                       |
| GET    | `/publicacion/{idPub}`                                            | Obtener publicación                     |
| POST   | `/publicacion/{idPub}/{texto}/{idUsuario}`                        | Comentar publicación                    |
| GET    | `/publicacion/comentarios/{idPub}`                                | Obtener comentarios                     |
| GET    | `/reporte/cargar`                                                 | Carga datos de prueba                   |
| GET    | `/reporte/usuariosporfecha/{fechaI}/{fechaF}`                     | Usuarios registrados en rango de fechas |
| GET    | `/reporte/usuariosporGeneroYNacionalidad/{genero}/{nacionalidad}` | Filtrar usuarios                        |
| GET    | `/reporte/top10publicaciones`                                     | Top 10 publicaciones                    |
| GET    | `/reporte/rankingPublicaciones`                                   | Ranking general                         |
| GET    | `/reporte/nivelPopularidad/{id}`                                  | Nivel de popularidad                    |
