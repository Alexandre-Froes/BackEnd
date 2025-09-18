import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket con = new Socket("127.0.0.1", 1234);

        System.out.println("Esperando conexão.");
        try(BufferedReader teclado = new 
            BufferedReader(new InputStreamReader(System.in));
            DataInputStream input = new 
                DataInputStream(con.getInputStream());
            DataOutputStream output = new 
                DataOutputStream(con.getOutputStream())) {

            while (true) {
                System.out.print("> ");
                String linha = teclado.readLine();
                output.writeUTF(linha);

                if (linha.isBlank()) {
                    System.out.println("Conexão encerrada");
                    break;
                }
            }
        }
    }
}