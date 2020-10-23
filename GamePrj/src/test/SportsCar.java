package test;

public class SportsCar extends Car {

	@Override
	public boolean drive() {
		boolean driving = getDriving();
		if(driving)
			return true;
		return false;
	}
	
}
