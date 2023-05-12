package upeu.entities;

import java.util.Date;

public class Person {

	public String name = "Pedro";
	private char sex;
	protected Date birthday;
	public String dni;

	
	public void laugh() {
	 System.out.println(name + " esta riendo...y su dni es "+dni);
	}

	public void eat() {
		System.out.println(name + " esta comiendo....y su dni es "+dni);
	}

	public void walk() {
		System.out.println(name+ " esta caminando...y su dni es "+dni);
	}

}