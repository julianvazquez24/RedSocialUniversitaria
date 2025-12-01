package servicio;
import modelos.Usuario;
import java.util.Date;
import static app.App.red;
public class usuarioService {

    // aca vamos a hacer funciones que correspondan al usuario

    public Usuario validarUsuario(Usuario usuario) {

        // Si no viene id, generamos uno aleatorio
        if (usuario.getId() == 0) {
            int id = (int) (Math.random() * 1_000_000);
            usuario.setId(id);
        }

        // Fecha de registro por defecto
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(new Date());
        }

        // Inicializar cola de notificaciones si no viene
        // if (usuario.getColaNotificaciones() == null) {
        //     usuario.setColaNotificaciones(new estructuras.notificacion.ColaNotificaciones());
        // }

        return usuario;
    }
    
    
    public Usuario actualizarUsuario(int id, Usuario datosModificado) {

    Usuario existente = red.buscarUsuario(id);
    if (existente == null) {
        return null; // por ahora lo manejamos asi, seria mejor manejar un string especificando el error
    }

    existente.setNombre(datosModificado.getNombre());
    existente.setEmail(datosModificado.getEmail());
    existente.setGenero(datosModificado.getGenero());
    existente.setNacionalidad(datosModificado.getNacionalidad());
    existente.setFechaRegistro(datosModificado.getFechaRegistro());

    return existente; 
}

}
