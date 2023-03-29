public class Carro extends Vehiculo {//Clase Hijo
    public int vigencia_periodo_soat=1;

    public void mostrarCaracteristica(){
        System.out.println("Este carro tiene las siguiemtes caracteristicas:");
        System.out.println("Marca: "+marca);
        color="Rojo";
        System.out.println("Color:"+color);
        modelo="Yaris";
        System.out.println("Modelo: "+modelo);
        System.out.println("y emite el siguiente sonido: "+ sonido());
        System.out.println("y la videncia del soat es:"+vigencia_periodo_soat);
    }

    public static void main(String[] args) {
        Carro objC=new Carro();
        objC.mostrarCaracteristica();
        
    }
}
