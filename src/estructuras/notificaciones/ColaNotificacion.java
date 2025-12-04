//En este caso utiilizamos una cola, porque las notificaciones tiene la particularidad
//de que la primera notifiacion que entra es la primera que se procesa
//Esto ademas puede escalar sin necesidad de andar redimensionando
//La complejidad temporal es O(1), dado que no neceista recorrer la lista. 



package estructuras.notificaciones;
import modelos.Notificacion;
public class ColaNotificacion {

    private NodoNotificacion frente; //el primero en salir
    private NodoNotificacion fondo; //el ultimo en llegar
    private int cantidad;

    public ColaNotificacion() {
        this.frente = null;
        this.fondo = null;
        this.cantidad = 0;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }

    public void ponerEnLaCola(Notificacion notificacion) { //la nueva notificacion la ubicamos a lo ultimo de todo, respetando la cola
        NodoNotificacion nuevo = new NodoNotificacion(notificacion);
        
        if (estaVacia()) { //si no hay nada en la cola, el nodo seria el mismo en ambas posiciones
            frente = nuevo;
            fondo = nuevo;
        } else { //el anterior al ultimo va a puntar al nuevo ultimo(nuevo) y el fondo va ser el utlimo(nuevo)
            fondo.siguiente = nuevo;
            fondo = nuevo;
        }

        cantidad++; //aumentamos el contador, controlar el largo.
    }

     public Notificacion sacarDelFrente() { //retiramos el primero de la cola
        if (estaVacia()) {
            return null;
        }

        Notificacion dato = frente.dato; //almacenamos la notificacion, es decir, el msj
        frente = frente.siguiente; //cambiamos el puntero para el siguiente, asi queda primero el que le sigue

        if (frente == null) { //validamos que el frente no sea null, sino la lista esta vacia
            fondo = null;
        }

        cantidad--; //retiramos la notificacion ya consumida
        return dato; //retornamos la notificacion vista
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public Notificacion[] listaNotificaciones() { //recorremos todas las notificaciones
        Notificacion[] notificaciones = new Notificacion[cantidad];
        NodoNotificacion actual = frente;
        int i = 0;
        while (actual != null) {
            notificaciones[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        }
        return notificaciones;
    }
}