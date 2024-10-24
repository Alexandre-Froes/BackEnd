package encapsulamento;

import java.util.Scanner;

public class App {
    public static Pessoa lePessoa(){
        Scanner ler = new Scanner(System.in);
        Pessoa pessoa = new Pessoa();

        System.out.println("Digite o nome da pessoa");
        pessoa.setNome(ler.nextLine());

        System.out.println("Digite o sexo da pessoa:");
        pessoa.setSexo(ler.next().charAt(0)); ;
        ler.nextLine();

        System.out.println("Digite o idade da pessoa:");
        pessoa.setIdade(ler.nextInt());

        return pessoa;
    }

    public static Formacao leFormacao(){
        Scanner ler = new Scanner(System.in);
        Formacao formacao = new Formacao();
        char concResp;

        System.out.println("Digite o nível de formação:");
        formacao.setNivel(ler.nextLine());

        System.out.println("Você concluiu o curso? (s ou n)");
        concResp = ler.next().charAt(0);
        ler.nextLine();

        formacao.setConcluido(concResp == 's' || concResp == 'S');

        System.out.println("Digite a instituição de ensino que você frequentou");
        formacao.setInstituto(ler.nextLine());

        System.out.println("Digite o ano de formação:");
        formacao.ano = ler.nextInt();
        ler.nextLine();

        return formacao;
    }
    public static String exibePessoa(Pessoa p){
        return "Nome: " + p.nome + "\n" +
               "Sexo: " + p.sexo + "\n" + 
               "Idade: " + p.idade + "\n";
    }
    public static String exibeFormacao(Formacao f){
        String concluido;
        if (f.concluido) {
            concluido = "Formado";
        } else {
            concluido = "Não concluido";
        }
        return "Nível: " + f.nivel + "\n" +
               "Estado da conclusão: " + concluido + "\n" + 
               "Instituto: " + f.instituto + "\n" +
               "Ano da formação: " + f.ano + "\n";
    }
    public static int exibirMenu(){
        Scanner ler = new Scanner(System.in);

        System.out.println("1 - Cadastrar pessoa");
        System.out.println("2 - Cadastrar formação");
        System.out.println("3 - Exibir informações");
        System.out.println("4 - Editar idade");
        System.out.println("5 - Sair");
        
        return ler.nextInt();
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Pessoa p = new Pessoa();
        Formacao f = new Formacao();

        int x = 0;

        do {
            x = exibirMenu();

            switch (x) {
                case 1:
                    p = lePessoa();
                    break;

                case 2:
                    f = leFormacao();
                    break;

                case 3:
                    System.out.println(exibePessoa(p));
                    System.out.println(exibeFormacao(f)); 
                    break;      

                case 4:
                    System.out.println("Digite uma nova idade para" + p.nome + ":");
                    p.idade = ler.nextInt();
                    break;    
            
                case 5:
                    System.out.println("Fechando o programa");
                    break;

                default:
                    System.out.println("\nOpção inválida\n");
                    break;
            }

        } while(x != 5);

        ler.close();
    }
}