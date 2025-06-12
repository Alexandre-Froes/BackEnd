package ocp.principle.violacao;

public class Pagamento {
    private static double salario;
    private static double bolsa;
    
    public static void setSalario(double sal){
        salario=sal;
    }
    
    public static void setBolsaAuxilio(double bol){
        bolsa=bol;
    }
    
    public static double salario(){
        return salario;
    }
    
    public static double bolsaAuxilio(){
        return bolsa;
    }
}


