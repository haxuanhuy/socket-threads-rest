## Beschreibung
Dieses Projekt nutzt die drei Hauptthemen: **Socket**, **Thread** und **REST**.

Es handelt sich um eine einfache Java-Chat-Server-Implementierung, die sowohl Socket-basierte als auch REST-basierte Kommunikation unterstützt. Der Server ist so konzipiert, dass er mehrere Client-Verbindungen gleichzeitig unter Verwendung von **Threads** verarbeitet. Jede Client-Verbindung (entweder über Socket oder REST-API) wird in einem separaten Thread verarbeitet, sodass der Server mehrere Benutzer gleichzeitig ohne Blockierung verwalten kann.

- **Socket-Kommunikation**: Der Server hört auf eingehende Socket-Verbindungen an Port `12345`. Wenn sich ein Client verbindet, erstellt der Server einen neuen Thread, um die Kommunikation mit diesem Client zu verwalten, was gleichzeitige Chat-Sitzungen ermöglicht.
  
- **REST-API**: Der Server bietet auch eine REST-API auf Port `8080`, mit der Benutzer Nachrichten senden und abrufen können. Ein separater Thread verarbeitet jede eingehende REST-Anfrage, um sicherzustellen, dass der Server reaktionsfähig bleibt und mehrere Anfragen parallel verwalten kann.

Durch die Verwendung von **Threads** kann der Server mehrere Clients effektiv verwalten, nicht-blockierende Kommunikation gewährleisten und die Skalierbarkeit verbessern.


### Installation
 git clone https://github.com/yourusername/chat-server.git

## Usage
- Führen Sie `ChatServer.java` aus, um den REST- und Socket-Server zu starten.
- Um die REST-API zu testen, können Benutzer eine Nachricht über `POST /restapi/chat` senden, gefolgt vom Anfragekörper. Der Anfragekörper kann ein JSON sein, z.B. `{ "username": "user1", "content": "message-test"}`.

- Benutzer können alle Chat-Historien über `http://localhost:8080/restapi/chat/history/all` abrufen.
- Benutzer können die Chat-Historie basierend auf ihrem Benutzernamen über `http://localhost:8080/restapi/chat/history/{username}` abrufen.

- Führen Sie `SocketClient.java` aus, um eine Verbindung zum Socket-Server herzustellen. Benutzer können ihren Benutzernamen eingeben und Nachrichten an den Server senden. Gesendete Nachrichten werden gespeichert und können später über die REST-API abgerufen werden. Geben Sie 'exit' ein, um den Chat zu beenden

 