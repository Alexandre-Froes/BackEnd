import org.junit.jupiter.api.*;
import exercicios.Exercicio5_RateLimiter;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercício 5: Rate Limiter com Janela Deslizante")
class Exercicio5Test {
    
    private Exercicio5_RateLimiter rateLimiter;
    private String usuario = "user123";
    
    @BeforeEach
    void setUp() {
        rateLimiter = new Exercicio5_RateLimiter();
    }
    
    @Test
    @DisplayName("10 primeiras requisições devem ser permitidas")
    void testePrimeiras10Permitidas() {
        for (int i = 1; i <= 10; i++) {
            assertTrue(rateLimiter.permitir(usuario), 
                "Requisição " + i + " deve ser permitida");
        }
    }
    
    @Test
    @DisplayName("11ª requisição deve ser negada")
    void testeOnzevaRequisicaoNegada() {
        for (int i = 1; i <= 10; i++) {
            rateLimiter.permitir(usuario);
        }
        assertFalse(rateLimiter.permitir(usuario), 
            "11ª requisição deve ser negada");
    }
    
    @Test
    @DisplayName("Múltiplas requisições após limite devem ser negadas")
    void testeMultiplasNeagdasAposLimite() {
        for (int i = 1; i <= 10; i++) {
            rateLimiter.permitir(usuario);
        }
        
        // Próximas 5 devem ser negadas
        for (int i = 1; i <= 5; i++) {
            assertFalse(rateLimiter.permitir(usuario), 
                "Requisição após limite " + i + " deve ser negada");
        }
    }
    
    @Test
    @DisplayName("Usuários diferentes têm limites independentes")
    void testeUsuariosIndependentes() {
        String user1 = "alice";
        String user2 = "bob";
        
        // Alice faz 10 requisições
        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimiter.permitir(user1));
        }
        
        // Alice deve ser negada na 11ª
        assertFalse(rateLimiter.permitir(user1));
        
        // Bob deve conseguir fazer 10 requisições (limite independente)
        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimiter.permitir(user2));
        }
        
        // Bob deve ser negado na 11ª
        assertFalse(rateLimiter.permitir(user2));
    }
    
    @Test
    @DisplayName("Debug retorna informações corretas")
    void testeDebug() {
        rateLimiter.permitir(usuario);
        rateLimiter.permitir(usuario);
        
        Map<String, Object> info = rateLimiter.debug(usuario);
        
        assertEquals(usuario, info.get("usuario"));
        assertEquals(2, info.get("requisicoes_na_janela"));
        assertTrue((boolean) info.get("permitido"));
    }
    
    @Test
    @DisplayName("Debug para usuário não encontrado")
    void testeDebugUsuarioNaoEncontrado() {
        Map<String, Object> info = rateLimiter.debug("usuario_inexistente");
        
        assertEquals("usuario_inexistente", info.get("usuario"));
        assertEquals(0, info.get("requisicoes_na_janela"));
        assertTrue((boolean) info.get("permitido"));
    }
    
    @Test
    @DisplayName("Debug após limite")
    void testeDebugAposLimite() {
        for (int i = 0; i < 10; i++) {
            rateLimiter.permitir(usuario);
        }
        
        Map<String, Object> info = rateLimiter.debug(usuario);
        
        assertEquals(10, info.get("requisicoes_na_janela"));
        assertFalse((boolean) info.get("permitido"));
    }
    
    @Test
    @DisplayName("1 requisição a cada vez")
    void testeUmaRequisicaoPorVez() {
        // Simula padrão de requisições espaçadas
        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimiter.permitir(usuario + i), 
                "Cada usuário deve ter limite próprio");
        }
        
        // Todos devem estar no limite
        for (int i = 0; i < 10; i++) {
            assertFalse(rateLimiter.permitir(usuario + i), 
                "Cada usuário deve estar no limite");
        }
    }
    
    @Test
    @DisplayName("Grande número de usuários")
    void testeGrandeNumeroUsuarios() {
        int numUsuarios = 1000;
        
        // Cada usuário faz 1 requisição
        for (int i = 0; i < numUsuarios; i++) {
            assertTrue(rateLimiter.permitir("user_" + i));
        }
        
        // Todos devem ter espaço para mais 9
        for (int i = 0; i < numUsuarios; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(rateLimiter.permitir("user_" + i),
                    "User " + i + " deve ter espaço para mais " + (10-j) + " requisições");
            }
        }
        
        // Agora todos devem estar no limite
        for (int i = 0; i < numUsuarios; i++) {
            assertFalse(rateLimiter.permitir("user_" + i),
                "User " + i + " deve estar no limite");
        }
    }
}
