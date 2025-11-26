package controllers;
import modelos.Usuario;
import servicio.usuarioService;

import java.util.Date;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType; //Utilizamos para definir los tipos de datos que vamos a manejar
import jakarta.ws.rs.Path; //extension que nos ayuda a definir las rutas
import jakarta.ws.rs.Produces;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON) //Indica que lo que retorna lo transforma a JSON
@Consumes(MediaType.APPLICATION_JSON) //Indica que recibe peticiones de tipo JSON
public class UsuariosControllers {
    
    private usuarioService usuarioService = new usuarioService();

    @POST
    @Path("/crear")
    public String crearUsuario(Usuario usuario) {
        if (usuario == null) {
            return "Error: El usuario no puede ser nulo.";
        }

        usuario = usuarioService.validarUsuario(usuario);

        return "Usuario creado con ID: " + usuario.getId();
         
    }
}
