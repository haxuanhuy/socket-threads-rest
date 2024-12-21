package app;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.*;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatServer {

    public static void main(String[] args) throws IOException {
        // Create configuration object for webserver instance
        ResourceConfig config = new ResourceConfig();
        // Register REST-resources (i.e. service classes) with the webserver
        config.register(RestHandler.class);

        // Create webserver instance for REST APIs and start it
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        server.createContext("/restapi", handler);
        server.start();
        
     //Create a new thread to handle the socket server
        Thread socketThread = new Thread(new SocketHandler());
      //Start the socket server in a separate thread.
        socketThread.start();
        
        // Show dialogue in order to prevent premature ending of server(s)
        JOptionPane.showMessageDialog(null, "Stop server...");
        server.stop(0);
    }
}
