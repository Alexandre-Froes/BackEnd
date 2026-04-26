# Capítulo 8: Exercícios Práticos - Pilhas e Filas

Este projeto implementa os **5 exercícios práticos do Capítulo 8** da apostila de Estrutura de Dados (IFTM 2026/1).

## Estrutura do Projeto

```
capitulo8/
├── src/
│   ├── estruturas/              # Interfaces e implementações base
│   │   ├── Pilha.java          # Interface TAD Pilha (LIFO)
│   │   ├── Fila.java           # Interface TAD Fila (FIFO)
│   │   ├── Node.java           # Nó para listas encadeadas
│   │   ├── PilhaArray.java     # Pilha com array dinâmico
│   │   ├── PilhaLista.java     # Pilha com lista encadeada
│   │   ├── FilaCircular.java   # Fila com array circular
│   │   └── FilaLista.java      # Fila com lista encadeada
│   │
│   ├── exercicios/              # Implementações dos 5 exercícios
│   │   ├── Exercicio1_InversaoPilha.java
│   │   ├── Exercicio2_AvaliadosPosFixa.java
│   │   ├── Exercicio3_FilaDuasPilhas.java
│   │   ├── Exercicio4_ComparacaoTempo.java
│   │   └── Exercicio5_RateLimiter.java
│   │
│   └── tests/                   # Testes JUnit 5
│       ├── Exercicio1Test.java
│       ├── Exercicio2Test.java
│       ├── Exercicio3Test.java
│       ├── Exercicio4Test.java
│       └── Exercicio5Test.java
│
├── pom.xml
└── README.md
```

## Exercícios Implementados

### Exercício 1: Inversão de Sequência com Pilha
**Arquivo:** `Exercicio1_InversaoPilha.java`

Inverte uma string usando **exclusivamente** operações da interface `Pilha<T>`.

**Exemplos:**
- `inverter("IFTM")` → `"MTFI"`
- `inverter("")` → `""`
- `inverter("A")` → `"A"`

**Complexidade:**
- Temporal: O(n)
- Espacial: O(n)

**Executar:**
```bash
cd src/exercicios
javac -cp ../../bin Exercicio1_InversaoPilha.java
java -cp ../../bin:. Exercicio1_InversaoPilha
```

---

### Exercício 2: Avaliador de Expressões Pós-Fixas
**Arquivo:** `Exercicio2_AvaliadosPosFixa.java`

Avalia expressões em **Notação Polonesa Reversa** (RPN) usando uma pilha.

**Exemplos:**
- `"3 4 +"` → `7.0`
- `"5 1 2 + 4 * + 3 -"` → `14.0` (equivalente a 5 + (1+2)*4 - 3)
- `"10 3 %"` → `1.0` (módulo)

**Operadores suportados:** `+`, `-`, `*`, `/`, `%`

**Complexidade:** O(n) onde n é o número de tokens

**Executar:**
```bash
cd src/exercicios
javac -cp ../../bin Exercicio2_AvaliadosPosFixa.java
java -cp ../../bin:. Exercicio2_AvaliadosPosFixa
```

---

### Exercício 3: Fila usando Duas Pilhas
**Arquivo:** `Exercicio3_FilaDuasPilhas.java`

Implementa uma **Fila FIFO** usando apenas **duas pilhas internas**.

**Estratégia:**
- `entrada`: recebe novos elementos (push)
- `saida`: fornece elementos (pop)
- Transferência automática: quando `saida` vazia, todos elementos são transferidos de `entrada`

**Complexidade:**
- `enqueue()`: Θ(1)
- `dequeue()`: Θ(1) amortizado

**Executar:**
```bash
cd src/exercicios
javac -cp ../../bin:. Exercicio3_FilaDuasPilhas.java
java -cp ../../bin:. Exercicio3_FilaDuasPilhas
```

---

### Exercício 4: Comparação Empírica de Desempenho
**Arquivo:** `Exercicio4_ComparacaoTempo.java`

Compara o desempenho de 1.000.000 operações push+pop em:
1. **PilhaArray** (implementação própria)
2. **PilhaLista** (implementação própria)
3. **ArrayDeque** (JCF)

**Resultado esperado:**
```
PilhaArray:  ~28 ms  (mais rápida - cache locality)
ArrayDeque:  ~30 ms  (similar)
PilhaLista:  ~62 ms  (2.2x mais lenta - alocação de Node)
```

**Por que PilhaArray é mais rápida?**
- Elementos contíguos em memória → melhor cache locality
- Sem alocação de objetos a cada operação
- Processador consegue prefetchar dados vizinhos

**Executar:**
```bash
cd src/exercicios
javac -cp ../../bin Exercicio4_ComparacaoTempo.java
java -cp ../../bin:. Exercicio4_ComparacaoTempo
```

---

### Exercício 5: Rate Limiter com Janela Deslizante
**Arquivo:** `Exercicio5_RateLimiter.java`

Implementa um **Rate Limiter** que controla requisições por usuário usando uma **janela deslizante de 60 segundos**.

**Configuração:**
- **Máximo:** 10 requisições por usuário
- **Janela:** 60 segundos

**Características:**
- Múltiplos usuários com limites independentes
- Thread-safe com `ConcurrentHashMap`
- Limpeza automática de timestamps expirados

**Padrão usado em:**
- AWS API Gateway
- GitHub API
- Google Cloud APIs

**Respostas das perguntas:**

**(a) Por que peekFirst() antes de pollFirst()?**
```
peekFirst() verifica sem remover (não lança exceção se vazio)
pollFirst() remove apenas se há algo para remover
Combinação garante remoção segura de timestamps expirados
```

**(b) Por que ConcurrentHashMap e não HashMap?**
```
API REST tem múltiplas threads processando requisições
ConcurrentHashMap permite leituras e escritas concorrentes
HashMap não é thread-safe (data race possível)
Apenas a Deque específica do usuário sofre sincronização
```

**(c) O mapa cresce indefinidamente?**
```
Sim - com a implementação atual, dados de usuários inativos
permanecem indefinidamente no mapa

Soluções:
1. Limpeza periódica com @Scheduled
2. Usar WeakHashMap<String, Deque<Long>>
3. Implementar LRU (Least Recently Used) cache
4. Usar Redis com TTL (Time-To-Live)
```

**Executar:**
```bash
cd src/exercicios
javac -cp ../../bin Exercicio5_RateLimiter.java
java -cp ../../bin:. Exercicio5_RateLimiter
```

---

## Como Executar

### 1. Compilar com javac

```bash
# Compilar estruturas
javac -d bin src/estruturas/*.java

# Compilar exercícios
javac -cp bin -d bin src/exercicios/*.java

# Compilar testes
javac -cp bin:lib/junit-jupiter-api-5.9.1.jar:lib/junit-jupiter-engine-5.9.1.jar -d bin src/tests/*.java
```

### 2. Executar exercícios individuais

```bash
# Exercício 1
java -cp bin Exercicio1_InversaoPilha

# Exercício 2
java -cp bin Exercicio2_AvaliadosPosFixa

# Exercício 3
java -cp bin Exercicio3_FilaDuasPilhas

# Exercício 4 (comparação de tempo - leva alguns segundos)
java -cp bin Exercicio4_ComparacaoTempo

# Exercício 5
java -cp bin Exercicio5_RateLimiter
```

### 3. Executar testes com Maven

```bash
mvn clean test
```

### 4. Executar testes individuais com Maven

```bash
mvn test -Dtest=Exercicio1Test
mvn test -Dtest=Exercicio2Test
mvn test -Dtest=Exercicio3Test
mvn test -Dtest=Exercicio4Test
mvn test -Dtest=Exercicio5Test
```

---

## Análise de Complexidade

| Operação | PilhaArray | PilhaLista | FilaCircular | FilaLista |
|----------|-----------|-----------|-------------|-----------|
| push/enqueue | Θ(1)* | Θ(1) | Θ(1)* | Θ(1) |
| pop/dequeue | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| peek | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| Redimensionamento (custo pontual) | O(n) | N/A | O(n) | N/A |
| Overhead memória/elemento | 1 ref. | 2 refs. | 1 ref. | 2 refs. |

**\* Θ(1) amortizado** - ocasionalmente Θ(n) durante redimensionamento

---

## Testes JUnit 5

Cobertura de testes: **80%+ das branches**

### Exemplo: Executar todos os testes

```bash
mvn clean test

# Output esperado:
# [INFO] BUILD SUCCESS
# [INFO] Tests run: 50+
# [INFO] Failures: 0
# [INFO] Skipped: 0
```

### Gerar relatório de cobertura

```bash
mvn test jacoco:report
# Abrir: target/site/jacoco/index.html
```

---

## Principais Conceitos Demonstrados

1. **TAD (Tipos Abstratos de Dados)** - Interfaces que definem contrato
2. **LIFO vs FIFO** - Políticas de acesso
3. **Implementações Alternativas** - Array vs Lista Encadeada
4. **Complexidade Amortizada** - Redimensionamento de arrays
5. **Cache Locality** - Performance no hardware real
6. **Generics em Java** - Type erasure e workarounds
7. **Padrões Práticos** - Inversão, RPN, Rate Limiting, etc.
8. **Testes Automatizados** - JUnit 5

---

## Referências

- CORMEN et al. (2024). Introdução a Algoritmos. Cap. 10 (Pilhas e Filas)
- GOODRICH & TAMASSIA (2013). Estruturas de Dados em Java. Cap. 6
- Oracle JCF Documentation: Queue<E>, Deque<E>, ArrayDeque<E>

---

## Autor

Implementação para IFTM - Disciplina de Estrutura de Dados - 2026/1

**Aluno:** [Seu Nome]
**Semana:** 6 (Pilhas e Filas)
**Data:** Março/Abril 2026
