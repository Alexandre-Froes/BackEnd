public class App {
    public static void main(String[] args) throws Exception {
        StringBuffer buffer = new StringBuffer();

        VerificarOrtografia ortografia = new VerificarOrtografia(buffer);
        LeituraTeclado leitura = new LeituraTeclado(buffer);
        SalvarAquivo salvar = new SalvarAquivo(buffer);

        Thread t1 = new Thread(ortografia);
        Thread t2 = new Thread(leitura);
        Thread t3 = new Thread(salvar);

        t1.start();
        t2.start();
        t3.start();

        t1.sleep(0);
        t2.sleep(0);
        t3.sleep(0);

        System.out.println("Tchau");
    }
}
