package violacao;

public class ServicoDeEmail {
    public void sendEmail(String msg, String destinatario) {
        System.out.println(
            "Enviando email para " + 
            destinatario +
            ": " +
            msg);
    }
}