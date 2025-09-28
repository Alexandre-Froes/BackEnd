import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Barbeiro extends Thread {
    private BarbeiroShop barbearia;
    
    public Barbeiro(BarbeiroShop barbearia) {
        this.barbearia = barbearia;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                barbearia.cortarCabelo();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
