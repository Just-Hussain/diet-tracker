package model;

/**
 * @author Mohammed Al-Ahmed
 */
public class Food extends Edible {
	public Food(String name, double calories, double portion, double fat, double carbohydrates, double protein) {
		super(name, calories, portion, fat, carbohydrates, protein);
	}

	public Food()
	{
		super();
	}

	public String toString() {
		return getName();
	}
}
