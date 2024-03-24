package com.pong.api;

import com.pong.logic.PongLogic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/pong")
@ApplicationScoped
public class PongResource {

    private PongGameData gameData;

    @OnOpen
    public void onOpen(Session session) {
        gameData = PongGameData.getInstance();
        sendGameData(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if(message != null && !message.isEmpty()){
            try{
                int direction = Integer.parseInt(message);
                gameData.getPlayer().setMove(direction);
            }
            catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        gameData = null;
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }


    private void sendGameData(Session session) {
        try {
            session.getBasicRemote().sendText(gameData.toJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
