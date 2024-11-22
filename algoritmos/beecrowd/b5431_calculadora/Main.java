package BackEnd.algoritmos.beecrowd.b5431_calculadora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String eq = ler.nextLine();

        String[] eqSeparada = eq.split(" ");

        double num1 = Double.parseDouble(eqSeparada[0]);
        String operador = eqSeparada[1];
        double num2 = Double.parseDouble(eqSeparada[2]);


        switch(operador){
            case "*":
                System.out.printf("%.2f%n", num1 * num2);
                break;

            case "/":
                System.out.printf("%.2f%n", num1 / num2);
                break;

            case "+":
                System.out.printf("%.2f%n", num1 + num2);
                break;

            case "-":
                System.out.printf("%.2f%n", num1 - num2);
                break;

            default:
                System.out.println("Operador invalido.");
                break;
		}

        ler.close();
    }
}
