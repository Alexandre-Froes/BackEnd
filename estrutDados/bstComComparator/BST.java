import java.util.Comparator;

public class BST<T> {
    private class Node {
        T data;
        Node left, right;
        Node(T data) { this.data = data; }
    }

    private Node root;
    private final Comparator<T> comparator;

    // Constructor accepting a custom comparator
    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node current, T data) {
        if (current == null) return new Node(data);

        // Use the comparator to decide the path
        int cmp = comparator.compare(data, current.data);
        if (cmp < 0) {
            current.left = insertRecursive(current.left, data);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, data);
        }
        return current;
    }

    public void imprimirEmOrdem() {
        percorrerEImprimir(root);
    }

    private void percorrerEImprimir(Node no) {
        if (no != null) {
            percorrerEImprimir(no.left);   // Visita os menores (esquerda)
            System.out.println(no.data);    // Imprime o atual
            percorrerEImprimir(no.right);  // Visita os maiores (direita)
        }
    }
}
