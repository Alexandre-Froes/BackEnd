public class App {
    public static void main(String[] args) {

        Gerente g = new Gerente("Xande", "123123123", 10000);
        Vendedor v = new Vendedor("Matheus", "123123123", 5000, 10000.00);

        System.out.println("GERENTE ANTES DE CALCULAR SALARIO");
        System.out.println("nome: " + g.getNome());
        System.out.println("salario: " + g.getSalario());
        System.out.println("GERENTE DEPOIS DE CALCULAR SALARIO");
        System.out.println("salario: " + g.calculaSalario());
        System.out.println();

        System.out.println("VENDEDOR ANTES DE CALCULAR SALARIO");
        System.out.println("nome: " + v.getNome());
        System.out.println("salario: " + v.getSalario());
        System.out.println("VENDEDOR DEPOIS DE CALCULAR SALARIO");
        System.out.println("total vendas: " + v.getTotalVendas());
        System.out.println("salario: " + v.calculaSalario());

    }
}
