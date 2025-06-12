package violacao;

public class ServicoDeSMS {
        public void sendSMS(String msg, String destinatario) {
        System.out.println(
            "Enviando SMS para " + 
            destinatario +
            ": " +
            msg);
    }
}