package model;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Salman Al-Ghamdi
 */
public class MealList implements Serializable{

	private ArrayList<Meal> theList;

	public MealList() {
		theList = new ArrayList<>(3);
		theList.add(new Meal()); // 0 -> Breakfast.
		theList.add(new Meal()); // 1 -> Lunch.
		theList.add(new Meal()); // 2 -> Dinner.
	}

	public ArrayList<Meal> getTheList(){
		return theList;
	}

	public void add(Meal meal) {
		theList.add(meal);
	}

	public void add(int pos, Meal meal) {
		theList.add(pos, meal);
	}

	public double sumAllCalories() {

		double sumCal = 0.0;
		for (int i = 0; i < theList.size(); i++) {
			sumCal += theList.get(i).sumCalories();
		}

		return sumCal;
	}

	public double sumAllProtein() {

		double sumPro = 0.0;
		for (int i = 0; i < theList.size(); i++) {
			sumPro += theList.get(i).sumProtein();
		}

		return sumPro;
	}

	public double sumAllCarbo() {

		double sumCarbo = 0.0;
		for (int i = 0; i < theList.size(); i++) {
			sumCarbo += theList.get(i).sumCarbo();
		}

		return sumCarbo;
	}

	public double sumAllFats() {

		double sumFat = 0.0;
		for (int i = 0; i < theList.size(); i++) {
			sumFat += theList.get(i).sumFats();
		}

		return sumFat;
	}

	public double sumAllBevarage() {
		double sumML = 0.0;
		for(int i = 0; i < theList.size(); i++) {
			sumML += theList.get(i).sumBevarage();
		}
		return sumML;
	}


}
