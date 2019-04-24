package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Date;
import model.MealList;
import model.Save;
import sun.misc.ObjectInputFilter;
import view.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Mohammed Al-Ahmed
 */
public class MonthDataController {
    //These to fields are needed for the buttonHandler and initializing scene to go back, Leave as is.
    private Stage mainStage = Driver.getMainStage();
    @FXML
    private Button backButton;
    @FXML
    private Label dateLabel;
    @FXML
    ListView breakfastListveiw;
    @FXML
    ListView lunchListveiw;
    @FXML
    ListView dinnerListveiw;
    @FXML
    Button previousDay;
    @FXML
    Button nextDay;

    public void setDateLabelContent(String text)
    {
        this.dateLabel.setText(text);
    }

    public void goNext()
    {
        if (Integer.parseInt(dateLabel.getText())==31)
            return;
        dateLabel.setText(Integer.parseInt((dateLabel.getText()))+1+"");
        LastMonthData();
    }
    public void goBack()
    {
        if (Integer.parseInt(dateLabel.getText())==1)
            return;
        dateLabel.setText(Integer.parseInt((dateLabel.getText()))-1+"");

        LastMonthData();


    }


    public void setDateOnEntrance()
    {
        dateLabel.setText(Driver.save.getDay()+"");
    }



    //If i need any references look for the fx:id in the fxml file/scene builder.
    //MEAT GOES HERE. CHINCHOPA CHINCHOPA
    public void LastMonthData() {


//        ObjectInputStream file = null;
//        MealList theScannedList= new MealList();
//        try {
//            file = new ObjectInputStream(new FileInputStream("savedMealData.bin"));
//        } catch (IOException e) {
//            System.out.println("FileNotFoud");
//        }
//
//        for (int i=0; i< Integer.parseInt(dateLabel.getText());i++)
//        {
//
//            try {
//                System.out.println(theScannedList);
//                theScannedList = (MealList) file.readObject();
//            } catch (Exception e) {
//                System.out.println("exception was thrown when reading the file for graphs");
//            }
//        }
        breakfastListveiw.getItems().clear();
        lunchListveiw.getItems().clear();
        dinnerListveiw.getItems().clear();

        if (Integer.parseInt(dateLabel.getText())>Driver.save.getDay())
            return;;
//        breakfastListveiw.getItems().addAll(theScannedList.getTheList().get(0));
//        lunchListveiw.getItems().addAll(theScannedList.getTheList().get(1));
//        dinnerListveiw.getItems().addAll(theScannedList.getTheList().get(2));


        breakfastListveiw.getItems().addAll(Driver.save.getByIndex(Integer.valueOf(dateLabel.getText())-1).getTheList().get(0));
        lunchListveiw.getItems().addAll(Driver.save.getByIndex(Integer.parseInt(dateLabel.getText())-1).getTheList().get(1));
        dinnerListveiw.getItems().addAll(Driver.save.getByIndex(Integer.parseInt(dateLabel.getText())-1).getTheList().get(2));


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
