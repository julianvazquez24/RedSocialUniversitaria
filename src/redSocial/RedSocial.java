/*Creamos esta clase con el fin de desacoplar responsabilidades,
que la controller se encarge de la comunicación y las estructuras de las distintas
funciones. Esta clase ahorra codigo  */



package redSocial;
import modelos.Usuario;
import esctructuras.usuarios.*;

public class RedSocial {

    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private GrafoUsuarios grafo; 

    public RedSocial() {
        this.usuarios = new Usuario[100]; // Capacidad inicial
        this.cantidadUsuarios = 0;

        this.grafo = new GrafoUsuarios(100);  
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

    public Boolean eliminarUsuario(int idUsuario){
        int userId = -1;
        for(int i = 0; i <= cantidadUsuarios; i++){
            if(usuarios[i].getId() == idUsuario){
                userId = i;
                break;
            }
        }

        if(userId == -1){
            return false; 
        }

        for(int i = userId; i < cantidadUsuarios - 1; i++){
            usuarios[i] = usuarios[i + 1];
        }

        usuarios[cantidadUsuarios - 1] = null;
        cantidadUsuarios--;

        grafo.eliminarUsuario(idUsuario);

        return true;
    }


}
