package app;

import java.io.*;
import java.net.Socket;
import entity.Message;
public class SocketHandler implements Runnable {

    private static final int PORT = 12340;

    @Override
    public void run() {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(PORT)) {
            System.out.println("Socket server started on port " + PORT);

            
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Einen neuen Client akzeptieren
                new Thread(new ClientHandler(clientSocket)).start(); // Erstelle und starte einen neuen Thread, um den Client zu behandeln
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Client-Handler, der jede Socket-Verbindung verarbeitet
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                // Lese den vom Client gesendeten Benutzernamen
                String username = in.readLine();
                

                String message;
                while ((message = in.readLine()) != null) {
                   
                    Message chatMessage = new Message(username, message);
                    
                    // Füge die Nachricht des Benutzers zur Nachrichtenhistorie hinzu
                    RestHandler.messageHistory.add(chatMessage.getUsername() + ": " + chatMessage.getContent());

                    //Server-Antwort
                    out.println("Hello " + username + ", server has received your message: "+ message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
