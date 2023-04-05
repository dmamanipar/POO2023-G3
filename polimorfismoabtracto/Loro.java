package polimorfismoabtracto;

public class Loro extends Animal{

    @Override
    public void sonidoAnimal() {//polimorfismo
        System.out.println("Hola buen día!!");
    }
    
    public void dormir(){//polimorfismo
        System.out.println("Ire a dormir ...favor de no molestar");
    }
}


class Principal {

    public static void main(String[] args) {
        Animal objA=new Loro();
        objA.sonidoAnimal();
        objA.dormir();
    }
    
}