package Main;

public class EGrau2 {

    public double a, b, c;

    EGrau2(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public double delta() {
        return Math.pow(b, 2) - (4.0*a*c);
    }
    public double calculaX1(double d) {
        return (-b + Math.sqrt(d)) / (2.0*a);
    }
    public double calculaX2(double d) {
        return (-b - Math.sqrt(d)) / (2.0*a);
    }
}
