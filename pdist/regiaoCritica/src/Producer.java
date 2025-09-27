import java.util.Random;

public class Producer implements Runnable {
    private Buffer buffer;
    private Random rand = new Random();

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        
        while(true) {
            int item = rand.nextInt(3000);
            try {
                System.out.println("Produtor produziu o item: " +item);
                buffer.produzir(item);
                System.out.println("Produtor entregou ao buffer o item: " +item);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}
