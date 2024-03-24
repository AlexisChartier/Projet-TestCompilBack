package com.pong;

import com.pong.api.PongGameData;
import com.pong.logic.PongLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PongLogicTest {

    private PongLogic pongLogic;
    private PongGameData pongGameData;

    @BeforeEach
    void setUp() {
        pongLogic = PongLogic.getInstance();
        pongGameData = PongGameData.getInstance();
        pongLogic.initialize(1400, 1000);
    }

    @Test
    void testInitialize() {
        assertNotNull(PongGameData.getInstance().getPlayer());
        assertNotNull(PongGameData.getInstance().getPaddle());
        assertNotNull(PongGameData.getInstance().getBall());
    }

    @Test
    void testUpdate_BallHitsWall_Left() {
        // Move ball to left wall
        PongGameData.getInstance().getBall().setX(0);
        PongGameData.getInstance().getBall().setMoveX(PongLogic.LEFT);
        // Update game state
        pongLogic.update();

        // Check if ball's position is reset
        assertTrue(PongGameData.getInstance().getBall().getX() > 0);
    }

    @Test
    void testUpdate_BallHitsWall_Right() {
        // Move ball to right wall
        PongGameData.getInstance().getBall().setX(1400);
        PongGameData.getInstance().getBall().setMoveX(PongLogic.RIGHT);

        // Update game state
        pongLogic.update();

        // Check if ball's position is reset
        assertTrue(pongGameData.getBall().getX() < 1400);
    }


    @Test
    void testUpdate_PlayerMovement() {
        // Set player's move to UP
        PongGameData.getInstance().getPlayer().setMove(PongLogic.UP);
        double initialY = PongGameData.getInstance().getPlayer().getY();

        // Update game state
        pongLogic.update();

        // Check if player's position is moved up
        assertTrue(PongGameData.getInstance().getPlayer().getY() < initialY);

        // Set player's move to DOWN
        PongGameData.getInstance().getPlayer().setMove(PongLogic.DOWN);
        initialY = PongGameData.getInstance().getPlayer().getY();

        // Update game state
        pongLogic.update();

        // Check if player's position is moved down
        assertTrue(PongGameData.getInstance().getPlayer().getY() > initialY);
    }

    @Test
    void testUpdate_AIPlayerMovement() {
        // Move ball to AI's side
        PongGameData.getInstance().getBall().setX(1400);
        PongGameData.getInstance().getPaddle().setY(500); // Set paddle's position

        // Update game state
        pongLogic.update();

        // Check if AI paddle moves towards the ball
        assertTrue(PongGameData.getInstance().getPaddle().getY() != 500);
    }
}
