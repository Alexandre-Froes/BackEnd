import java.util.Scanner;

public class LeituraTeclado implements Runnable {
    
    private StringBuffer buffer;
    
    public LeituraTeclado(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try (Scanner ler = new Scanner(System.in)) {
            while (true) {
                buffer.append(ler.nextLine() +"\n");
            }
        }
    }
}
