public class App {
    public static void main(String[] args) throws Exception{
        Corrida xande1 = new Corrida("Xande1");    
        Corrida xande2 = new Corrida("Xande2");    
        Corrida xande3 = new Corrida("Xande3");
        
        Thread thread1 = new Thread(xande1);
        Thread thread2 = new Thread(xande2);
        Thread thread3 = new Thread(xande3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Acabou a corrida");
    }
}
