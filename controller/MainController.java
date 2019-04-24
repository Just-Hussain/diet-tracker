package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Bevarage;
import model.Edible;
import model.Food;
import view.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 * @author Hussain Al-Bin Hajji 
 */
public class MainController {

    private static Stage mainStage = Driver.getMainStage();
    private static ObservableList<Edible> data = FXCollections.observableArrayList();

    public static ObservableList<Edible> getData()
    {
        return data;
    }

    @FXML
    private Label dateLabel;
    @FXML
    private VBox breakfastContainer;
    @FXML
    private VBox lunckContainer;
    @FXML
    private VBox dinnerContainer;
    @FXML
    private Button breakfastButton, lunchButton, dinnerButton;
    @FXML
    private Button lastMonthButton, monthlyButton, dailyButton;

    public void setDateLabelText(String date)
    {
        dateLabel.setText(date);
    }

    public void mealClickHandler(MouseEvent mouseEvent)
    {
        String fxmlPath = "/view/MealAndDateView.fxml";
        if (mouseEvent.getSource() == breakfastContainer || mouseEvent.getSource() == breakfastButton)
        {
            loadMealsScene(mainStage, fxmlPath, "Breakfast");
        }
        else if (mouseEvent.getSource() == lunckContainer || mouseEvent.getSource() == lunchButton)
        {
            loadMealsScene(mainStage, fxmlPath, "Lunch");
        }
        else
        {
            loadMealsScene(mainStage, fxmlPath, "Dinner");
        }
    }

    public void dataClickHandler(ActionEvent event)
    {
        String graphViewPath = "/view/graphView.fxml";
        String dailyGoalsViewPath = "/view/dailyGoalsView.fxml";
        String monthDataViewPath = "/view/monthDataView.fxml";

        try
        {
            if (event.getSource() == dailyButton)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(dailyGoalsViewPath));
                DailyGoalsController dailyGoalsController = new DailyGoalsController();
                loader.setController(dailyGoalsController);
                
                Parent root = loader.load();
                
                //If u want something to be loaded and shown directly after loading the scene,
                //Call the method here, the method will be in the scene controller.
                //So u can call it using the controller object above.
                
                dailyGoalsController.ProgressBarHandler();
                
                
                mainStage.setScene(new Scene(root));
                mainStage.show();
            }
            else if (event.getSource() == lastMonthButton)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(monthDataViewPath));
                MonthDataController monthDataController = new MonthDataController();
                loader.setController(monthDataController);
                Parent root = loader.load();
                
                //If u want something to be loaded and shown directly after loading the scene,
                //Call the method here, the method will be in the scene controller.
                //So u can call it using the controller object above.

                monthDataController.setDateOnEntrance();
                monthDataController.LastMonthData();
                monthDataController.setDateLabelContent(Driver.save.getDay() + "");


                mainStage.setScene(new Scene(root));
                mainStage.show();
            }
            else if (event.getSource() == monthlyButton)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(graphViewPath));
                GraphsController graphsController = new GraphsController();
                loader.setController(graphsController);
                Parent root = loader.load();


                //If u want something to be loaded and shown directly after loading the scene,
                //Call the method here, the method will be in the scene controller.
                //So u can call it using the controller object above.



                mainStage.setScene(new Scene(root));
                mainStage.show();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private void loadMealsScene(Stage stage, String fxmlPath, String mealName)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            MealAddingController mealAddingController = new MealAddingController();
            loader.setController(mealAddingController);

            Parent root = loader.load();

            mealAddingController.setMealNameLabelText(mealName);

            Driver.readExcelFile(data);

            mealAddingController.setAllColContent();
            mealAddingController.setTableView(data);

            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



}
