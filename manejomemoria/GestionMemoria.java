package manejomemoria;

import enumerators.Persona;

public class GestionMemoria {    
    static Operacion op;
    static Operacion op2=new Operacion(4, 3);
    static int resultado=0;
    static int[] vecl={2, 4, 5};
    static int[] vecx;
    static int[][] m;

    public static void main(String[] args) {
        op=new Operacion();
        op.a=8;
        op.b=4;
        resultado=op.suma();
        op=null;
        Operacion op3=op2;    
        Operacion[] op4={
             new Operacion(2, 4),
             new Operacion(6, 4)
            };
    }
}

class Operacion{
    int a;
    int b;
    char operador;

    Operacion(){}
    Operacion(int a, int b){ this.a=a; this.b=b; }
    public int suma(){ return a+b;}
    public int multiplicar(){return a*b; }

}