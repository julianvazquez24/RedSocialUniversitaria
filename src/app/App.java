package app;

import org.glassfish.grizzly.http.server.HttpServer;    //esto es lo que va a levantar el servidor http
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory; //esto es lo que va a integrar jersey con grizzly
import org.glassfish.jersey.server.ResourceConfig; //esto es para configurar los recursos de jersey, es decir, le pasamos la conntroller y mapeamos los endpoints

import java.net.URI;

public class App {

    public static void main(String[] args) {
 
        final ResourceConfig rc = new ResourceConfig() //busca los endpoints en el paquete controllers en las controllers
                .packages("controllers");  

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer( //es el servidor como tal, le pasamos al url y escucha los endpoints
                URI.create("http://localhost:8080/api/"),  
                rc
        );
    }
}
