//En este caso vamos a guardar los indices de cada usuaroi en la matriz de adyacencia 
//que tenemos que recorrer para hacer el recorrido fs y encontrar a los amigos de amigos 
//La cola está implementada mediante un arreglo circular, lo que permite realizar las 
//operaciones de encolar y desencolar en tiempo constante O(1) sin necesidad de desplazar elementos.

package estructuras.usuarios;
public class ColaUsuarios {

    private int[] usuariosCola;
    private int frente;
    private int fin;
    private int capacidad;
    private int tamaño;

    public ColaUsuarios(int capacidad){
        this.capacidad = capacidad;
        this.usuariosCola = new int[capacidad]; // seteamos la capacidad de la cola con el valor que vamos a recibir al generarla
        this.frente = 0; 
        this.fin = -1; // el fin empieza en -1 porque no hay elementos en la cola
        this.tamaño = 0; // el tamaño inicial es 0
    }

    public boolean estaVacia(){
        if (tamaño == 0){
            return true;
        }
        return false;
    }

    public boolean estaLlena(){
        if( tamaño == capacidad){
            return true;
        }
        return false;
    }

    public boolean encolar(int idUsuario){
        if (estaLlena() == true){
            return false; // si la cola esta llena no podemos agregar mas elementos aella
        }

        fin = (fin + 1) % capacidad; // movemos el fin al siguiente indice circularmente  
        //se usa el modulo para que cuando llegue al final del array vuelva al inicio
        usuariosCola[fin] = idUsuario; // agregamos el id del usuario en la posicion del fin
        tamaño++; // incrementamos el tamaño de la cola
        return true;
    }

    public int desencolar(){
        if (estaVacia() == true){
            return -1; // si la cola esta vacia no podemos desencolar ningun elemento
        }

        int idUsuario = usuariosCola[frente]; // obtenemos el id del usuario en la posicion del frente
        frente = (frente + 1) % capacidad; // movemos el frente al siguiente indice 
        tamaño--; // disminuimos el tamaño de la cola xq sacamos un usuario
        return idUsuario; // devolve,ps el id del usuario desencolado
    }


    
}
