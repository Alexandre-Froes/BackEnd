package b1170_blobs;

import java.util.Scanner;

public class Main
{
    public static int blobCalculo(double kg, int dias){
        if (kg < 1.00001) {
            return dias;
        } else {
            return blobCalculo(kg / 2, dias + 1);
        }
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
        double kg = 0;
		
		for(int i = 0; i < n; i++) {
            kg = sc.nextDouble();
            int dias = 0;

            dias = blobCalculo(kg, dias);
            System.out.printf("%d dias\n" , dias);
		}

        sc.close();
	}
}
