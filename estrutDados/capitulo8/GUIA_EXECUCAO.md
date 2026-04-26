# GUIA DE EXECUÇÃO - Capítulo 8: Pilhas e Filas

## Requisitos

- **Java 17+**
- **Maven 3.8+** (opcional, mas recomendado)
- **IDE**: VS Code, IntelliJ IDEA, ou Eclipse

---

## Opção 1: Compilar e Executar com javac (Simples)

### 1.1 Compilar estruturas base

```bash
cd c:\Users\alexa\Documents\IFTM\BackEnd\estrutDados\capitulo8

# Criar diretório para bytecode
mkdir bin

# Compilar interfaces e implementações
javac -d bin src/estruturas/*.java
```

### 1.2 Compilar exercícios

```bash
# Compilar todos os exercícios
javac -cp bin -d bin src/exercicios/*.java
```

### 1.3 Executar cada exercício

```bash
# Exercício 1: Inversão com Pilha
java -cp bin exercicios.Exercicio1_InversaoPilha

# Exercício 2: Avaliador de Expressões Pós-Fixas
java -cp bin exercicios.Exercicio2_AvaliadosPosFixa

# Exercício 3: Fila com Duas Pilhas
java -cp bin exercicios.Exercicio3_FilaDuasPilhas

# Exercício 4: Comparação de Tempo (leva ~20 segundos)
java -cp bin exercicios.Exercicio4_ComparacaoTempo

# Exercício 5: Rate Limiter
java -cp bin exercicios.Exercicio5_RateLimiter
```

---

## Opção 2: Usar Maven (Recomendado)

### 2.1 Compilar tudo

```bash
cd c:\Users\alexa\Documents\IFTM\BackEnd\estrutDados\capitulo8
mvn clean compile
```

### 2.2 Executar testes

```bash
# Todos os testes
mvn test

# Teste específico
mvn test -Dtest=Exercicio1Test
mvn test -Dtest=Exercicio2Test
mvn test -Dtest=Exercicio3Test
mvn test -Dtest=Exercicio4Test
mvn test -Dtest=Exercicio5Test
```

### 2.3 Ver cobertura de testes

```bash
mvn test jacoco:report
# Abrir: target/site/jacoco/index.html
```

### 2.4 Executar exercício específico como main

```bash
mvn exec:java -Dexec.mainClass="exercicios.Exercicio1_InversaoPilha"
mvn exec:java -Dexec.mainClass="exercicios.Exercicio2_AvaliadosPosFixa"
mvn exec:java -Dexec.mainClass="exercicios.Exercicio3_FilaDuasPilhas"
mvn exec:java -Dexec.mainClass="exercicios.Exercicio4_ComparacaoTempo"
mvn exec:java -Dexec.mainClass="exercicios.Exercicio5_RateLimiter"
```

### 2.5 Criar JAR executável

```bash
mvn package shade:shade
# Resultado: target/capitulo8-all.jar
java -jar target/capitulo8-all.jar
```

---

## Opção 3: VS Code (Integração)

### 3.1 Extensões necessárias
- Extension Pack for Java (Microsoft)
- Maven for Java (Microsoft)
- Test Runner for Java (Microsoft)

### 3.2 Executar diretamente

1. Abrir `src/exercicios/Exercicio1_InversaoPilha.java`
2. Clicar no botão "Run" acima do método `main()`
3. Saída aparece no terminal integrado

### 3.3 Executar testes

1. Abrir `src/tests/Exercicio1Test.java`
2. Clicar no ícone de teste (ou pressionar Ctrl+Shift+A → "Run Tests")
3. Resultados aparecem no painel "Test Explorer"

---

## Saída Esperada

### Exercício 1: Inversão de Pilha

```
Teste 1: inverter("IFTM") = "MTFI"
Esperado: "MTFI"

Teste 2: inverter("") = ""
Esperado: ""

Teste 3: inverter("A") = "A"
Esperado: "A"

Teste 4: inverter("Estrutura de Dados") = "sodaD ed aruturtE"
Esperado: "sodaD ed aruturtE"
```

### Exercício 2: Avaliador Pós-Fixo

```
Teste 1: "3 4 +" = 7.0
Esperado: 7.0

Teste 2: "5 1 2 + 4 * + 3 -" = 14.0
Esperado: 14.0 (= 5 + (1+2)*4 - 3)

Teste 3: "15 7 1 1 + - / 3 * 2 1 1 + + -" = 5.0
Esperado: 5.0

Teste 4: "10 2 %" = 0.0
Esperado: 0.0 (módulo)

Teste 5: "17 5 %" = 2.0
Esperado: 2.0 (módulo)
```

### Exercício 3: Fila com Duas Pilhas

```
Teste 1: Operações básicas FIFO
Enfileirou: 1, 2, 3
Desenfileirado 1: 1
Desenfileirado 2: 2
Desenfileirado 3: 3
Esperado: 1, 2, 3 (em ordem FIFO)
Fila vazia? true

Análise de Complexidade:
- enqueue(): Θ(1) - apenas push na entrada
- dequeue():
  * Caso normal (saida não vazia): Θ(1)
  * Pior caso (saida vazia): Θ(n) - transfere todos
  * Amortizado: Θ(1) - cada elemento é transferido uma única vez
```

### Exercício 4: Comparação de Desempenho

```
=== Exercício 4: Comparação de Desempenho ===

Medindo 1.000.000 de operações push+pop
Executando 3 vezes para calcular média

Iteração 1:
  PilhaArray:  1,234,567 ns = 1.23 ms
  PilhaLista:  2,456,789 ns = 2.46 ms
  ArrayDeque:  1,345,678 ns = 1.35 ms

[... iterações 2 e 3 ...]

=== RESULTADOS (MÉDIA) ===

PilhaArray:  1.25 ms
PilhaLista:  2.45 ms
ArrayDeque:  1.30 ms

PilhaLista é 1.96x mais lenta que PilhaArray
ArrayDeque é 1.04x comparado a PilhaArray

=== ANÁLISE TEÓRICA ===

Cache Locality:
- PilhaArray: Elementos contíguos em memória → Prefetch de cache eficiente
- PilhaLista: Nós espalhados pela heap → Cache misses frequentes

Alocação:
- PilhaArray: Realoca inteiro em blocos (amortizado)
- PilhaLista: Aloca Node a cada push (pressão no GC)

Conclusão:
PilhaArray é consistentemente 2-5x mais rápida em sistemas reais
```

### Exercício 5: Rate Limiter

```
=== Exercício 5: Rate Limiter com Janela Deslizante ===

Teste 1: Requisições dentro do limite (10 permitidas)
  Requisição  1: PERMITIDA
  Requisição  2: PERMITIDA
  ...
  Requisição 10: PERMITIDA

Teste 2: Tentativa de exceder limite
  Requisição 11: NEGADA
  Requisição 12: NEGADA

Teste 3: Múltiplos usuários (isolamento de limite)
  Usuario123 - Debug: {usuario=usuario123, requisicoes_na_janela=5, permitido=true}
  Usuario456 - Debug: {usuario456=user456, requisicoes_na_janela=8, permitido=true}

[... mais detalhes ...]

=== PERGUNTAS E RESPOSTAS ===

(a) Por que peekFirst() é chamado antes de pollFirst()?
    → peekFirst() verifica sem remover (não lança exceção se vazio)
    ...

(b) Por que ConcurrentHashMap em vez de HashMap?
    → Uma API REST tem múltiplas threads processando requisições
    ...

(c) O mapa cresce indefinidamente?
    → Sim, com a implementação atual...
```

---

## Testes com Maven

### Exemplo de saída de testes

```bash
$ mvn test

[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running Exercicio1Test
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s - in Exercicio1Test
[INFO] Running Exercicio2Test
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.087 s - in Exercicio2Test
[INFO] Running Exercicio3Test
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.102 s - in Exercicio3Test
[INFO] Running Exercicio4Test
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.456 s - in Exercicio4Test
[INFO] Running Exercicio5Test
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.234 s - in Exercicio5Test
[INFO] 
[INFO] -------------------------------------------------------
[INFO] Test Results:
[INFO] -------------------------------------------------------
[INFO] Tests run: 44, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS [in 1.503 s]
```

---

## Troubleshooting

### Problema: "Classe não encontrada"

```
Error: Could not find or load main class exercicios.Exercicio1_InversaoPilha
```

**Solução:**
```bash
# Verifique se estruturas foram compiladas
ls bin/estruturas/

# Se não existir, compile novamente:
javac -d bin src/estruturas/*.java
javac -cp bin -d bin src/exercicios/*.java
```

### Problema: "Pacote não encontrado"

```
error: package estruturas does not exist
```

**Solução:**
```bash
# Use -cp (classpath) corretamente
javac -cp bin -d bin src/exercicios/*.java
```

### Problema: Maven não encontra testes

```
No tests to run
```

**Solução:**
```bash
# Verifique se testes estão em src/test/java/ (Maven padrão)
# Ou configure em pom.xml:
<testSourceDirectory>src/tests</testSourceDirectory>
```

### Problema: JUnit não encontrado

```
error: cannot find symbol
  symbol: class Test
```

**Solução:**
```bash
# Use Maven que baixa dependências automaticamente
mvn test

# Ou adicione JUnit ao classpath manualmente:
javac -cp bin:lib/junit-jupiter-api-5.9.1.jar -d bin src/tests/*.java
```

---

## Próximos Passos

Após completar os exercícios do Capítulo 8:

1. **Capítulo 9**: Desafios Práticos (PilhaComMinimo, BufferCircular, etc.)
2. **Capítulo 10**: Árvores Binárias (usará Fila para BFS, Pilha para DFS)
3. **Capítulo 11**: Grafos (usará Fila e Pilha para buscas)

---

## Recursos Adicionais

- **Oracle JCF Docs**: https://docs.oracle.com/javase/17/docs/api/java.base/java/util/Deque.html
- **JUnit 5 Guide**: https://junit.org/junit5/docs/current/user-guide/
- **Maven Guide**: https://maven.apache.org/guides/

---

## Suporte

Para dúvidas ou problemas:
1. Revise o README.md no diretório do projeto
2. Analise os testes em `src/tests/` para exemplos de uso
3. Execute com verbosidade: `mvn -X test`
4. Consulte os comentários no código-fonte

---

**Última atualização:** Março 2026
**Versão:** 1.0.0
