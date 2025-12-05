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

