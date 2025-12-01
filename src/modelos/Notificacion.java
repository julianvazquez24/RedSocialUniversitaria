package modelos;

import java.util.Date;

public class Notificacion {
    private int id;
    private String tipo;       //ewl tipo va a ser nuevo comentario o nuevo seguidor
    private String mensaje;
    private Date fecha;
    private Usuario origen;
    //private Publicacion publicacion;   // puede ser null

    public Notificacion(int id, String tipo, String mensaje, Date fecha, Usuario origen) {
        this.id = id;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.origen = origen;
     //   this.publicacion = publicacion;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getMensaje() { return mensaje; }
    public Date getFecha() { return fecha; }
    public Usuario getOrigen() { return origen; }
   // public Publicacion getPublicacion() { return publicacion; }
}
