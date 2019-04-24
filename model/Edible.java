package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * @author Mohammed Al-Ahmed
 */
public class Edible implements Serializable {
	private String name;
	private double portion, calories, fat, carbohydrates, protein;

	public Edible(String name,
				  double calories,
				  double portion,
				  double fat,
				  double carbohydrates,
				  double protein)
	{
		//this.name = new SimpleStringProperty(name);
		this.name = name;
//		this.calories = new SimpleDoubleProperty(calories);
//		this.portion = new SimpleDoubleProperty(portion);
//		this.fat = new SimpleDoubleProperty(fat);
//		this.carbohydrates = new SimpleDoubleProperty(carbohydrates);
//		this.protein = new SimpleDoubleProperty(protein);
		this.calories = calories;
		this.portion = portion;
		this.fat = fat;
		this.carbohydrates = carbohydrates;
		this.protein = protein;
	}

	public Edible()
	{
		this.name = "No Food/Beverage";
		this.calories = 0.0;
		this.portion = 0.0;
		this.portion = 0.0;
		this.fat = 0.0;
		this.carbohydrates = 0.0;
		this.protein = 0.0;
	}

	// gets

	public String getName() {
		//return name.get();
		return name;
	}

	public double getCalories() {
		//return calories.get();
		return calories;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public double getPortion() {
		return portion;
	}

	public double getFat() {
		return fat;
	}

	public double getProtein() {
		return protein;
	}

//	 sets

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public void setName(String name) {
		//this.name.set(name);
		this.name = name;
	}

	public void setPortion(double portion) {
		this.portion = portion;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}
}
