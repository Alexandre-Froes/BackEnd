package sim;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {
        public static String formatarMoeda(double valor) {
        Locale brasil = new Locale("pt", "br");
        return NumberFormat.getCurrencyInstance(brasil).format(valor);
    }
}
