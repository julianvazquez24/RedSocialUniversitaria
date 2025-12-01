package modelos;

import java.util.Date;
import estructuras.notificaciones.ColaNotificacion;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String genero;
    private String nacionalidad;
    private Date fechaRegistro;

    private ColaNotificacion colaNotificaciones;


    public Usuario(int id, String nombre, String email, String genero, String nacionalidad, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.fechaRegistro = fechaRegistro;
        this.colaNotificaciones = new ColaNotificacion();

    }

    public Usuario() {
        this.colaNotificaciones = new ColaNotificacion();

    }
    // aca vamos a hacer los getters y setters para traer info luego y no hacerlo en otras clases o controllers

    // getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getGenero() {
        return genero;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public ColaNotificacion getColaNotificaciones() {
        return colaNotificaciones;
    }


    
    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }




}


