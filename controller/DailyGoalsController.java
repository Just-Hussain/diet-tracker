package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Date;
import view.Driver;

/**
 * @author Salman Al-Ghamdi
 */
public class DailyGoalsController {

	// These to fields are needed for the buttonHandler to go back, Leave as is.
	private Stage mainStage = Driver.getMainStage();
	@FXML
	private Button backButton;

	@FXML
	private ProgressBar calProgBar, protProgBar, carboProgBar, fatProgBar, bevProgBar;

	@FXML
	private Label calLabel, protLabel, carboLabel, fatLabel, bevLabel;

	// If i need any references look for the fx:id in the fxml file/scene builder.
	// MEAT GOES HERE.

	public void ProgressBarHandler() {
		
        
		int todayIndex = Driver.save.getLog().size() - 1;
		if(todayIndex >= 0) {
			
		System.out.println(Driver.save.getLog());
		// sets the calories progress bar and label
		calProgBar.setProgress(percentage(Driver.save.getDayCalories(todayIndex), 2000.0));
		calLabel.setText(Driver.save.getDayCalories(todayIndex) + "/2000");
		if (percentage(Driver.save.getDayCalories(todayIndex), 2000.0) >= 1.0)
			calLabel.setTextFill(Color.DARKGREEN);

		// sets the protein progress bar and label
		protProgBar.setProgress(percentage(Driver.save.getDayProtein(todayIndex), 100.0));
		protLabel.setText(Driver.save.getDayProtein(todayIndex) + "/100");
		if (percentage(Driver.save.getDayProtein(todayIndex), 100.0) >= 1.0)
			protLabel.setTextFill(Color.DARKGREEN);

		// sets the carbohydrates progress bar and label
		carboProgBar.setProgress(percentage(Driver.save.getDayCarbo(todayIndex), 300.0));
		carboLabel.setText(Driver.save.getDayCarbo(todayIndex) + "/300");
		if (percentage(Driver.save.getDayCarbo(todayIndex), 300.0) >= 1.0)
			carboLabel.setTextFill(Color.DARKGREEN);

		// sets the fats progress bar and label
		fatProgBar.setProgress(percentage(Driver.save.getDayFats(todayIndex), 50.0));
		fatLabel.setText(Driver.save.getDayFats(todayIndex) + "/50");
		if (percentage(Driver.save.getDayFats(todayIndex), 50.0) >= 1.0)
			fatLabel.setTextFill(Color.DARKGREEN);

		// sets the beverages progress bar and label
		bevProgBar.setProgress(percentage(Driver.save.getDayBevarage(todayIndex), 3000.0));
		bevLabel.setText(Driver.save.getDayBevarage(todayIndex) + "/3000");
		if (percentage(Driver.save.getDayBevarage(todayIndex), 3000.0) > 1.0)
			bevLabel.setTextFill(Color.DARKGREEN);
		}
		
	}

	public void buttonHandler(ActionEvent actionEvent) {
		if (actionEvent.getSource() == backButton) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
				MainController mainController = new MainController();
				loader.setController(mainController);
				Parent root = loader.load();

				mainController.setDateLabelText(new Date().toString());

				mainStage.setScene(new Scene(root));
				mainStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private double percentage(double amount, double goal) {
    	return amount/goal;
    }
	
	public  void clearBars() {
		calProgBar.setProgress(0);
		protProgBar.setProgress(0);
		carboProgBar.setProgress(0);
		fatProgBar.setProgress(0);
		bevProgBar.setProgress(0);
		
		calLabel.setText("0/2000");
		protLabel.setText("0/100");
		carboLabel.setText("0/300");
		fatLabel.setText("0/50");
		bevLabel.setText("0/3000");
	}

}
