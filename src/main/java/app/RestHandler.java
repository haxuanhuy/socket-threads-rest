package app;

import java.util.*;
import entity.Message;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;import 
jakarta.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/chat")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RestHandler {

	//ArrayList zum Speichern der Nachrichtenhistorie
    public static List<String> messageHistory = new ArrayList<>();

    //Die POST-Methode ermöglicht es einem Benutzer, eine Nachricht an den Server zu senden - POST /restapi/chat
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response sendMessage(Message message) {
        messageHistory.add(message.getUsername() + ": " + message.getContent());
        return Response.status(Response.Status.OK).entity("Hello "+message.getUsername()+", server has received your message: "+message.getContent()).build();
    }

   
    //Die GET-Methode zum Abrufen der gesamten Chat-Historie - GET /restapi/chat/history/all
    @GET
    @Path("/history/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<String> getMessageHistoryAll() {
        return messageHistory;
    }
    
    //Die GET-Methode zum Abrufen der Chat-Historie eines bestimmten Benutzers, basierend auf dem Benutzernamen - GET /restapi/chat/history/{username}
    @GET
    @Path("/history/{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessageHistory(@PathParam("username") String user) {
        if (user == null) {
        	 return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        List<String> userMessages = messageHistory.stream()
                .filter(message -> message.startsWith(user + ":"))
                .collect(Collectors.toList());

        if (userMessages.isEmpty()) {
        	return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.ok(userMessages).build();
    }
    
   
}
