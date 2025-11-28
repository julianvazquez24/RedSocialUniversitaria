package controllers;
import modelos.Usuario;
import servicio.usuarioService;
import redSocial.RedSocial;

import java.util.Date;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType; //Utilizamos para definir los tipos de datos que vamos a manejar
import jakarta.ws.rs.Path; //extension que nos ayuda a definir las rutas
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON) //Indica que lo que retorna lo transforma a JSON
@Consumes(MediaType.APPLICATION_JSON) //Indica que recibe peticiones de tipo JSON
public class UsuariosControllers {
    
    private usuarioService usuarioService = new usuarioService();
    private RedSocial redSocial = new RedSocial();


    @POST
    @Path("/crear")
    public String crearUsuario(Usuario usuario) {
        if (usuario == null) {
            return "Error: El usuario no puede ser nulo.";
        }

        usuario = usuarioService.validarUsuario(usuario);

        Usuario usuarioCreado = redSocial.registrarUsuario(usuario);
        if (usuarioCreado == null) {
            return "Error: No se pudo crear el usuario. Capacidad máxima alcanzada.";
        }

        return "Se agregó el usuario con ID: " + usuarioCreado.getId();
         
    }

    @DELETE
    @Path("/eliminar/{id}")
    public String eliminarUsuario( @PathParam("id") int idUsuario){
        if(idUsuario <= 0){
            return "No existe un usuario con este id +: " + idUsuario;
        }

        Boolean eliminado = redSocial.eliminarUsuario(idUsuario);
        
        if(!eliminado){
            return "No se pudo eliminar el usuario con id: " + idUsuario;
        }   

        return "Se eliminó el usuario con id: " + idUsuario;
    }
}
