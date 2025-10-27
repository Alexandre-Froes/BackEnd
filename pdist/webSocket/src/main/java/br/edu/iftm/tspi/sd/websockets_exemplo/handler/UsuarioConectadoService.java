package br.edu.iftm.tspi.sd.websockets_exemplo.handler;


import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UsuarioConectadoService {
    private final Set<String> usuariosConectados = ConcurrentHashMap.newKeySet();

    public synchronized void adicionarUsuario(String username) {
        usuariosConectados.add(username);
    }

    public synchronized void removerUsuario(String username) {
        usuariosConectados.remove(username);
    }

    public synchronized Set<String> getUsuarios() {
        return usuariosConectados;
    }
}
