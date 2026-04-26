public static String inverter(String texto) {
    Pilha<Character> pilha = new PilhaArray<>();
    String resultado = "";

    for (int i = 0; i < texto.length(); i++) {
        pilha.empilhar(texto.charAt(i));
    }

    while (!pilha.estaVazia()) {
        resultado += pilha.desempilhar();
    }

    return resultado;
}
