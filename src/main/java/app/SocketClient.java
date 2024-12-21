package app;

import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";  
        int serverPort = 12340;              
        try {
            //Verbindung zum Server herstellen
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);

            //Eingabe-/Ausgabeströme erstellen
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Benutzer auffordern, einen Benutzernamen einzugeben
            System.out.print("Enter your username: ");
            String username = userInputReader.readLine();

            // Sende den Benutzernamen an den Server
            out.println(username);

            // Lese kontinuierlich Nachrichten vom Benutzer und sende sie an den Server
            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = userInputReader.readLine();
               // Geben Sie 'exit' ein, um den Chat zu beenden
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chat.");
                    break;
                }

                // Sende die Nachricht an den Server
                out.println(message);

                // Lese die Antwort des Servers und drucke sie aus
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }

            //Schließe die Verbindung
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
