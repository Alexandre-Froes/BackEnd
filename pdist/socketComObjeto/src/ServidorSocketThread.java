import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServidorSocketThread implements Runnable {
    private final Socket socketClient;

    public ServidorSocketThread(Socket socketClient) {
        this.socketClient = socketClient;
    }
    
    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream())) {

            Pedido pedido = (Pedido) input.readObject();

            System.out.println("Recebo o pedido: " + pedido);
            output.writeObject("Objecto recebido com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
