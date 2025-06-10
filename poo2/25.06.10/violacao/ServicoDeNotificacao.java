package violacao;

public class ServicoDeNotificacao {
    private final ServicoDeEmail EMAIL;
    private final ServicoDeSMS SMS;

    public ServicoDeNotificacao(ServicoDeEmail eMAIL, ServicoDeSMS sMS) {
        EMAIL = eMAIL;
        SMS = sMS;
    }

    public void notificaPorEmail(String msg, String destinatario) {
        EMAIL.sendEmail(msg, destinatario);
    }

    public void notificaPorSMS(String msg, String destinatario) {
        SMS.sendSMS(msg, destinatario);
    }
    
}
