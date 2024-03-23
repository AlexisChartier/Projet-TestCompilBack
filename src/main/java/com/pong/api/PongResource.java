package com.pong.api;

import com.pong.logic.PongLogic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/pong")
public class PongResource {

    private PongGameData gameData;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gameData")
    public PongGameData getGameData() {
        gameData = PongGameData.getInstance();
        return gameData;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateGame(PongGameData gameData) {
        this.gameData = gameData;
    }
}
