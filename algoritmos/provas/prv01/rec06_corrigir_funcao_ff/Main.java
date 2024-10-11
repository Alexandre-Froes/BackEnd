import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        System.out.println("ff(" + n + ") = " + funcaoFF(n));

        s.close();
    }

    public static int funcaoFF(int n) {
        if (n == 1 || n < 0){
            return 1;
        }else if (n % 2 == 0){
            return funcaoFF(n/2);
        }else {
            return funcaoFF((n-1)/2) + funcaoFF((n+1)/2);
        }      
    }
}

//linha 14: o "private" deveria ser trocado para "public"

//linha 16: Não faz sentido o programa fazer -1 do n quando n == 1, pois, se isso acontecer ele retornará 0,
//que vimos que é errado, por exemplo, quando digitamos 2, deveria retornar 1, ou seja caso o caso de n ser = 1, a função deverá retornar 1.
// Além disso o programa também retorna o valor 1 para casos de números negativos, ou seja esse if deve ter outra condição para quando o n for negativo

// private static int funcaoFF(int n) {
//     if (n == 1){
//         return funcaoFF(n-1);
//     }else if (n % 2 == 0){
//         return funcaoFF(n/2);
//     }else {
//         return funcaoFF((n-1)/2) + funcaoFF((n+1)/2);
//     }      
// }
