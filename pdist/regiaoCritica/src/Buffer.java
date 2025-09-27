import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue<Integer> fila = new LinkedList<>();
    private static final int LIMITE = 5;
    
    public synchronized void produzir(int item)
        throws InterruptedException {
            while (fila.size() == LIMITE) {
                System.out.println("Buffer cheio");
                wait();
            }

            fila.add(item);
            System.out.println("Produziu: " + item);

            if (fila.size() == 1) {
                notifyAll();
            }

            Thread.sleep(300);
        }

    public synchronized void consumir() 
        throws InterruptedException {
            while (fila.isEmpty()) {
                System.out.println("Buffer vazio");
                wait();
            }

            int item = fila.poll();
            System.out.println("Consumiu: " +item);

            if(fila.size() == LIMITE - 1) {
                notifyAll();
            }
    }
}