public class BarbeiroCochilaoMain {
    public static void main(String[] args) {
        final int NUMERO_CADEIRAS_ESPERA = 3;
        final int NUMERO_CLIENTES = 10;
        
        BarbeiroShop barbearia = new BarbeiroShop(NUMERO_CADEIRAS_ESPERA);
        
        Barbeiro barbeiro = new Barbeiro(barbearia);
        barbeiro.start();
        
        System.out.println("=== Barbearia Aberta ===");
        System.out.println("Número de cadeiras na sala de espera: " + NUMERO_CADEIRAS_ESPERA);
        System.out.println("Número de clientes que chegará: " + NUMERO_CLIENTES);
        System.out.println("========================\n");
        
        for (int i = 1; i <= NUMERO_CLIENTES; i++) {
            Cliente cliente = new Cliente(i, barbearia);
            cliente.start();
            
            try {
                Thread.sleep((long)(Math.random() * 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        try {
            Thread.sleep(20000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n=== Fechando Barbearia ===");
        barbeiro.interrupt();
    }
}
