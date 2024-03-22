package com.pong.api;

import com.pong.logic.PongLogic;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pong")
public class PongResource {

    private final PongLogic gameLogic;

    public PongResource() {
        this.gameLogic = new PongLogic();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gameData")
    public PongGameData getGameData() {
        PongGameData gameData = new PongGameData();
        gameData.setPlayerScore(gameLogic.getPlayerScore());
        gameData.setPaddleScore(gameLogic.getPaddleScore());
        gameData.setOver(gameLogic.isOver());
        gameData.setRunning(gameLogic.isRunning());
        gameData.setRound(gameLogic.getRound());
        // Ajoute d'autres données de jeu si nécessaire
        return gameData;
    }
}
