import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        try(ServerSocket s = new ServerSocket(1234, 10)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");
            while (true) {
                Socket con = s.accept();
                System.out.println("Conexão estabelecida!");
                ServidorSocketThread thread = new ServidorSocketThread(con);
                new Thread(thread).start();
            }
        }
    }
}