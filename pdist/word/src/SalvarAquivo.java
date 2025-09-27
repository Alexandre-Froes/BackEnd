import java.io.File;
import java.io.PrintWriter;

public class SalvarAquivo implements Runnable {
    private StringBuffer buffer;

    private static final String PATH = "C:/Users/aluno/Documents/alexandre-sistemas/texto.txt";

    public SalvarAquivo(StringBuffer buffer) {
        this.buffer = buffer;
    }    

    @Override
    public void run() {
        while (true) {
            try {
                File file = new File(PATH);
                PrintWriter pw = new PrintWriter(file);

                pw.write(buffer.toString());
                pw.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
