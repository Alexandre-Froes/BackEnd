package estruturas;

import java.util.NoSuchElementException;

/**
 * Implementação de Fila com lista duplamente encadeada.
 * Referências para cabeça (frente) e cauda (fundo) garantem Θ(1).
 *
 * @param <T> tipo dos elementos
 */
public class FilaLista<T> implements Fila<T> {
    private Node<T> cabeca;  // primeiro a sair (frente da fila)
    private Node<T> cauda;   // último a entrar (fundo da fila)
    private int quantidade;

    public FilaLista() {
        cabeca = null;
        cauda = null;
        quantidade = 0;
    }

    @Override
    public void enqueue(T elemento) {
        Node<T> novo = new Node<>(elemento);
        if (isEmpty()) {
            cabeca = novo;
        } else {
            cauda.proximo = novo;
        }
        cauda = novo;
        quantidade++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        T removido = cabeca.dado;
        cabeca = cabeca.proximo;
        if (cabeca == null) cauda = null;
        quantidade--;
        return removido;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        return cabeca.dado;
    }

    @Override
    public boolean isEmpty() {
        return quantidade == 0;
    }

    @Override
    public int size() {
        return quantidade;
    }
}
