package model;

/**
 * @author Mohammed Al-Ahmed
 */ 
public class Bevarage extends Edible {

	public Bevarage(String name, double calories, double portion, double fat, double carbohydrates, double protein) {
		super(name, calories, portion, fat, carbohydrates, protein);
	}

	public Bevarage()
	{
		super();
	}

	public String toString() {
		return getName();
	}
}
