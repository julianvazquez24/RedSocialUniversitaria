package controllers;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import modelos.Comentario;
import modelos.Publicacion;
import static app.App.red;

@Path("/publicacion")
@Produces(MediaType.APPLICATION_JSON) // Indica que lo que retorna lo transforma a JSON
@Consumes(MediaType.APPLICATION_JSON) // Indica que recibe peticiones de tipo JSON
public class PublicacionController {

    @POST
    @Path("/crear/{idUsuario}/{contenido}")
    public Publicacion crearPublicacion(@PathParam("idUsuario") int idUsuario,
            @PathParam("contenido") String contenido) {

        if (contenido == null || contenido.isEmpty()) {
            return null; // O lanzar una excepción según el manejo de errores que prefieras
        }

        Publicacion nuevaPublicacion = red.crearPublicacion(idUsuario, contenido);

        return nuevaPublicacion;
    }

    @GET
    @Path("/{idPub}")
    public Publicacion obtenerPublicacion(@PathParam("idPub") int idPub) {

        if (idPub < 0) {
            return null;
        }

        Publicacion publicacion = red.obtenerPublicacion(idPub);

        return publicacion;
    }

    @POST
    @Path("/{idPub}/{texto}/{idUsuario}")
    public Comentario comentar(@PathParam("idPub") int idPub,
            @PathParam("idUsuario") int idUsuario,
            @PathParam("texto") String texto) {
        
        if (texto == null || texto.isBlank() || idPub < 0 || idUsuario < 0) {
            return null;
        }

        Comentario comentar = red.comentarPublicacion(idUsuario, idPub, texto);

        return comentar;

    }

    @GET
    @Path("/comentarios/{idPub}")
    public Comentario[] obtenerComentarios(@PathParam("idPub") int idPub) {
        Publicacion publicacion = red.obtenerPublicacion(idPub);

        if (publicacion == null || idPub < 0) {
            return new Comentario[0];
        }

        return publicacion.getComentarios().listaComentarios();
    }

}
