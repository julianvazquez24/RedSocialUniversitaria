package modelos;
import java.util.*;;

public class Comentario {
    private int id;
    private Usuario autor;
    private String contenido;
    private Date fecha;

    public Comentario(int id, Usuario autor, String contenido, Date fecha) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Usuario getAutor() { 
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
    }

}
