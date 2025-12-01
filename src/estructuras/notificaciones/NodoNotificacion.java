//Optamos por cola dado que en esta sitaucion se necestia valorar la posicion predeterminado
package estructuras.notificaciones;
import modelos.Notificacion;

public class NodoNotificacion {
    public Notificacion dato;
    public NodoNotificacion siguiente;

    public NodoNotificacion(Notificacion dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
