package view;

import controller.DailyGoalsController;
import controller.MainController;
import controller.MonthDataController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hussain Al-Bin Hajji
 */
public class Driver extends Application {

    private static Stage mainStage;
    public static Save save = null; //This should be the one and only Save object, call it from anywhere using Driver.save
    public static Date today = new Date();
    public static Date lastDay;

    public static Stage getMainStage()
    {
        return mainStage;
    }

    public void start(Stage primaryStage) throws Exception
    {
        mainStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        MainController mainController = new MainController();
        loader.setController(mainController);
        Parent root = loader.load();

        mainController.setDateLabelText(new Date().toString());

        ObjectInputStream in;
        try
        {
            in = new ObjectInputStream(new FileInputStream("savedMealData.bin"));
            save = (Save) in.readObject();
            lastDay = (Date) in.readObject();

            if (today.getDay() != lastDay.getDay()) {
            	save.addOneDayLog(save.getDay(), new MealList());
            }


        }
        catch (IOException e)
        {
            save = new Save();
            lastDay = new Date();
        }



        primaryStage.setTitle("Diet Tracker");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }



    public static void readExcelFile(ObservableList<Edible> data) throws IOException
    {
        Scanner excelValueSheet = new Scanner( new FileInputStream("nutritionvalues-data.csv"));
        excelValueSheet.nextLine();
        excelValueSheet.nextLine();

        while (excelValueSheet.hasNext())
        {
            String fullLine = excelValueSheet.nextLine();
            if (fullLine.equalsIgnoreCase(",,,,,,"))
                break;
            String edibleName,unit;
            double portion, cal,fat,carbs,protien;

            protien=Double.parseDouble(fullLine.substring(fullLine.lastIndexOf(",")+1));

            fullLine=fullLine.substring(0, fullLine.lastIndexOf(","));


            carbs=Double.parseDouble(fullLine.substring(fullLine.lastIndexOf(",")+1));

            fullLine=fullLine.substring(0, fullLine.lastIndexOf(","));


            fat=Double.parseDouble(fullLine.substring(fullLine.lastIndexOf(",")+1));

            fullLine=fullLine.substring(0, fullLine.lastIndexOf(","));

            cal=Double.parseDouble(fullLine.substring(fullLine.lastIndexOf(",")+1));

            fullLine=fullLine.substring(0, fullLine.lastIndexOf(","));


            unit=fullLine.substring(fullLine.lastIndexOf(",")+1);
            fullLine=fullLine.substring(0, fullLine.lastIndexOf(","));


            portion=Double.parseDouble(fullLine.substring(fullLine.lastIndexOf(",")+1));
            edibleName=fullLine.substring(0, fullLine.lastIndexOf(","));

            if (carbs + protien + fat <= portion)
            {
                if (unit.equalsIgnoreCase("g"))
                {
                    Food tempFood= new Food(edibleName, cal, portion,fat,carbs,protien);
                    data.add(tempFood);
                }
                else if (unit.equalsIgnoreCase("ml"))
                {
                    Bevarage tempBeverage= new Bevarage(edibleName, cal, portion,fat,carbs,protien);
                    data.add(tempBeverage);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
