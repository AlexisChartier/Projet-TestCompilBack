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

    @OnOpen
    public void onOpen(Session session) {
        gameData = PongGameData.getInstance();
        PongLogic.initialize(700,500);
        sendGameData(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if (message != null && !message.isEmpty()) {
            try {
                // Analyser le message JSON
                JsonObject json = new Gson().fromJson(message, JsonObject.class);
                if (json.has("direction")) {
                    int direction = json.get("direction").getAsInt();
                    gameData.getPlayer().setMove(direction);
                }
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
        session.getAsyncRemote().sendText(gameData.toJSON());
    }
}
