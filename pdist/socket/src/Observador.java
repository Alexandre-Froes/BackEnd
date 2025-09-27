import java.io.IOException;

public interface Observador {
    public void enviaMsg(String msg) throws IOException;
}
