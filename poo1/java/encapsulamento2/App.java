package encapsulamento2;

import java.util.Scanner;

public class App {
    public static JogadorDeFutebol leDados() {
        Scanner ler = new Scanner(System.in);
        JogadorDeFutebol jogador = new JogadorDeFutebol();

        System.out.println("Digite o nome do jogador:");
        jogador.setNome(ler.nextLine());
        
        System.out.println("Digite a nacionalidade do jogador:");
        jogador.setNacionalidade(ler.nextLine());
        
        System.out.println("Digite a data de nascimento do jogador (somente números) na seguinte sequência ddmmaaaa:");
        jogador.setDtNasc(ler.nextInt());
        ler.nextLine();
        
        System.out.println("Digite a posição do jogador:");
        jogador.setPosic(ler.nextLine());
        
        System.out.println("Digite o peso do jogador:");
        jogador.setPeso(ler.nextDouble());
        ler.nextLine();
        
        System.out.println("Digite a altura do jogador:");
        jogador.setAltura(ler.nextDouble());
        ler.nextLine();
        
        ler.close();

        return jogador;
    }

    public static void tempoAposentadoria(JogadorDeFutebol jogador) {
        String posicao = jogador.getPosic().toLowerCase();
        int idade = jogador.calculaIdade();
        int idadeAposentadoria = 0;
    
        switch (posicao) {
            case "meio de campo":
                idadeAposentadoria = 38;
                break;
            case "atacante":
                idadeAposentadoria = 35;
                break;
            case "defesa":
                idadeAposentadoria = 40;
                break;
            default:
                System.out.println("Posição desconhecida. Não foi possível calcular a idade de aposentadoria.");
                break;
        }
    
        if (idade >= idadeAposentadoria) {
            System.out.println("Você pode se aposentar!");
        } else {
            System.out.printf("Você ainda não pode se aposentar! Faltam %d ano(s).\n", idadeAposentadoria - idade);
        }
    }
        
    public static String exibe(JogadorDeFutebol jogador) {
        int ano = jogador.getDtNasc() % 10000;

        int mes = (jogador.getDtNasc() / 10000) % 100;

        int dia = jogador.getDtNasc() / 1000000;

    return "Nome: " + jogador.getNome() + "\n" +
            "Data de Nascimento: " + String.format("%02d/%02d/%d", dia, mes, ano) + "\n" +
            "Altura: " + String.format("%.2f", jogador.getAltura()) + " m\n" +
            "Peso: " + String.format("%.1f", jogador.getPeso()) + " kg\n" +
            "Nacionalidade: " + jogador.getNacionalidade() + "\n" +
            "Posição: " + jogador.getPosic() + "\n";
    }

    public static void main(String[] args) {
        JogadorDeFutebol jogador = leDados();

        System.out.println(exibe(jogador));

        tempoAposentadoria(jogador);
    }
}
