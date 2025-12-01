package controllers;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import modelos.Publicacion;
import static app.App.red;

@Path("/publicacion")
@Produces(MediaType.APPLICATION_JSON) //Indica que lo que retorna lo transforma a JSON
@Consumes(MediaType.APPLICATION_JSON) //Indica que recibe peticiones de tipo JSON
public class PublicacionController {    
          
    @POST
    @Path("/crear/{idUsuario}/{contenido}")
    public Publicacion crearPublicacion(  @PathParam("idUsuario") int idUsuario,
                                        @PathParam("contenido") String contenido) {
            
                if (contenido == null || contenido.isEmpty()) {
                    return null; // O lanzar una excepción según el manejo de errores que prefieras
                }
                
                Publicacion nuevaPublicacion = red.crearPublicacion(idUsuario, contenido);

                return nuevaPublicacion;
    }

    
}
