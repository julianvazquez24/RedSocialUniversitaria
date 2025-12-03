package controllers;
import modelos.Notificacion;
import modelos.Usuario;
import servicio.usuarioService;
import static app.App.red;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType; //Utilizamos para definir los tipos de datos que vamos a manejar
import jakarta.ws.rs.Path; //extension que nos ayuda a definir las rutas
import jakarta.ws.rs.PathParam;
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

        Usuario usuarioCreado = red.registrarUsuario(usuario);
        if (usuarioCreado == null) {
            return "Error: No se pudo crear el usuario. Capacidad máxima alcanzada.";
        }

        return "Se agregó el usuario con ID: " + usuarioCreado.getId();
         
    }

    @DELETE
    @Path("/eliminar/{id}")
    public String eliminarUsuario(@PathParam("id") int idUsuario) {
        if (idUsuario <= 0) {
            return "No existe un usuario con este id +: " + idUsuario;
        }

        Boolean eliminado = red.eliminarUsuario(idUsuario);

        if (!eliminado) {
            return "No se pudo eliminar el usuario con id: " + idUsuario;
        }

        return "Se eliminó el usuario con id: " + idUsuario;
    }

    @GET
    @Path("/listar")
    public Usuario[] listarUsuarios() {
        Usuario[] listaUsuarios = red.listarUsuarios();

        if (listaUsuarios.length == 0) {
            return new Usuario[0];
        }
        return listaUsuarios;
    }

    
    @PUT
    @Path("/editar/{id}")
    public Usuario editarUsuario(@PathParam("id") int idUsuario, Usuario datosModificado) {

        return usuarioService.actualizarUsuario(idUsuario, datosModificado);
    }
    
     @GET
    @Path("/{id}")
     public Usuario obtenerUsuario(@PathParam("id") int id) {

         Usuario usuario = red.buscarUsuario(id);

         if (usuario == null) {
             return new Usuario();
         }

         return usuario;
     }

    @POST
    @Path("/{idSeguidor}/seguir/{idSeguido}")
    public String seguir(@PathParam("idSeguidor") int seguidor,
                         @PathParam("idSeguido") int seguido) 
    {
        if (seguidor == seguido) {
            return ("No te pueden seguir a tu mismo");
        }

        String respuesta = red.seguirUsuario(seguidor, seguido);

        return respuesta;
    }

    @GET
    @Path("/{id}/seguidos")
    public Usuario[] seguidos(@PathParam("id") int id) {
        Usuario[] seguidos = red.obtenerSeguidos(id);
        
        if (seguidos == null) {
            return new Usuario[0];
        }
        
        return seguidos;
    }

    @GET
    @Path("/{id}/seguidores")
    public Usuario[] seguidores(@PathParam("id") int id) {
        Usuario[] seguidores = red.obtenerSeguidores(id);
        if (seguidores == null) {
            return new Usuario[0];
        }

        return seguidores;
    }

    @GET
    @Path("/{id1}/amigosEnComun/{id2}")
    public Usuario[] amigosEnComun(@PathParam("id1") int usuarioA,
                                    @PathParam("id2") int usuarioB) {
        Usuario[] amigosEnComun = red.amigosEnComun(usuarioA, usuarioB);
       
        if (amigosEnComun == null) {
            return new Usuario[0];
        }

        return amigosEnComun;
    }

    @GET
    @Path("/recomendarAmigos/{id}")   // recomendaion de amigos a partir de los amigos de mis amigos.
    public Usuario[] recomendarAmigos(@PathParam("id") int idUsuario) {
        Usuario[] recomendaciones = red.recomendarAmigosDeAmigos(idUsuario);

        if (recomendaciones == null) {
            return new Usuario[0];
        }

        return recomendaciones;
    }
                                        
    @GET
    @Path("/{id}/notificaciones")
    public Notificacion[] notificaciones(@PathParam("id") int id) {
        Notificacion[] notificaciones = red.obtenerNotificaciones(id);

        if (notificaciones == null) {
            return new Notificacion[0];
        }

        return notificaciones;

    }                                
}
