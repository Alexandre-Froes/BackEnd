public class Leitores extends Thread {
    private int numeroLeitor;
    private BancoDados bancoDados;
    
    public Leitores(int numeroLeitor, BancoDados bancoDados) {
        this.numeroLeitor = numeroLeitor;
        this.bancoDados = bancoDados;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long)(Math.random() * 2000 + 1000));
                
                bancoDados.iniciarLeitura(numeroLeitor);
                
                Thread.sleep((long)(Math.random() * 1500 + 500));
                
                bancoDados.finalizarLeitura(numeroLeitor);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Leitor " + numeroLeitor + " foi interrompido");
        }
    }
    
    public int getNumeroLeitor() {
        return numeroLeitor;
    }
}
