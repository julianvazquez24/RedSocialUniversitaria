package servicio;
import modelos.Usuario;
import java.util.Date;
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
    
}
