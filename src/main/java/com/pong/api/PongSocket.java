package com.pong.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pong.logic.PongLogic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/pong")
@ApplicationScoped
public class PongSocket {

    private PongGameData gameData;

    private PongLogic pongLogic;

    @OnOpen
    public void onOpen(Session session) {
        pongLogic = PongLogic.getInstance();
        gameData = PongGameData.getInstance();
        pongLogic.initialize(1400,1000);
        sendGameData(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("Message: " + message);
        if (message != null && !message.isEmpty()) {
            try {
                int direction = Integer.parseInt(message);
                gameData.getPlayer().setMove(direction);
                pongLogic.update();
                sendGameData(session);
            } catch (Exception e) {
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
        session.getAsyncRemote().sendObject(gameData.toJSON());
    }

    public PongLogic getPongLogic() {
        return pongLogic;
    }

    public PongGameData getGameData() {
        return gameData;
    }
}
