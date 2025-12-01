/*Creamos esta clase con el fin de desacoplar responsabilidades,
Ejecuta la ligica de cada componente y estructura maneja la red, 
es la base de esta red social universitaria  */



package redSocial;
import estructuras.usuarios.*;
import modelos.Usuario;
import modelos.Notificacion;
import java.util.Date;

public class RedSocial {

    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private GrafoUsuarios grafo; 

    public RedSocial() {
        this.usuarios = new Usuario[100]; // Capacidad inicial
        this.cantidadUsuarios = 0;

        this.grafo = new GrafoUsuarios(100);  
    }

    
    private int generarIdNotificacion() {
        return (int) (Math.random() * 1000000);
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
                    seguidor
            //publicacion: null       
            );
            seguido.getColaNotificaciones().ponerEnLaCola(nuevaNotificacion);
            return ("Agregado el usuario " + idSeguido + " Correctamente ");
        }
        return ("Error en la petición");
    
    }

    public Notificacion[] mostrarNotificaciones(int idUsuario) {
        Usuario usuario = buscarUsuario(idUsuario);
        if(usuario ==null){
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
}
