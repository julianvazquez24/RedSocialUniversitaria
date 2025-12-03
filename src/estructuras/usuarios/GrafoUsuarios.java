package estructuras.usuarios;

import modelos.Usuario;

// video que tomamos como referencia para investigar sobre grafos
// https://www.youtube.com/watch?v=F5Xjpg0-NhM

public class GrafoUsuarios {
    private Usuario[] usuarios; // array de usuarios, para saber su indice
    private boolean[][] matriz; // matriz de adyacencia, utilizamos el tipo bool para documentar true si se
                                // sigue pero false no se sigue.
    private int capacidad;
    private int cantidad;

    public GrafoUsuarios(int capacidad) { /// Constructor del grafo, inicializa la matriz y el array de usuarios
        this.capacidad = capacidad;
        this.cantidad = 0;
        this.usuarios = new Usuario[capacidad];
        this.matriz = new boolean[capacidad][capacidad];
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (cantidad >= capacidad)
            return false; // se maneja la logica de agregar usuario, y tambien controlamos la cantidad que
                          // sea menor o igual a la capacidad

        usuarios[cantidad] = usuario;
        cantidad++;
        return true;
    }

    private int obtenerIndice(int idUsuario) { // usamos este metodo para obtener el id del usuario, si es que este se
                                               // encuentra en el array de usuarios,
                                               /// retornamos -1 si no se enncuentra el usuario.
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getId() == idUsuario)
                return i;
        }
        return -1;
    }

    // en este caso eliminamos del array usuarios y de la matriz para que no
    // existan diferencias en ambos, se maneja la persistencia de datos..
    public boolean eliminarUsuario(int idUsuario) { // logica para eliminar usuario, buscamos su indice en el array, si
                                                    // no se encuentra retornamos false
        int indice = obtenerIndice(idUsuario);
        if (indice == -1)
            return false;

        // corremos un lugar a la izquierda de los usuarios
        for (int i = indice; i < cantidad - 1; i++) { // si se encuentra, desplazamos los usuarios en el array y
                                                      // ajustamos la matriz de adyacencia
            usuarios[i] = usuarios[i + 1];
        }
        // corregimos el largo y la cantidad de usuarios
        usuarios[cantidad - 1] = null;

        // Ajusttamos la matriz, en esta caso la fila
        for (int i = indice; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad; j++) {
                matriz[i][j] = matriz[i + 1][j];
            }
        }
        // limpiar la última fila
        for (int j = 0; j < cantidad; j++) {
            matriz[cantidad - 1][j] = false;
        }

        // Ajustamos la columna afectada
        for (int i = 0; i < cantidad; i++) { // siguiendo la misma logica, que los arrays
            for (int j = indice; j < cantidad; j++) {
                matriz[i][j] = matriz[i][j + 1];
            }
        }

        cantidad--;
        return true;
    }

    public void seguir(int idSeguidor, int idSeguido) { /// aca manejamos la logica de relaciones en la matriz
        int i = obtenerIndice(idSeguidor); // buscamos los id de los usuarios, si alguno es -1 sale del metodo, dado que
                                           // uno de los dos no existe,
        int j = obtenerIndice(idSeguido); // si ambos indices son encontrados, vamos a buscar la interaeccion de los dos
                                          // en la matris y hacrmos que au valor sea true
                                          // esto indica que el i va a seguir al j

        if (i == -1 || j == -1)
            return;

        matriz[i][j] = true;
    }

    public boolean sigueA(int idA, int idB) { // para identificar si i segue a j, si el valor de la posicion es true, i
                                              // lo sigue de lo contrario es false
        int i = obtenerIndice(idA);
        int j = obtenerIndice(idB);

        if (i == -1 || j == -1)
            return false;

        return matriz[i][j];
    }

    public Usuario[] obtenerSeguidos(int idUsuario) {
        int i = obtenerIndice(idUsuario);
        if (i == -1)
            return new Usuario[0]; // si no existe el usuario con ese id, retornamos el array vacio dado que no
                                   // tendria relaciones

        int contador = 0;
        for (int j = 0; j < cantidad; j++) { /// este for analizamos las relaciones del usuario en la matriz, contamos
                                             /// cuantas relaciones tiene el usuario es decir,
            if (matriz[i][j]) // en los casso que sea true. Para posteriormente usar ese contador ppara
                              // designar el largo del array
                contador++;
        }

        Usuario[] UsuariosSeguidos = new Usuario[contador]; // creamos el array con el largo del contador
        int posicion = 0;

        for (int j = 0; j < cantidad; j++) { // otro for para llenar el array con los usuarios que el usuario sigue
            if (matriz[i][j]) {
                UsuariosSeguidos[posicion] = usuarios[j];
                posicion++;
            }
        }

        return UsuariosSeguidos;
    }

    public Usuario[] obtenerSeguidores(int idUsuario) { /// la logica es igual a obtenerseguidos, pero en este caso
                                                        /// buscariamos en los otros usuarios que quienes
        int j = obtenerIndice(idUsuario); // siguen al usuario j, es decir, invertimos la logica.
        if (j == -1)
            return new Usuario[0];

        int contador = 0;
        for (int i = 0; i < cantidad; i++) {
            if (matriz[i][j])
                contador++;
        }

        Usuario[] seguidoresUsuario = new Usuario[contador];
        int posicion = 0;

        for (int i = 0; i < cantidad; i++) {
            if (matriz[i][j]) {
                seguidoresUsuario[posicion] = usuarios[i];
                posicion++;
            }
        }

        return seguidoresUsuario;
    }

    public Usuario[] amigosEnComun(int idSeguidorA, int idSeguidorB) {

        int iA = obtenerIndice(idSeguidorA);
        int iB = obtenerIndice(idSeguidorB);

        if (iA == -1 || iB == -1) {
            return new Usuario[0];
        }

        int contador = 0;

        for (int p = 0; p < cantidad; p++) { // Primero buscamos los casos donde coincidan ambos en true, para
                                             // piosterior asignarle el largo al nuevoArray amigos en comun
            if (matriz[iA][p] && matriz[iB][p]) { // siempre que sea true
                contador++;
            }
        }

        Usuario[] amigosEnComun = new Usuario[contador];
        int posicion = 0;

        for (int p = 0; p < cantidad; p++) { // En este for llenamos el array en los casso que den true.
            if (matriz[iA][p] && matriz[iB][p]) {
                amigosEnComun[posicion] = usuarios[p];
                posicion++;
            }
        }

        return amigosEnComun;
    }

    public Usuario[] recomendarAmigosDeAmigos(int idUsuario){

        // videos que utilizamos para comprender el bfs, ya que a simple vista no lograbamos comprenderlo 
        // https://www.youtube.com/watch?v=_Yf8tneauJ8
        // https://www.youtube.com/watch?v=FXaUUYqTtY8

        // al nosotros tener una matriz de adyacencia y realizar el metodo de recorrida de bfs, el costo de este metodo 
        // va a ser O(V^2). dado que en el peor de los casos, vamos a tener que recorrer 
        //toda la matriz para encontrar las recomendaciones

        // neceitamos un array en el que marquemos los nodos que ya visitamos a medida que vamos recorriendo
        // necesitamos una cola para ir guardando los nodos que vamos a visitar
        // y necesitamos un array para guardar las recomendaciones que encontramos 
        // ademas vamos a tener que llevar un control sobre los niveles xq si no limitamos el bfs a nivel 2 que serian los amigos
        // de amigos, vamos a seguir acumulamdo recomendaciones a niveles posteriores
     
        int usuarioInicial = obtenerIndice(idUsuario);
        if (usuarioInicial == -1){
            return new Usuario[0];
        }

        boolean[] visitado = new boolean[cantidad]; // array para marcar los nodos visitados en la matriz

        int[] nivel = new int[cantidad];  // si el nivel es -1 quiere decir que no fue visitado 
        for ( int i = 0; i < nivel.length ; i++){
            nivel[i] = -1;
        }

        ColaUsuarios cola = new ColaUsuarios(cantidad);
        Usuario[] recomendacionesAmigos = new Usuario[cantidad];
        int cantidadRecomendaciones = 0;

        // arrancamos con el nodo inicial, es decir el usuario al que vamos a buscarle las recomendaciones
        visitado[usuarioInicial] = true;
        nivel[usuarioInicial] = 0;
        cola.encolar(usuarioInicial);

        // ARRRANCAMOS A RECORRER CON EL BFS SIN IRNOS DE NIVEL DOS

        while (cola.estaVacia() == false ){

            int actual = cola.desencolar();

            // si ya estoy en el nivel dos no sigo recorriendo
            if(nivel[actual] < 2){

                for( int j = 0; j < cantidad; j++){

                    if(matriz[actual][j] == true && visitado[j] == false){
                        visitado[j] = true;
                        nivel[j] = nivel[actual] + 1;
                        cola.encolar(j);

                        if( nivel[j] == 2 && matriz[usuarioInicial][j] == false && j != usuarioInicial){
                            recomendacionesAmigos[cantidadRecomendaciones] = usuarios[j];
                            cantidadRecomendaciones++;
                        }
                    }
                    
                }
            }

        }

        Usuario[] recomendacionesFinales = new Usuario[cantidadRecomendaciones];
        for(int i = 0; i < cantidadRecomendaciones; i++){
            recomendacionesFinales[i] = recomendacionesAmigos[i];
        }

        return recomendacionesFinales;
    }
    
}
