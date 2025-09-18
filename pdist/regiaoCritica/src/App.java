public class App {
    public static void main(String[] args) throws Exception {
        Buffer buffer = new Buffer();

        Producer prod = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        new Thread(prod).start();
        new Thread(consumer).start();

        System.out.println("haha kk L esteve aqui");
    }
}
