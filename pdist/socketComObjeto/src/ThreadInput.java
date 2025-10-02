import java.io.DataInputStream;

public class ThreadInput implements Runnable{
    private DataInputStream input;

    public ThreadInput(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String linha = input.readUTF();
                if(linha.isBlank()) {
                    System.out.println("Conexão encerrada");
                    break;
                }
                System.out.println(linha);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}