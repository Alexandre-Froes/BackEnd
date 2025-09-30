import java.io.IOException;
import java.io.ObjectStreamConstants;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Observador {
    private List<ServidorSocketThread> conexoes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        try(ServerSocket s = new ServerSocket(1234, 10)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");
            while (true) {
                Socket con = s.accept();
                System.out.println("Conexão estabelecida!");
                ServidorSocketThread threadSocket = new ServidorSocketThread(con, server);
                server.conexoes.add(threadSocket);
                Thread thread = new Thread(threadSocket);
                thread.start();
            }
        }
    }

    @Override
    public void enviaMsg(String msg) throws IOException {
        for(ServidorSocketThread thread: conexoes)
            thread.getOutput().writeUTF(msg);
    }
}