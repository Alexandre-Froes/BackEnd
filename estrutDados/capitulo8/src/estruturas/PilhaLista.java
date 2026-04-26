package estruturas;

import java.util.EmptyStackException;

/**
 * Implementação de Pilha com lista simplesmente encadeada.
 * Sem redimensionamento — sempre Θ(1) puro.
 * Trade-off: alocação de Node a cada push.
 *
 * @param <T> tipo dos elementos
 */
public class PilhaLista<T> implements Pilha<T> {
    private Node<T> topo;
    private int quantidade;

    public PilhaLista() {
        topo = null;
        quantidade = 0;
    }

    @Override
    public void push(T elemento) {
        topo = new Node<>(elemento, topo);
        quantidade++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T removido = topo.dado;
        topo = topo.proximo;
        quantidade--;
        return removido;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return topo.dado;
    }

    @Override
    public boolean isEmpty() {
        return topo == null;
    }

    @Override
    public int size() {
        return quantidade;
    }
}
