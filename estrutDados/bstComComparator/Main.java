import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        
        Pessoa p1 = new Pessoa("Ana", 25);
        Pessoa p2 = new Pessoa("Bruno", 19);
        Pessoa p3 = new Pessoa("Carlos", 32);
        Pessoa p4 = new Pessoa("Daniela", 21);
        Pessoa p5 = new Pessoa("Eduardo", 40);

        Comparator<Pessoa> porIdade = (a, b) -> Integer.compare(a.idade, b.idade);
        Comparator<Pessoa> porNome = (a, b) -> a.nome.compareTo(b.nome);
        
        BST<Pessoa> arvoreIdade = new BST<>(porIdade);
        BST<Pessoa> arvoreNome = new BST<>(porNome);

        arvoreIdade.insert(p1);
        arvoreIdade.insert(p2);
        arvoreIdade.insert(p3);
        arvoreIdade.insert(p4);
        arvoreIdade.insert(p5);

        arvoreNome.insert(p1);
        arvoreNome.insert(p2);
        arvoreNome.insert(p3);
        arvoreNome.insert(p4);
        arvoreNome.insert(p5);

        System.out.println("Pessoas inseridas com sucesso na árvore!");

        System.out.println("àrvore de idades");
        arvoreIdade.imprimirEmOrdem();

        System.out.println();
        System.out.println("àrvore de nomes");
        arvoreNome.imprimirEmOrdem();
    }
}
