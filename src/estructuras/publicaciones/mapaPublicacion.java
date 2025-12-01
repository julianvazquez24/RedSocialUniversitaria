package estructuras.publicaciones;
//Optamos por utilizar listas enlazada, dado que nunca vamos a saber largo que va a tener, por ende en este caso
//seguimos direccionando el puntero o otro o le asignamos null, controlando asi su escalabilidad

import modelos.Publicacion;

public class mapaPublicacion {
    private listaPublicaciones[] almacenamientoPublicaciones;
    private int capacidad;

    public mapaPublicacion(int capacidad) {
        this.capacidad = capacidad;
        this.almacenamientoPublicaciones = new listaPublicaciones[capacidad];

        for (int i = 0; i < capacidad; i++) {
            almacenamientoPublicaciones[i] = new listaPublicaciones();
        }
    }

    private int asignacionPosicion(int id) { //hash aca asignamos ala publicacion en que lista se va almacenar
        return id % capacidad; //siempre da un numero entero. 
    }

    public void agregarPublicacion(Publicacion publicacion) {
        int posicion = asignacionPosicion(publicacion.getId()); //le asignamos el array donde los vamos almacenar
        almacenamientoPublicaciones[posicion].agregar(publicacion); //direccionamos el ultimo puntero a esta publicacion. 
    }

     public Publicacion buscarPublicacion(int id) {
        int posicion = asignacionPosicion(id);
        return almacenamientoPublicaciones[posicion].buscar(id);
    }

    public boolean validarPublicacion(int id) {
        int posicion = asignacionPosicion(id);
        return almacenamientoPublicaciones[posicion].existe(id);
    }
}
