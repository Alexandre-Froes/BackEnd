import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorSocketThread implements Runnable {
    private final Socket socketClient;
    private DataOutputStream output;
    private Observador obs;

    public ServidorSocketThread(
            Socket socketClient, Observador obs) {
        this.socketClient = socketClient;
        this.obs = obs;
    }

    public DataOutputStream getOutput() {
        return output;
    }
    
    @Override
    public void run() {
        try(
        DataInputStream input = new 
            DataInputStream(socketClient.getInputStream())) {

            String linha;
            while((linha = input.readUTF()) != null && !linha.isBlank()) {
                output.writeUTF("O servidor leu "+linha);
                System.out.println(linha);

                if (linha.startsWith("/all"))
                    obs.enviaMsg(linha);
    
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
