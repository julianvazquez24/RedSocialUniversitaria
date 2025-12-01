package estructuras.comentarios;

import modelos.Comentario;

public class NodoComentario {
      Comentario dato;
    NodoComentario siguiente;

    public NodoComentario(Comentario dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
