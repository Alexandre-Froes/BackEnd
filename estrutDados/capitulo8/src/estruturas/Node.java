package estruturas;

/**
 * Nó genérico para listas encadeadas.
 * Reutilizável em PilhaLista e FilaLista.
 *
 * @param <T> tipo dos dados
 */
public class Node<T> {
    public T dado;
    public Node<T> proximo;

    public Node(T dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public Node(T dado, Node<T> proximo) {
        this.dado = dado;
        this.proximo = proximo;
    }
}
