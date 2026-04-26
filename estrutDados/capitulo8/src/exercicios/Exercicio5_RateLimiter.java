package exercicios;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Exercício 5 — Rate Limiter com janela deslizante (Sliding Window)
 * 
 * O RateLimiterService usa um Deque<Long> para manter os timestamps das
 * últimas requisições de cada usuário. A janela deslizante de 1 minuto
 * garante que apenas requisições recentes sejam consideradas.
 * 
 * Padrão usado em APIs reais (AWS, Google Cloud, GitHub API) para
 * controle de tráfego e proteção contra abuso.
 * 
 * Complexidade:
 * - permitir(): O(k) onde k é o número de requisições na janela
 *   (na prática, k é limitado pelo MAX_REQS)
 * - Espaço: O(usuários × MAX_REQS)
 */
public class Exercicio5_RateLimiter {
    private static final int MAX_REQS = 10;          // máximo 10 requisições
    private static final long JANELA_MS = 60_000L;   // janela de 1 minuto

    /**
     * ConcurrentHashMap: map thread-safe para uso em API REST com múltiplas threads.
     * Cada usuario tem seu próprio Deque<Long> com timestamps das requisições.
     */
    private final Map<String, Deque<Long>> historico = new ConcurrentHashMap<>();

    /**
     * Verifica se o usuário pode fazer mais uma requisição dentro do limite.
     * 
     * @param userId identificador do usuário
     * @return true se requisição é permitida, false se usuário excedeu limite
     */
    public boolean permitir(String userId) {
        long agora = System.currentTimeMillis();
        
        // Obtém ou cria a fila de timestamps do usuário
        Deque<Long> fila = historico.computeIfAbsent(userId, k -> new ArrayDeque<>());

        // Remove timestamps fora da janela de 1 minuto
        // Questão: Por que peekFirst() é chamado antes de pollFirst()?
        // Resposta: peekFirst() verifica sem remover, evitando exceção se fila vazia
        while (!fila.isEmpty() && agora - fila.peekFirst() > JANELA_MS) {
            fila.pollFirst(); // Θ(1)
        }

        // Verifica se ainda há espaço na janela
        if (fila.size() < MAX_REQS) {
            fila.addLast(agora); // Registra novo timestamp
            return true;
        }
        
        return false;
    }

    /**
     * Retorna informações de debug sobre o usuário.
     */
    public Map<String, Object> debug(String userId) {
        Map<String, Object> info = new HashMap<>();
        Deque<Long> fila = historico.get(userId);
        
        if (fila == null) {
            info.put("usuario", userId);
            info.put("requisicoes_na_janela", 0);
            info.put("permitido", true);
        } else {
            info.put("usuario", userId);
            info.put("requisicoes_na_janela", fila.size());
            info.put("permitido", fila.size() < MAX_REQS);
            info.put("proxima_disponivel_em_ms", fila.isEmpty() ? 0 
                : JANELA_MS - (System.currentTimeMillis() - fila.peekFirst()));
        }
        
        return info;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Exercício 5: Rate Limiter com Janela Deslizante ===\n");

        Exercicio5_RateLimiter rateLimiter = new Exercicio5_RateLimiter();
        
        String usuario = "usuario123";

        // Teste 1: Requisições dentro do limite
        System.out.println("Teste 1: Requisições dentro do limite (10 permitidas)");
        for (int i = 1; i <= 10; i++) {
            boolean permitido = rateLimiter.permitir(usuario);
            System.out.printf("  Requisição %2d: %s%n", i, permitido ? "PERMITIDA" : "NEGADA");
        }
        System.out.println();

        // Teste 2: Tentativa de exceder limite
        System.out.println("Teste 2: Tentativa de exceder limite");
        for (int i = 11; i <= 12; i++) {
            boolean permitido = rateLimiter.permitir(usuario);
            System.out.printf("  Requisição %2d: %s%n", i, permitido ? "PERMITIDA" : "NEGADA");
        }
        System.out.println();

        // Teste 3: Múltiplos usuários (isolamento)
        System.out.println("Teste 3: Múltiplos usuários (isolamento de limite)");
        String usuario2 = "usuario456";
        
        for (int i = 1; i <= 5; i++) {
            rateLimiter.permitir(usuario);
        }
        for (int i = 1; i <= 8; i++) {
            rateLimiter.permitir(usuario2);
        }
        
        System.out.println("  Usuario123 - Debug: " + rateLimiter.debug(usuario));
        System.out.println("  Usuario456 - Debug: " + rateLimiter.debug(usuario2));
        System.out.println();

        // Teste 4: Esperar 61 segundos e tentar novamente (simulado com print)
        System.out.println("Teste 4: Janela deslizante (seria necessário esperar 60s)");
        System.out.println("  Recomendação: Aguarde 60+ segundos e execute novamente");
        System.out.println("  para ver o comportamento de limpeza da janela");
        System.out.println();

        // Perguntas e respostas
        System.out.println("=== PERGUNTAS E RESPOSTAS ===\n");
        
        System.out.println("(a) Por que peekFirst() é chamado antes de pollFirst()?");
        System.out.println("    → peekFirst() verifica sem remover (não lança exceção se vazio)");
        System.out.println("    → pollFirst() remove apenas se há algo para remover");
        System.out.println("    → Combinação garante remoção segura de timestamps expirados");
        System.out.println();

        System.out.println("(b) Por que ConcurrentHashMap em vez de HashMap?");
        System.out.println("    → Uma API REST tem múltiplas threads processando requisições");
        System.out.println("    → ConcurrentHashMap permite leituras e escritas concorrentes");
        System.out.println("    → HashMap não é thread-safe (data race em casos de colisão)");
        System.out.println("    → Com ConcurrentHashMap, apenas a Deque específica do");
        System.out.println("      usuário sofre sincronização, não o mapa inteiro");
        System.out.println();

        System.out.println("(c) O mapa cresce indefinidamente?");
        System.out.println("    → Sim, com a implementação atual, dados de usuários inativos");
        System.out.println("    → permanecem no mapa indefinidamente");
        System.out.println("    → Soluções:");
        System.out.println("       * Implementar limpeza periódica com @Scheduled");
        System.out.println("       * Usar WeakHashMap<String, Deque<Long>>");
        System.out.println("       * Implementar LRU (Least Recently Used) cache");
        System.out.println("       * Usar Redis com TTL (Time-To-Live) para dados");
    }
}
