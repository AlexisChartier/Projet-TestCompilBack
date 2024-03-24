package com.pong;

import com.pong.api.PongGameData;
import com.pong.api.PongSocket;
import com.pong.logic.PongLogic;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PongSocketTest {

    private PongSocket pongSocket;
    private Session session;

    @BeforeEach
    void setUp() {
        pongSocket = new PongSocket();
        session = mock(Session.class);
        pongSocket.onOpen(session);
    }

    @Test
    void testOnOpen() {
        assertNotNull(pongSocket.getPongLogic());
        assertNotNull(pongSocket.getGameData());
        verify(session).getAsyncRemote();
    }

    @Test
    void testOnMessage() {
        String message = "1"; // Sample message
        PongGameData gameData = PongGameData.getInstance();
        PongLogic pongLogic = pongSocket.getPongLogic();
        pongSocket.onMessage(message, session);
        assertEquals(1, gameData.getPlayer().getMove());
        verify(pongLogic).update();
        verify(session).getAsyncRemote();
    }

    @Test
    void testOnClose() {
        pongSocket.onClose(session);
        assertNull(pongSocket.getGameData());
    }

    @Test
    void testOnError() {
        Throwable error = mock(Throwable.class);
        pongSocket.onError(error);
        verify(error).printStackTrace();
    }
}
