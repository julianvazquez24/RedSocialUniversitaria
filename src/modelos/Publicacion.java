package modelos;
import estructuras.*;
import estructuras.comentarios.ListaComentario;

import java.util.Date;

public class Publicacion {
    private int id;
    private String autor;
    private String contenido;
    private Date fecha;
    private ListaComentario comentarios;

    public Publicacion(int id, Usuario autor, String contenido, Date fecha) {
        this.id = id;
        this.autor = autor.getNombre();
        this.contenido = contenido;
        this.fecha = fecha;
        this.comentarios = new ListaComentario();
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

    public ListaComentario getComentarios() {
        return comentarios;
    }
    
    
}
