package com.pong.compiler;

import com.pong.logic.PongLogic;
import com.pong.api.PongSocket;
import com.pong.api.PongGameData;

public class MainTest {
    public static void main(String[] args) {
        PongLogic pongLogic = PongLogic.getInstance();
        PongGameData gameData = PongGameData.getInstance();

        PongSocket pongSocket = new PongSocket();
        pongSocket.onOpen(null); // Simulating socket opening

        // Exemple de déplacement de joueur
        gameData.getPlayer().setMove(1); // Direction : droite
        pongLogic.update();
        pongSocket.onMessage("1", null); // Simulating message reception

        System.out.println("Position du joueur : " + gameData.getPlayer().getX() + ", " + gameData.getPlayer().getY());
    }
}
