package polimorfismo;

public class Loro extends Animal {    

    public void sonidoAnimal() { //Polimorfismo
        System.out.println("Hola mi amigo....como te encuentra?");
    }
}

class Main {

    public static void main(String[] args) {
        Animal objL=new Loro();
        objL.nombre="Lorito";
        System.out.println(objL.nombre);
        objL.sonidoAnimal();
    }
    
}