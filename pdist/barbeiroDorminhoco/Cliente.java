public class Cliente extends Thread {
    private int numeroCliente;
    private BarbeiroShop barbearia;
    
    public Cliente(int numeroCliente, BarbeiroShop barbearia) {
        this.numeroCliente = numeroCliente;
        this.barbearia = barbearia;
    }
    
    @Override
    public void run() {
        try {
            barbearia.chegarNaBarbearia(numeroCliente);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public int getNumeroCliente() {
        return numeroCliente;
    }
}
