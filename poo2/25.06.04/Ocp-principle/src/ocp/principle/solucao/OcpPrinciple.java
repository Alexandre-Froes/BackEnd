package ocp.principle.solucao;

public class OcpPrinciple {

    public static void main(String[] args) {

        FolhaPagamento folhaPagamento = new FolhaPagamento();

        ContratoEstagio estagiario = new ContratoEstagio("Xade", "RH", 900);
        ContratoCLT clt = new ContratoCLT("Xade", "RH", 1200);
        
        System.out.println(folhaPagamento.calcular(estagiario));
        System.out.println(folhaPagamento.calcular(clt));
    }
}
