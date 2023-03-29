public class ClaseGeneral {

    public static void main(String[] args) {
       /* Persona.nombre="Dario";
        Persona.edad=20;
        Persona.saludo();*/

        Persona p1Obj=new Persona(); //Concepto de Objetos
        p1Obj.nombre="Raul";
        p1Obj.edad=21;
        p1Obj.saludo();

        p1Obj=new Persona();
        p1Obj.setNombre("Pedro");//Encapsulamiento
        p1Obj.setEdad(22);//Encapsulamiento
        p1Obj.saludo();
    }
}
