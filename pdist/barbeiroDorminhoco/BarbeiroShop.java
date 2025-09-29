import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.LinkedList;
import java.util.Queue;

public class BarbeiroShop {
    private final int numeroMaximoCadeirasEspera;
    private final Queue<Integer> filaEspera;
    private final Lock mutex;
    private final Condition clienteChegou;
    private final Condition cadeiraLivre;
    private boolean barbeiroOcupado;
    private Integer clienteAtual;
    
    public BarbeiroShop(int numeroMaximoCadeirasEspera) {
        this.numeroMaximoCadeirasEspera = numeroMaximoCadeirasEspera;
        this.filaEspera = new LinkedList<>();
        this.mutex = new ReentrantLock();
        this.clienteChegou = mutex.newCondition();
        this.cadeiraLivre = mutex.newCondition();
        this.barbeiroOcupado = false;
        this.clienteAtual = null;
    }
    
    public void chegarNaBarbearia(int numeroCliente) throws InterruptedException {
        mutex.lock();
        try {
            System.out.println("Cliente " + numeroCliente + " chegou na barbearia");
            if (barbeiroOcupado && filaEspera.size() >= numeroMaximoCadeirasEspera) {
                System.out.println("Cliente " + numeroCliente + " foi embora - barbearia lotada");
                return;
            }
            
            if (!barbeiroOcupado) {
                barbeiroOcupado = true;
                clienteAtual = numeroCliente;
                System.out.println("Cliente " + numeroCliente + " acordou o barbeiro e sentou na cadeira");
                clienteChegou.signal();
            } else {
                filaEspera.offer(numeroCliente);
                System.out.println("Cliente " + numeroCliente + " sentou na sala de espera. Posição: " + filaEspera.size());
                
                while (filaEspera.contains(numeroCliente)) {
                    cadeiraLivre.await();
                }
            }
            
        } finally {
            mutex.unlock();
        }
    }
    
    public void cortarCabelo() throws InterruptedException {
        mutex.lock();
        try {
            while (!barbeiroOcupado) {
                System.out.println("Barbeiro está cochilando...");
                clienteChegou.await();
            }
            
            if (clienteAtual != null) {
                System.out.println("Barbeiro está cortando o cabelo do cliente " + clienteAtual);
            }
            
        } finally {
            mutex.unlock();
        }
    
        Thread.sleep((long)(Math.random() * 3000 + 2000));
        
        mutex.lock();
        try {
            if (clienteAtual != null) {
                System.out.println("Barbeiro terminou de cortar o cabelo do cliente " + clienteAtual);
                clienteAtual = null;
            }
            
            if (!filaEspera.isEmpty()) {
                clienteAtual = filaEspera.poll();
                System.out.println("Cliente " + clienteAtual + " saiu da sala de espera e sentou na cadeira do barbeiro");
                cadeiraLivre.signalAll();
            } else {
                barbeiroOcupado = false;
                System.out.println("Não há mais clientes. Barbeiro vai cochilar...");
            }
            
        } finally {
            mutex.unlock();
        }
    }
    
    public int getNumeroClientesEsperando() {
        mutex.lock();
        try {
            return filaEspera.size();
        } finally {
            mutex.unlock();
        }
    }
    
    public boolean isBarbeiroOcupado() {
        mutex.lock();
        try {
            return barbeiroOcupado;
        } finally {
            mutex.unlock();
        }
    }
}
