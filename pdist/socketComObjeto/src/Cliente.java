import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        try (Socket con = new Socket("127.0.0.1", 1234)) {
            try(ObjectInputStream input = new ObjectInputStream(con.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(con.getOutputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

                while (true) {
                    System.out.print("Digite o CPF: ");
                    String cpf = teclado.readLine();
                    System.out.print("Digite o valor: ");
                    Double valor = new Double(teclado.readLine());
                    Pedido pedido = new Pedido(cpf, valor);
                    output.writeObject(pedido);
                    String resposta = (String) input.readObject();    
                    System.out.println(resposta);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
