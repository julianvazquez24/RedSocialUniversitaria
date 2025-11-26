package modelos;
import java.util.Date;

public class Usuario {
    public int Id;
    public String Nombre;
    public String Email;
    public String Genero;
    public String Nacionalidad;
    public Date FechaRegistro;

    public Usuario(int id, String nombre, String email, String genero, String nacionalidad, Date fechaRegistro) {
        this.Id = id;
        this.Nombre = nombre;
        this.Email = email;
        this.Genero = genero;
        this.Nacionalidad = nacionalidad;
        this.FechaRegistro = fechaRegistro;
    }

    public Usuario() {    }
    // aca vamos a hacer los getters y setters para traer info luego y no hacerlo en otras clases o controllers

    // getters
    public int getId() {
        return Id;
    }
    public String getNombre() {
        return Nombre;
    }
    public String getEmail() {
        return Email;
    }
    public String getGenero() {
        return Genero;
    }
    public String getNacionalidad() {
        return Nacionalidad;
    }
    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    
    // setters
    public void setId(int id) {
        this.Id = id;
    }
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setGenero(String genero) {
        this.Genero = genero;
    }
    public void setNacionalidad(String nacionalidad) {
        this.Nacionalidad = nacionalidad;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.FechaRegistro = fechaRegistro;
    }




}


