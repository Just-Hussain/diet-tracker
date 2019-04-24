package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Date;
import view.Driver;

/**
 * @author Mohammed Al-Ahemd
 */
public class GraphsController {
    //These to fields are needed for the buttonHandler to go back, Leave as is.
    private Stage mainStage = Driver.getMainStage();
    @FXML
    private Button backButton;
    @FXML
    LineChart<String,Number> theLineGraph;
    @FXML
    Button caloriesButton;
    @FXML
    Button protienButton;
    @FXML
    Button carbsButton;
    @FXML
    Button fatsButton;
    @FXML
    Button bevButton;




    //If i need any references look for the fx:id in the fxml file/scene builder.
    //MEAT GOES HERE.

    public void setGraph(ActionEvent event)
    {
        theLineGraph.getData().clear();
        double temporary=0;
        theLineGraph.getXAxis().setLabel("Days");

        XYChart.Series<String, Number> theGraph = new XYChart.Series<String, Number>();

        for (int i=0; i<Driver.save.getLog().size();i++)
        {
            String myI=i+"";

            if (event.getSource()==caloriesButton)
            {
                temporary=Driver.save.getDayCalories(i);
                theLineGraph.getYAxis().setLabel("Calories");

            } else if(event.getSource()==protienButton)
            {
                temporary=Driver.save.getDayProtein(i);
                theLineGraph.getYAxis().setLabel("protien");
            }
            else if (event.getSource()==carbsButton)
            {
                temporary=Driver.save.getDayCarbo(i);
                theLineGraph.getYAxis().setLabel("Carbs");
            }
            else if (event.getSource()==fatsButton)
            {
                temporary=Driver.save.getDayFats(i);
                theLineGraph.getYAxis().setLabel("Fats");
            }
            else if (event.getSource()==bevButton)
            {
                temporary=Driver.save.getDayBevarage(i);
                theLineGraph.getYAxis().setLabel("Drinks in ml");
            }

            theGraph.getData().add(new XYChart.Data<String, Number>(myI ,temporary));
        }
        theLineGraph.getData().addAll(theGraph);

    }








    // This method handles going back to the main window, Leave as is.
    public void buttonHandler(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == backButton)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
                MainController mainController = new MainController();
                loader.setController(mainController);
                Parent root = loader.load();

                mainController.setDateLabelText(new Date().toString());

                mainStage.setScene(new Scene(root));
                mainStage.show();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
