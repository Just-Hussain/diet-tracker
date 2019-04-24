package model;

import java.io.*;
import java.util.*;

import view.Driver;

/**
 * @author Salman Al-Ghamdi
 */
public class Save implements Serializable {

	private int tracker;
	private Integer day;
	private LinkedHashMap<Integer, MealList> saveLog;
	private MealList mealList;

	public Save() {
		saveLog = new LinkedHashMap<>(31);
		mealList = new MealList();
		day = 1;
		tracker = 1;
	}

	public Integer getDay()
	{
		return day;
	}

	public LinkedHashMap<Integer, MealList> getLog() {
		return this.saveLog;
	}

	public void addOneDayLog(int day, MealList log)
	{
		saveLog.put(day, log);
	}

	public void addLog(Meal meal, String mealName)
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedMealData.bin"));
			this.saveLog = ((Save) in.readObject()).saveLog;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (Driver.today.getDay() == Driver.lastDay.getDay())
		{
			addLogHelper(meal, mealName);
		}
		else
		{
			tracker++;
			
			if (tracker > 31)
			{
				int tmpTracker = tracker/31;
				day = tracker - (tmpTracker * 31);
				saveLog.remove(day);
			}
			else
			{
				day++;
			}

			addLogHelper(meal, mealName);
			Driver.lastDay = Driver.today;
		}

		ObjectOutputStream out;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream("savedMealData.bin"));
			out.writeObject(this);
			out.writeObject(Driver.today);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private void addLogHelper(Meal meal, String mealName)
	{
		if (saveLog.containsKey(day))
		{
			MealList tmp = saveLog.get(day);
			specifyMeal(meal, mealName, tmp);
			saveLog.put(day, tmp);
		}
		else
		{
			MealList newMealList = new MealList();
			specifyMeal(meal, mealName, newMealList);
			saveLog.put(day, newMealList);
		}
	}

	private void specifyMeal(Meal meal, String mealName, MealList mealList)
	{
		if (mealName.equalsIgnoreCase("BREAKFAST"))
			mealList.add(0, meal);
		else if (mealName.equalsIgnoreCase("LUNCH"))
			mealList.add(1, meal);
		else
			mealList.add(2, meal);
	}

	public double getDayCalories(int index) {
		return getByIndex(index).sumAllCalories();
	}

	public double getDayProtein(int index) {
		return getByIndex(index).sumAllProtein();
	}

	public double getDayCarbo(int index) {
		return getByIndex(index).sumAllCarbo();
	}

	public double getDayFats(int index) {
		return getByIndex(index).sumAllFats();
	}

	public double getDayBevarage(int index) {
		return getByIndex(index).sumAllBevarage();
	}
	
	public MealList getByIndex(int index) {
		ArrayList<MealList> list = new ArrayList<>(saveLog.values());
		return list.get(index);
	}
}
