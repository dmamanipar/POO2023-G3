
public class Persona {
    public   String nombre;
    public  int edad;
    
    public String getNombre() { return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre; }

    public int getEdad() {return edad;}
    public void setEdad(int edad) { this.edad = edad;}

    public  void saludo() {
        System.out.println("Hola, soy "+nombre+" y mi edad es:"+edad);
    }
}
