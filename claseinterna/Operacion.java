package claseinterna;

import claseinterna.Operacion.OperMatematicos;
public class Operacion {
    int a;
    int b;
    public int suma() {
        return a+b;
    }
    public class OperMatematicos {
    int resultados;
    public double potencia(int num, int nump){
        return Math.pow(num, nump);
    }        
    }
}

class Principal {
public static void main(String[] args) {
    Operacion o=new Operacion();
    OperMatematicos om=o.new OperMatematicos();
    System.out.println(om.potencia(2, 3));
}    
}
