package br.edu.iftm.tspi.sd.websockets_exemplo.handler;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    
    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private UsuarioConectadoService usuarioConectadoService;

    @MessageMapping("/chat.users")
    @SendTo("/topic/users")
    public List<String> usuariosConectados() {
        return usuarioConectadoService.getUsuarios();
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Mensagem enviarTexto(Mensagem mensagem) {
        mensagem.setTipoMensagem(TipoMensagem.ENVIAR_TEXTO);
        mensagem.setDataHora(Instant.now());
        return mensagem;
    }

    @MessageMapping("/chat.join") 
    @SendTo("/topic/public")
    public Mensagem entrar(Mensagem mensagem,  SimpMessageHeaderAccessor accessor) {
        mensagem.setTipoMensagem(TipoMensagem.ENTRAR);
        mensagem.setDataHora(Instant.now());

        accessor.getSessionAttributes().put("origem", mensagem.getOrigem());
        mensagem.setTexto(" entrou");
        return mensagem;
    }
    
    @MessageMapping("/chat.leave") 
    @SendTo("/topic/public")
    public Mensagem sair(Mensagem mensagem) {
        mensagem.setTipoMensagem(TipoMensagem.SAIR);
        mensagem.setDataHora(Instant.now());
        mensagem.setTexto(mensagem.getOrigem() + " saiu");
        return mensagem;
    }

}
