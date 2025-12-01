package estructuras.comentarios;

//En este caso utilizamos una lista represtando una fila, agreegando al final
//dado que el nuevo comentario aparece a lo utlimo
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


