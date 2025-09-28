import java.util.concurrent.locks.StampedLock;

public class BancoDados {
    private final StampedLock lockOtimista = new StampedLock();
    private String dados = "Dados_Iniciais_V1.0";
    private int contadorLeitores = 0;
    private long versao = 1;
    
    public void iniciarLeitura(int numeroLeitor) {
        long stamp = lockOtimista.readLock();
        try {
            contadorLeitores++;
            System.out.println("Leitor " + numeroLeitor + " iniciou leitura. Leitores ativos: " + contadorLeitores);
            System.out.println("Leitor " + numeroLeitor + " lendo dados: " + dados);
        } finally {
            lockOtimista.unlockRead(stamp);
        }
    }
    
    public void finalizarLeitura(int numeroLeitor) {
        long stamp = lockOtimista.readLock();
        try {
            contadorLeitores--;
            System.out.println("Leitor " + numeroLeitor + " finalizou leitura. Leitores ativos: " + contadorLeitores);
        } finally {
            lockOtimista.unlockRead(stamp);
        }
    }
    
    public void iniciarEscrita(int numeroEscritor, String novosDados) {
        long stamp = lockOtimista.writeLock();
        try {
            System.out.println("Escritor " + numeroEscritor + " aguardou acesso exclusivo e iniciou escrita");
            String dadosAntigos = this.dados;
            this.dados = novosDados;
            this.versao++;
            System.out.println("Escritor " + numeroEscritor + " atualizou dados de '" + dadosAntigos + "' para '" + novosDados + "'");
            System.out.println("Nova versao do banco: " + versao);
        } finally {
            lockOtimista.unlockWrite(stamp);
        }
    }
    
    public void finalizarEscrita(int numeroEscritor) {
        System.out.println("Escritor " + numeroEscritor + " finalizou escrita e liberou acesso exclusivo");
    }
    
    public void lerComOtimizacao(int idLeitor) {
        long stamp = lockOtimista.tryOptimisticRead();
        String dadosLidos = dados;
        long versaoLida = versao;
        
        if (!lockOtimista.validate(stamp)) {
            stamp = lockOtimista.readLock();
            try {
                dadosLidos = dados;
                versaoLida = versao;
                System.out.println("Leitor otimista " + idLeitor + " teve que fazer leitura pessimista devido a escrita concorrente");
            } finally {
                lockOtimista.unlockRead(stamp);
            }
        } else {
            System.out.println("Leitor otimista " + idLeitor + " fez leitura otimista com sucesso");
        }
        
        System.out.println("Leitor otimista " + idLeitor + " leu dados: " + dadosLidos + " (versao " + versaoLida + ")");
    }
    
    public String getDados() {
        long stamp = lockOtimista.tryOptimisticRead();
        String dadosAtuais = dados;
        
        if (!lockOtimista.validate(stamp)) {
            stamp = lockOtimista.readLock();
            try {
                dadosAtuais = dados;
            } finally {
                lockOtimista.unlockRead(stamp);
            }
        }
        
        return dadosAtuais;
    }
    
    public int getContadorLeitores() {
        long stamp = lockOtimista.tryOptimisticRead();
        int contador = contadorLeitores;
        
        if (!lockOtimista.validate(stamp)) {
            stamp = lockOtimista.readLock();
            try {
                contador = contadorLeitores;
            } finally {
                lockOtimista.unlockRead(stamp);
            }
        }
        
        return contador;
    }
    
    public long getVersao() {
        long stamp = lockOtimista.tryOptimisticRead();
        long versaoAtual = versao;
        
        if (!lockOtimista.validate(stamp)) {
            stamp = lockOtimista.readLock();
            try {
                versaoAtual = versao;
            } finally {
                lockOtimista.unlockRead(stamp);
            }
        }
        
        return versaoAtual;
    }
}