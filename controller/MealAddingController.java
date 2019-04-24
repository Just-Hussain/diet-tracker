package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import view.Driver;

import java.io.IOException;

/**
 * @author Hussain Al-Bin Hajji
 */
public class MealAddingController {

    @FXML
    private CheckBox checkBoxFood;
    @FXML
    private CheckBox checkBoxBevarage;
    @FXML
    private TextField searchingTextField;
    @FXML
    private Label mealNameLabel;
    @FXML
    private Label helpLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button addButton;
    @FXML
    private ListView listView;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn foodCol, portionCol, caloriesCol, fatCol, carboCol, proteinCol;

    private static Stage mainStage = Driver.getMainStage();
    private ObservableList<Edible> data = MainController.getData();
    private ObservableList<Edible> addedData = FXCollections.observableArrayList();

    public void setTableView(ObservableList<Edible> observableList)
    {
        this.tableView.setItems(observableList);
        MouseHandler mouseHandler = new MouseHandler();
        this.tableView.setOnMouseClicked(mouseHandler);
        this.listView.setOnMouseClicked(mouseHandler);
    }

    public void setAllColContent()
    {
        this.foodCol.setCellValueFactory(
                new PropertyValueFactory<Edible, String>("name")
        );

        this.caloriesCol.setCellValueFactory(
                new PropertyValueFactory<Edible, Double>("calories")
        );

        this.portionCol.setCellValueFactory(
                new PropertyValueFactory<Edible, Double>("portion")
        );

        this.fatCol.setCellValueFactory(
                new PropertyValueFactory<Edible, Double>("fat")
        );

        this.carboCol.setCellValueFactory(
                new PropertyValueFactory<Edible, Double>("carbohydrates")
        );

        this.proteinCol.setCellValueFactory(
                new PropertyValueFactory<Edible, Double>("protein")
        );
    }

    public void setMealNameLabelText(String text)
    {
        this.mealNameLabel.setText(text);
    }


    private KeyEvent searchKeyEvent = null;
    public void SearchFieldHandler(KeyEvent event)
    {
        searchKeyEvent = event;

        ObservableList<Edible> keywordData = FXCollections.observableArrayList();
        String keyword = searchingTextField.getText().toUpperCase();

        if (!keyword.equalsIgnoreCase(""))
        {
            if (checkBoxFood.isSelected() && !checkBoxBevarage.isSelected())
            {
                for (int i = 0; i < data.size(); i++)
                {
                    if (data.get(i).getName().toUpperCase().contains(keyword)
                        && data.get(i) instanceof Food)
                    {
                        keywordData.add(data.get(i));
                    }
                }
                tableView.setItems(keywordData);
            }
            else if (checkBoxBevarage.isSelected() && !checkBoxFood.isSelected())
            {
                for (int i = 0; i < data.size(); i++)
                {
                    if (data.get(i).getName().toUpperCase().contains(keyword)
                        &&  data.get(i) instanceof Bevarage)
                    {
                        keywordData.add(data.get(i));
                    }
                }
                tableView.setItems(keywordData);
            }
            else if (checkBoxFood.isSelected() && checkBoxBevarage.isSelected())
            {
                for (int i = 0; i < data.size(); i++)
                {
                    if (data.get(i).getName().toUpperCase().contains(keyword))
                    {
                        keywordData.add(data.get(i));
                    }
                }
                tableView.setItems(keywordData);
            }
            else
            {
                tableView.getItems().clear();
            }
        }
        else
        {
            if (!checkBoxFood.isSelected() && !checkBoxBevarage.isSelected())
            {
                tableView.getItems().clear();
            }
            else if (checkBoxFood.isSelected() && checkBoxBevarage.isSelected())
            {
                tableView.setItems(data);
            }
        }
    }

    public void checkBoxHandler(ActionEvent event)
    {
        ObservableList<Edible> filteredData = FXCollections.observableArrayList();

        if (checkBoxFood.isSelected() && !checkBoxBevarage.isSelected())
        {
            for (int i = 0; i < data.size(); i++)
            {
                if (data.get(i) instanceof Food)
                {
                    filteredData.add(data.get(i));
                }
            }
        }
        else if (checkBoxBevarage.isSelected() && !checkBoxFood.isSelected())
        {
            for (int i = 0; i < data.size(); i++)
            {
                if (data.get(i) instanceof Bevarage)
                {
                    filteredData.add(data.get(i));
                }
            }
        }
        else if (checkBoxFood.isSelected() && checkBoxBevarage.isSelected() && searchingTextField.getText().equalsIgnoreCase(""))
        {
            filteredData.addAll(data);
        }
        else if (checkBoxFood.isSelected() && checkBoxBevarage.isSelected())
        {
            filteredData.addAll(data);
        }
        else
        {
            tableView.getItems().clear();
        }
        tableView.setItems(filteredData);
        SearchFieldHandler(searchKeyEvent);
    }


    public void buttonHandler(ActionEvent actionEvent) throws IOException
    {
        if (actionEvent.getSource() == backButton)
        {
            try
            {
                backHandler();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (actionEvent.getSource() == addButton)
        {
            Meal meal = new Meal();
            for (int i = 0; i < addedData.size(); i++)
            {
                meal.add(addedData.get(i));
            }
            Driver.save.addLog(meal, mealNameLabel.getText());

            backHandler();
        }

    }


    private void backHandler() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        MainController mainController = new MainController();
        loader.setController(mainController);
        Parent root = loader.load();

        mainController.setDateLabelText(new Date().toString());

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }



    private class MouseHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            if (event.getClickCount() == 2)
            {
                if (event.getSource() == tableView)
                {
                    helpLabel.setVisible(false);
                    Edible edible = (Edible) tableView.getSelectionModel().getSelectedItem();
                    if (edible != null)
                    {
                        addedData.add(edible);
                    }
                    listView.setItems(addedData);
                }
                else if (event.getSource() == listView)
                {
                    Edible edible = (Edible) listView.getSelectionModel().getSelectedItem();
                    addedData.remove(edible);
                    listView.setItems(addedData);
                }
            }
        }
    }
}
