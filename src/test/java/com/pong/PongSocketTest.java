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
    private RemoteEndpoint.Async async;

    @BeforeEach
    void setUp() {
        pongSocket = new PongSocket();
        session = mock(Session.class);
        async = mock(RemoteEndpoint.Async.class);
        when(session.getAsyncRemote()).thenReturn(async); // Mocking the behavior of getAsyncRemote()
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
        String message = "1";
        pongSocket.onMessage(message, session);
        verify(session, times(2)).getAsyncRemote();
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
