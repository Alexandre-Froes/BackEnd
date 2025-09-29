public class Escritores extends Thread {
    private int numeroEscritor;
    private BancoDados bancoDados;
    
    public Escritores(int numeroEscritor, BancoDados bancoDados) {
        this.numeroEscritor = numeroEscritor;
        this.bancoDados = bancoDados;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long)(Math.random() * 3000 + 2000));
                
                String novosDados = "Dados_Escritor_" + numeroEscritor + "_" + System.currentTimeMillis();
                bancoDados.iniciarEscrita(numeroEscritor, novosDados);
                
                Thread.sleep((long)(Math.random() * 2000 + 1000));
                
                bancoDados.finalizarEscrita(numeroEscritor);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Escritor " + numeroEscritor + " foi interrompido");
        }
    }
    
    public int getNumeroEscritor() {
        return numeroEscritor;
    }
}