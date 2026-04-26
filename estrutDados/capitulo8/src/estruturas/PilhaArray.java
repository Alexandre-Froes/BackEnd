package estruturas;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Implementação de Pilha com array dinâmico redimensionável.
 * O topo é armazenado como índice do próximo slot livre.
 * Operações: O(1) amortizado para push/pop.
 *
 * @param <T> tipo dos elementos
 */
public class PilhaArray<T> implements Pilha<T> {
    private static final int CAP_INICIAL = 16;
    private Object[] elementos;
    private int topo; // índice do próximo slot LIVRE (não do último elemento)

    public PilhaArray() {
        elementos = new Object[CAP_INICIAL];
        topo = 0;
    }

    @Override
    public void push(T elemento) {
        garantirCapacidade();
        elementos[topo] = elemento;
        topo++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        topo--;
        T removido = (T) elementos[topo];
        elementos[topo] = null; // libera referência — evita memory leak
        return removido;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (T) elementos[topo - 1];
    }

    @Override
    public boolean isEmpty() {
        return topo == 0;
    }

    @Override
    public int size() {
        return topo;
    }

    private void garantirCapacidade() {
        if (topo == elementos.length) {
            elementos = Arrays.copyOf(elementos, elementos.length * 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pilha[topo→");
        for (int i = topo - 1; i >= 0; i--) {
            if (i < topo - 1) sb.append(", ");
            sb.append(elementos[i]);
        }
        return sb.append("]").toString();
    }
}
