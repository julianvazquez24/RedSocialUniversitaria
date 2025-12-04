/*Creamos esta clase con el fin de desacoplar responsabilidades,
Ejecuta la ligica de cada componente y estructura maneja la red, 
es la base de esta red social universitaria  */



package redSocial;
import estructuras.publicaciones.*;
import estructuras.usuarios.*;
import modelos.Usuario;
import modelos.Comentario;
import modelos.Notificacion;
import modelos.Publicacion;

import static app.App.red;

import java.util.Date;

public class RedSocial {

    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private GrafoUsuarios grafo;
    private mapaPublicacion mapaPublicacion;

    public RedSocial() {
        this.usuarios = new Usuario[100]; // Capacidad inicial
        this.cantidadUsuarios = 0;

        this.grafo = new GrafoUsuarios(100);
        this.mapaPublicacion = new mapaPublicacion(101);
    }

    private int generarIdNotificacion() {
        return (int) (Math.random() * 1000000);
    }

    private int generarIdPublicacion() {
        return (int) (Math.random() * 100000);
    }

    private int generarIdComentario() {
        return (int)(Math.random() * 1000000);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        if (cantidadUsuarios >= usuarios.length) {
            return null;
        }

        usuarios[cantidadUsuarios] = usuario;
        cantidadUsuarios++;

        grafo.agregarUsuario(usuario);

        return usuario;
    }

    public Boolean eliminarUsuario(int idUsuario) {
        int userId = -1;
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getId() == idUsuario) {
                userId = i;
                break;
            }
        }

        if (userId == -1) {
            return false;
        }

        //Corremos un lugar hacia la izquierda, desde la posicion que queremos eliminar
        for (int i = userId; i < cantidadUsuarios - 1; i++) {
            usuarios[i] = usuarios[i + 1];
        }

        usuarios[cantidadUsuarios - 1] = null;
        cantidadUsuarios--;

        grafo.eliminarUsuario(idUsuario);

        return true;
    }

    public Usuario[] listarUsuarios() {
        Usuario[] listaUsuarios = new Usuario[cantidadUsuarios];
        for (int i = 0; i < cantidadUsuarios; i++) {
            listaUsuarios[i] = usuarios[i];
        }
        return listaUsuarios;
    }

    public Usuario buscarUsuario(int id) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getId() == id)
                return usuarios[i];
        }
        return null;
    }

    public String seguirUsuario(int idSeguidor, int idSeguido) {
        grafo.seguir(idSeguidor, idSeguido);

        //tambien avisamos al usuario de la solicitud, notificandolo
        Usuario seguidor = buscarUsuario(idSeguidor);
        Usuario seguido = buscarUsuario(idSeguido);

        if (seguido != null && seguidor != null) {
            Notificacion nuevaNotificacion = new Notificacion(
                    generarIdNotificacion(),
                    "Nuevo Seguidor",
                    seguidor.getNombre() + " comenzo a seguirte",
                    new Date(),
                    seguidor,
                    null       
            );
            seguido.getColaNotificaciones().ponerEnLaCola(nuevaNotificacion);
            return ("Agregado el usuario " + idSeguido + " Correctamente ");
        }
        return ("Error en la petición");

    }

    public Notificacion[] mostrarNotificaciones(int idUsuario) {
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario == null) {
            return new Notificacion[0];
        }

        Notificacion[] listaNotificaciones = usuario.getColaNotificaciones().listaNotificaciones();
        return listaNotificaciones;
    }

    public Usuario[] obtenerSeguidores(int idUsuario) {
        return grafo.obtenerSeguidores(idUsuario);
    }

    public Usuario[] obtenerSeguidos(int idUsuario) {
        return grafo.obtenerSeguidos(idUsuario);
    }

    public Usuario[] amigosEnComun(int UsuarioA, int usuarioB) {
        return grafo.amigosEnComun(UsuarioA, usuarioB);
    }

    public Usuario[] recomendarAmigosDeAmigos(int idUsuario){
        return grafo.recomendarAmigosDeAmigos(idUsuario);
    }
    
    public Notificacion[] obtenerNotificaciones(int idUsuario) {
        Usuario usuario = buscarUsuario(idUsuario);

        if (usuario == null) {
            return new Notificacion[0];
        }

        return usuario.getColaNotificaciones().listaNotificaciones();
    }

    public Publicacion crearPublicacion(int idUsuario, String contenido) {
        
        Usuario autor = buscarUsuario(idUsuario);

        if (autor == null) {
            return null;
        }

        int idPub = generarIdPublicacion();
        Publicacion publicacion = new Publicacion(idPub, autor, contenido, new Date());

        mapaPublicacion.agregarPublicacion(publicacion);

        return publicacion;

    }

    public Publicacion obtenerPublicacion(int idPublicacion) {
        Publicacion publicacion = mapaPublicacion.buscarPublicacion(idPublicacion);
        return publicacion;
    }

    public Comentario comentarPublicacion(int idUsuario, int idPublicacion, String texto) {
        Usuario autorComment = buscarUsuario(idUsuario);
        Publicacion publicacion = obtenerPublicacion(idPublicacion);

        if (autorComment == null || publicacion == null) {
            return null;
        }

         Comentario comentario = new Comentario(
            generarIdComentario(),
            autorComment,
            texto,
            new Date()
        );

        // agregamos el comentario a la lista que pertenece a la instanica de la publicacion
        publicacion.getComentarios().agregarAlFinal(comentario);
    
        ///una vez ya realizado el comnentario le avisamos al usuario, creando una uneva notificacion
        Usuario autorDeLaPublicacion = publicacion.getAutor();

        if (autorDeLaPublicacion != null && autorDeLaPublicacion.getId() != autorComment.getId()) {
            Notificacion notificacion = new Notificacion(
                generarIdNotificacion(),
                "NUEVO_COMENTARIO",
                autorComment.getNombre() + " comentó tu publicación",
                new Date(),
                autorComment,
                publicacion
            );
            autorDeLaPublicacion.getColaNotificaciones().ponerEnLaCola(notificacion);
        }

        return comentario;

    }

    public Usuario[] cantidadDeUsuarios(Date fechaI, Date fechaF){       
        int contador = 0;

        for( int i = 0; i < cantidadUsuarios; i++){
            Date fechaRegistro = usuarios[i].getFechaRegistro();
            if(fechaRegistro.compareTo(fechaI) >= 0 && fechaRegistro.compareTo(fechaF) <= 0){
                contador++;

            }
        }

        Usuario[] listaFinal = new Usuario[contador];
        int contadorFinal = 0;
        for( int i = 0; i < cantidadUsuarios; i++){
            Date fechaRegistro = usuarios[i].getFechaRegistro();
            if(fechaRegistro.compareTo(fechaI) >= 0 && fechaRegistro.compareTo(fechaF) <= 0){
                listaFinal[contadorFinal] = usuarios[i];
                contadorFinal++;
            }
        } 

        for (int i = 0; i < listaFinal.length; i++) {
            for (int j = i + 1; j < listaFinal.length; j++) {
                if (listaFinal[i].getFechaRegistro().compareTo(listaFinal[j].getFechaRegistro()) > 0) {
                    Usuario temp = listaFinal[i];
                    listaFinal[i] = listaFinal[j];
                    listaFinal[j] = temp;
                }
            }
        }
        
        return listaFinal;
    }

    public Usuario[] filtroPorGeneroYNacionalidad(String genero, String nacionalidad){

        int contador = 0;

        for(int i=0; i < cantidadUsuarios; i++){
            String generoUsuario = usuarios[i].getGenero();
            String nacionalidadUsuario = usuarios[i].getNacionalidad();

            if (generoUsuario.equals(genero) && nacionalidadUsuario.equals(nacionalidad)){
                contador++; 
            }
        }

        Usuario[] listaFinal = new Usuario[contador];
        int contadorFinal = 0;
        for (int i=0; i< cantidadUsuarios; i++){
            
            String generoUsuario = usuarios[i].getGenero();
            String nacionalidadUsuario = usuarios[i].getNacionalidad();
            if (generoUsuario.equals(genero) && nacionalidadUsuario.equals(nacionalidad)){
                listaFinal[contadorFinal] = usuarios[i];
                contadorFinal++;
            }
        }

        return listaFinal;
    }

    private Publicacion[] obtenerTodasLasPublicaciones() {
        //realizamos este metodo privado para poder utilizarlo en dos consultas, y pasamos a un array para poder iterar sobre ellos


        listaPublicaciones[] listas = mapaPublicacion.almacenamientoPublicaciones; //traemos  el array de nodos

        int total = 0;

        for (int i = 0; i < listas.length; i++) { //recorremos el array, para acceder a cada nodo hasta llegar al null, si es null
            NodoPublicacion actual = listas[i].cabeza; //seguimos con otra posición del array. 
            while (actual != null) {
                actual = actual.siguientePublicacion;
                total++;
            }
        }

        Publicacion[] arr = new Publicacion[total]; // le pasamos el total de nodos del array 
        int pos = 0;

         for (int i = 0; i < listas.length; i++) { //para posteriormente guardarlos a todos en un nuevo array
            NodoPublicacion actual = listas[i].cabeza;
            while (actual != null) {
                arr[pos] = actual.dato;
                pos++;
                actual = actual.siguientePublicacion;
            }
        }



        return arr;
    }

    private void ordenarPublicacionesPorComentarios(Publicacion[] arr) {
        //Ordenamos los publicaciones por cantidad de comentarios
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {

                int c1 = arr[j].getComentarios().getCantidad();
                int c2 = arr[j + 1].getComentarios().getCantidad();

                if (c1 < c2) {
                    Publicacion aux = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = aux;
                } 
            }
        }
    }

    public Publicacion[] rankingPublicaciones() {

    Publicacion[] publicacionesOrdenadas = obtenerTodasLasPublicaciones();
    ordenarPublicacionesPorComentarios(publicacionesOrdenadas);

    return publicacionesOrdenadas;
}
    
    public Publicacion[] topPublicaciones() {

        Publicacion[] publicacionesOrdenadas = obtenerTodasLasPublicaciones();
        ordenarPublicacionesPorComentarios(publicacionesOrdenadas);

        int limitePublicaciones = 0; 

        if(publicacionesOrdenadas.length < 10){
            limitePublicaciones = publicacionesOrdenadas.length;
        }else{
           limitePublicaciones = 10;
        }

        

        Publicacion[] top10Publicacions = new Publicacion[limitePublicaciones];
        for (int i = 0; i < limitePublicaciones; i++) {
            top10Publicacions[i] = publicacionesOrdenadas[i];
        }

        return top10Publicacions;
    }

    public void cargarUsuariosDemo() {
        if (cantidadUsuarios > 0)
            return; //evitamos precarga

        
        Usuario u1 = new Usuario(1, "Ana", "ana@mail.com", "F", "Uruguay",
            new Date(2023 - 1900, 0, 15));   // 15/01/2023

        Usuario u2 = new Usuario(2, "Luis", "luis@mail.com", "M", "Argentina",
            new Date(2023 - 1900, 5, 10));   // 10/06/2023

        Usuario u3 = new Usuario(3, "Pedro", "pedro@mail.com", "M", "Chile",
            new Date(2024 - 1900, 2, 5));    // 05/03/2024

        Usuario u4 = new Usuario(4, "María", "maria@mail.com", "F", "Uruguay",
            new Date(2024 - 1900, 8, 20));   // 20/09/2024

        Usuario u5 = new Usuario(5, "Lucía", "lucia@mail.com", "F", "México",
            new Date(2025 - 1900, 1, 28));   // 28/02/2025

        registrarUsuario(u1);
        registrarUsuario(u2);
        registrarUsuario(u3);
        registrarUsuario(u4);
        registrarUsuario(u5);

         
        seguirUsuario(1, 2);
        seguirUsuario(1, 3);

         seguirUsuario(2, 4); // Luis -> María
        seguirUsuario(3, 4); // Pedro -> María
        seguirUsuario(3, 5); // Pedro -> Lucía

         seguirUsuario(4, 5); // María -> Lucía

         
        Publicacion p1 = crearPublicacion(1, "Hola, soy Ana, probando la red social 😄");
        Publicacion p2 = crearPublicacion(2, "Buen día, acá Luis programando en Java.");
        Publicacion p3 = crearPublicacion(3, "Hoy estuve estudiando grafos dirigidos.");
        Publicacion p4 = crearPublicacion(4, "Hermoso día para tomar unos mates y codear.");
        Publicacion p5 = crearPublicacion(5, "Lucía saluda a todos desde México 🌎");
 
        if (p1 != null) {
            comentarPublicacion(2, p2.getId(), "Bienvenida Ana!");
            comentarPublicacion(3, p1.getId(), "Hola Ana, ya te sigo 😁");
        }

         if (p3 != null) {
            comentarPublicacion(1, p3.getId(), "Los grafos me están matando 😂");
            comentarPublicacion(4, p2.getId(), "A mí también, pero están buenos.");
        }

         if (p5 != null) {
            comentarPublicacion(1, p2.getId(), "¡Saludos desde Uruguay!");
            comentarPublicacion(2, p2.getId(), "Qué bueno, Lucía 😎");
        }

        System.out.println("Usuarios, relaciones, publicaciones y comentarios de prueba creados correctamente.");
    }

}

