package test;

public class Car {
	private Person[] passengers;
	public static Car instance;
	private static boolean driving;
	private Light[] lights;
	
	public Car() {
		passengers = new Person[5];
		instance = this;
		driving = false;
		lights = new Light[2];
	}
	
	public boolean turnLightOn() {
		
	}
	
	public boolean drive() {
		
	}
	
	public void add(Light light) {
		
	}
	
	public void setListener(LightListener listener) {
		
	}
}
