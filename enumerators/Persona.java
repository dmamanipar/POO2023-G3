package enumerators;


public class Persona {
    String nombre;
    String genero;
    String estado_civil;
    enum GENERO{Femenino, Masculino}
    enum ESTADO_CIVIL{Soltero, Casado, Divorciado, Viudo}    
    public static void main(String[] args) {
        System.out.println("Una persona puede pasar por diferentes estados:");
        Persona p=new Persona();
        p.nombre="Dario";        
        p.genero=GENERO.Masculino.toString();
        System.out.println(p.nombre+ "  es "+ p.genero + 
        " ha pasado por distintos estados:");
        for (ESTADO_CIVIL ec : ESTADO_CIVIL.values()) {            
            System.out.println(ec);
        }
    }
}
