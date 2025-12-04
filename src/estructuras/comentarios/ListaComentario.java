//En este caso optamos por utilizar una cola, dado que cada nuevo comnetario se apila al final
//Esto permite agregar nodos al final, sin necesidad de mover o copiar elementos. Tambien
//mantenemos el orden cronologico de los comentarios, nos era mas practico de utilizar. La operacion de insercion es O(n)




package estructuras.comentarios;
import modelos.Comentario;
            
public class ListaComentario {
    
    private NodoComentario cabeza;
    private int cantidad;

    public ListaComentario() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public void agregarAlFinal(Comentario comentario) {
        NodoComentario nuevo = new NodoComentario(comentario);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoComentario actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        cantidad++;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Comentario[] listaComentarios() {
        Comentario[] comentarios = new Comentario[cantidad];
        NodoComentario actual = cabeza;
        int i = 0;
        while (actual != null) {
            comentarios[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        }
        return comentarios;
    }
}


