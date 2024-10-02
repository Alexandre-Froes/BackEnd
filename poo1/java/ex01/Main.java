package BackEnd.poo1.java.ex01;

import java.util.Scanner;

public class Main {
    static int n = 3;
    static int[][] matrizMapa = new int[n][n]; 
    static char[][] matrizJogo = new char[n][n];

    static void preencherMapas() {
        int q = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrizMapa[i][j] = q++;
                matrizJogo[i][j] = '-';
            }
        }
    }

    static int interfaceJogo(char jogador, Scanner ler) {
        int posicao = 0;

        preencherMatrizMapa();

        System.out.println("");

        preencherMatrizJogo();

        System.out.printf("Jogador '%s' é a sua vez de jogar!%n", jogador);

        do {
            System.out.printf("Escolha uma posição: ");
            posicao = ler.nextInt();
        } while(posicao < 1 || posicao > 9);

        return posicao;
    }

    static void preencherMatrizJogo() {
        for(int i = 0; i < n; i++) {

            for(int j = 0; j < n; j++) {
                System.out.printf(matrizJogo[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static void preencherMatrizMapa() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.printf(matrizMapa[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static boolean validacao(int posicao) {
        int row = (posicao - 1) / n;
        int col = (posicao - 1) % n;

        return matrizJogo[row][col] == '-';
    }

    static void preencherJogo(int posicao, char jogador) {
        int row = (posicao - 1) / n;
        int col = (posicao - 1) % n;

        matrizJogo[row][col] = jogador;
    }

    static void restricao() {
        System.out.println("Jogada inválida, coloque outro número");
    }

    static boolean exibeFim(char jogador) {
        boolean vitoria = vitoria(jogador);
        boolean velha = velha();

        if (vitoria) {
            preencherMatrizJogo();
            System.out.println();
            System.out.printf("Parabéns jogador %s, você ganhou!%n", jogador);
            return true;
        }

        if (velha) {
            preencherMatrizJogo();
            System.out.println();
            System.out.printf("Deu Velha!%n");
            return true;
        }

    return false;
    }

    static boolean vitoria(char jogador) {
        
        for (int i = 0; i < n; i++) {
            if (matrizJogo[i][0] == jogador && matrizJogo[i][1] == jogador && matrizJogo[i][2] == jogador) return true;
            if (matrizJogo[0][i] == jogador && matrizJogo[1][i] == jogador && matrizJogo[2][i] == jogador) return true;
        }
        if (matrizJogo[0][0] == jogador && matrizJogo[1][1] == jogador && matrizJogo[2][2] == jogador) return true;
        if (matrizJogo[0][2] == jogador && matrizJogo[1][1] == jogador && matrizJogo[2][0] == jogador) return true;
        return false;
    }

    static boolean velha() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(matrizJogo[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        char jogador;
        boolean jogAtual = false;
        int posicao = 0;
        boolean valido;
        boolean fimDeJogo;

        preencherMapas();

        do {
            jogador = jogAtual ? 'X' : 'O';
            jogAtual = !jogAtual;
    
            do {
                posicao = interfaceJogo(jogador, ler);
                valido = validacao(posicao);
    
                if (!valido) {
                    restricao();
                }
    
            } while (!valido);
    
            preencherJogo(posicao, jogador);
    
            fimDeJogo = exibeFim(jogador);
    
        } while (!fimDeJogo);
    
        ler.close();
    }
}
