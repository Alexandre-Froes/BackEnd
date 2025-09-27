import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;
    private Random rand = new Random();

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                buffer.consumir();
                Thread.sleep(rand.nextInt(3000));
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }


}
