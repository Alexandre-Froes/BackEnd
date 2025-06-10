package violacao;

public class App {
    public static void main(String[] args) {
        ServicoDeEmail e1 = new ServicoDeEmail();
        ServicoDeSMS s1 = new ServicoDeSMS();

        ServicoDeNotificacao sn = new ServicoDeNotificacao(e1, s1);

        sn.notificaPorEmail("seu email é xxx@sss.com",
        "Xande");

        sn.notificaPorSMS("seu código é 34544",
        "Xande");
    }
}
