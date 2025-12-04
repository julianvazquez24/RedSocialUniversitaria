//Optamos por utilizar listas enlazada, dado que nunca vamos a saber largo que va a tener, por ende en este caso
//seguimos direccionando el puntero o otro o le asignamos null, controlando asi su escalabilidad
//la estructura es una pila la insercion es al inicio, su complejidad temporal es O(1),
// Las operaciones de busqueda y verificación de existencia requieren recorrer la lista completa,
// por lo cual su complejidad temporal es O(n).


package estructuras.publicaciones;
import modelos.Publicacion;

public class listaPublicaciones {

    public NodoPublicacion cabeza;
    
    public listaPublicaciones() {
        cabeza = null;
    }

    public void agregar(Publicacion publicacion) {
        NodoPublicacion nuevo = new NodoPublicacion(publicacion);
        nuevo.siguientePublicacion = cabeza;
        cabeza = nuevo;
    }

    public Publicacion buscar(int id) {
        NodoPublicacion actual = cabeza;
        while (actual != null) {
            if (actual.dato.getId() == id) {
                return actual.dato;
            }
            actual = actual.siguientePublicacion;
        }
        return null;
    }

    public boolean existe(int id) {
        Publicacion respuesta = buscar(id);
        if(respuesta != null)
        {
            return true;
        }
        return false;

    }
}