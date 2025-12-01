package estructuras.publicaciones;

//Optamos por utilizar listas enlazada, dado que nunca vamos a saber largo que va a tener, por ende en este caso
//seguimos direccionando el puntero o otro o le asignamos null, controlando asi su escalabilidad
//la estructura es una pila la insercion es al inicio

import modelos.Publicacion;

class listaPublicaciones {

    private NodoPublicacion cabeza;
    
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