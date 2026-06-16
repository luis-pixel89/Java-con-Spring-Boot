package com.krakedev.evaluacion;

public class Animal {
	public void dormir() {
		System.out.println("Animal durmiendo");
	}

	@Override
	public String toString() {
		return "Animal [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
