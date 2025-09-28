public class LeitoresEscritoresMain {
    public static void main(String[] args) {
        final int NUMERO_LEITORES = 5;
        final int NUMERO_ESCRITORES = 2;
        
        System.out.println("=== PROBLEMA LEITORES E ESCRITORES - LOCK OTIMISTA ===");
        System.out.println("Número de leitores: " + NUMERO_LEITORES);
        System.out.println("Número de escritores: " + NUMERO_ESCRITORES);
        System.out.println("Usando StampedLock com leitura otimista");
        System.out.println("========================================================\n");
        
        BancoDados bancoDados = new BancoDados();
        
        Thread[] leitores = new Thread[NUMERO_LEITORES];
        for (int i = 1; i <= NUMERO_LEITORES; i++) {
            leitores[i-1] = new Leitores(i, bancoDados);
            leitores[i-1].start();
        }
        
        Thread[] escritores = new Thread[NUMERO_ESCRITORES];
        for (int i = 1; i <= NUMERO_ESCRITORES; i++) {
            escritores[i-1] = new Escritores(i, bancoDados);
            escritores[i-1].start();
        }
        
        Thread estatisticas = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    System.out.println("\nESTATISTICAS: Leitores ativos: " + 
                                     bancoDados.getContadorLeitores() + 
                                     " | Dados atuais: " + bancoDados.getDados() + "\n");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        estatisticas.setDaemon(true);
        estatisticas.start();
        
        Thread leitorOtimista = new Thread(() -> {
            try {
                int contador = 1;
                while (true) {
                    Thread.sleep(1500);
                    bancoDados.lerComOtimizacao(999 + contador);
                    contador++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        leitorOtimista.setDaemon(true);
        leitorOtimista.start();
        
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== FINALIZANDO SIMULACAO ===");
        
        for (Thread leitor : leitores) {
            leitor.interrupt();
        }
        for (Thread escritor : escritores) {
            escritor.interrupt();
        }
        
        System.out.println("Simulacao finalizada!");
    }
}