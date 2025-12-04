//En este caso investigamos varias maneras de hacerlo, concluimos en utilizar un hash,
//es decir, el arreglo principal actua como tabla, y cada indice es un lista enlazada,
//manejamos las colisiones mediante encadenamiento separado con la funcion 'asignacionPosicion',
//En promedio, las operaciones de inserción, busqueda y validacion tienen complejidad O(1). 
//En el peor caso (todas las claves colisionando), las listas internas pueden crecer hasta tamaño n,
//llevando las operaciones a O(n).

package estructuras.publicaciones;
import modelos.Publicacion;

public class mapaPublicacion {
    public listaPublicaciones[] almacenamientoPublicaciones;
    private int capacidad;

    public mapaPublicacion(int capacidad) {
        this.capacidad = capacidad;
        this.almacenamientoPublicaciones = new listaPublicaciones[capacidad];

        for (int i = 0; i < capacidad; i++) {
            almacenamientoPublicaciones[i] = new listaPublicaciones();
        }
    }

    private int asignacionPosicion(int id) { //hash aca asignamos ala publicacion en que lista se va almacenar
        return id % capacidad; //siempre da un numero entero. Va pasar que varias publicaciones entren dentro de la misma posicion de la lista
        //pero en este escenario, todas se almacenan en la misma posición de la lista, asi se encadenan una a otra sin importar el orden.
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
