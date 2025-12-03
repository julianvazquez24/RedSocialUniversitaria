package controllers;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import modelos.Usuario;
import jakarta.ws.rs.PathParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import static app.App.red;

@Path("/reporte")
@Produces(MediaType.APPLICATION_JSON) // Indica que lo que retorna lo transforma a JSON
public class ReporteController {
  
    @GET
    @Path("/usuariosporfecha/{fechaI}/{fechaF}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario[] usuariosPorFecha(@PathParam("fechaI") String fechaI,
                                    @PathParam("fechaF") String fechaF) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try { //utilizamos este try dado que neceistabamos manejar un excepction en caso de que no pueda hacer la conversion, lo
            Date fI = formato.parse(fechaI); //lo pedia el editor de codigo, el lenguaje en si
            Date fF = formato.parse(fechaF);

            return red.cantidadDeUsuarios(fI, fF);

        } catch (Exception e) {
            e.printStackTrace(); //muestra la marca en donde fue fallando el error
            return new Usuario[0];
        }
    }


    @GET
    @Path("/cargar")
    @Produces(MediaType.TEXT_PLAIN)
    public String cargarDatosDemo() {

        red.cargarUsuariosDemo();

        return "Datos de usuarios y relaciones cargados correctamente.";
    }
}
