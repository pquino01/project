/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author pablo
 */
@ServerEndpoint("/chatEndpoint")
public class chatEndpoint {
    static Set<Session> chatUsers = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void open (Session userSession) throws IOException{
        chatUsers.add(userSession);
        userSession.getBasicRemote().sendText(buildJsonData("System","Write your name to display on the chatroom: "));
    }
    @OnMessage
    public void message (String message, Session userSession) throws IOException{
        String userName =(String) userSession.getUserProperties().get("username");
        if (userName==null){
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText(buildJsonData("System","you are now connected as "+message));
        }else{
            Iterator<Session> iterator= chatUsers.iterator();
            while(iterator.hasNext()) iterator.next().getBasicRemote().sendText(buildJsonData(userName,message));
        }
    }
    
    @OnClose
    public void close (Session userSession){
        chatUsers.remove(userSession);
    }
    
    private String buildJsonData(String userName,String message){
        JsonObject jsonObject = Json.createObjectBuilder().add("message", userName+": "+message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {jsonWriter.write(jsonObject);}
        return stringWriter.toString();
    }
}
