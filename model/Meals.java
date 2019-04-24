package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Salman Al-Ghamdi
 */
public class Meals implements Serializable {
    private ArrayList<Meal> meals;

    public Meals()
    {
        meals = new ArrayList<>(3);
    }

    public void addMeal(Meal meal)
    {
        meals.add(meal);
    }

    public ArrayList<Meal> getMeals()
    {
        return meals;
    }
}
