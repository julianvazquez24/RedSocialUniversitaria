package estructuras.publicaciones;
import modelos.Publicacion;
public class NodoPublicacion {
    public Publicacion dato;   
    public NodoPublicacion siguientePublicacion; 

    public NodoPublicacion(Publicacion publicacion) {
         this.dato = publicacion;
         this.siguientePublicacion = null;
    }
}