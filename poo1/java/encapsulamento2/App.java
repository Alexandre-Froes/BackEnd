package encapsulamento2;

import java.util.Scanner;

public class App {
    public static JogadorDeFutebol leDados() {
        Scanner ler = new Scanner(System.in);
        JogadorDeFutebol jogador = new JogadorDeFutebol();
    
        System.out.println("Digite a nacionalidade do jogador:");
        jogador.nacionalidade = ler.nextLine();
    
        System.out.println("Digite a data de nascimento do jogador (somente números) na seguinte sequência ddmmaaaa:");
        jogador.dtNasc = ler.nextInt();
        ler.nextLine();
    
        System.out.println("Digite a posição do jogador:");
        jogador.posic = ler.nextLine();
    
        System.out.println("Digite o peso do jogador:");
        jogador.peso = ler.nextDouble();
        ler.nextLine();
    
        System.out.println("Digite a altura do jogador:");
        jogador.altura = ler.nextDouble();
        ler.nextLine();
    
        ler.close();
    
        return jogador;
    }
    
    public static void tempoAposentadoria(JogadorDeFutebol jogador) {
        String posicao = jogador.posic.toLowerCase();
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
            System.out.println("Jogador " + jogador.getNome() + ", você pode se aposentar!");
        } else {
            System.out.printf("Jogador %s, você ainda não pode se aposentar! Faltam %d ano(s).\n", jogador.getNome(), idadeAposentadoria - idade);
        }
    }
    
    public static String exibe(JogadorDeFutebol jogador) {
        int ano = jogador.dtNasc % 10000;
        int mes = (jogador.dtNasc / 10000) % 100;
        int dia = jogador.dtNasc / 1000000;
    
        return "Nome: " + jogador.getNome() + "\n" +
                "Data de Nascimento: " + String.format("%02d/%02d/%d", dia, mes, ano) + "\n" +
                "Altura: " + String.format("%.2f", jogador.altura) + " m\n" +
                "Peso: " + String.format("%.1f", jogador.peso) + " kg\n" +
                "Nacionalidade: " + jogador.nacionalidade + "\n" +
                "Posição: " + jogador.posic + "\n";
    }

    public static void main(String[] args) {
        JogadorDeFutebol jogador = leDados();

        System.out.println(exibe(jogador));

        tempoAposentadoria(jogador);
    }
}
