package br.edu.iftm.tspi.sd.websockets_exemplo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

@Component
public class WebSocketListener {
    @Autowired
    private UsuarioConectadoService usuarioConectadoService;

    @EventListener
    public void disconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        usuarioConectadoService.removerUsuario(sessionId);
        System.out.println("Desconectado: " + sessionId);
    }
}
