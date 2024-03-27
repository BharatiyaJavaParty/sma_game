package bjparty.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import bjparty.Main;


public class LaunchController {
    @FXML
    private Button newGameButton;
    @FXML
    private Button scoreboardButton;

    @FXML
    public void newGameButtonClicked() {
       try
       {
            // Stage currentStage = (Stage) newGameButton.getScene().getWindow();
            // currentStage.close();
            //loads the main game
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            // Parent newGameRoot = loader.load();
            // Stage newGameStage = new Stage();
            // newGameStage.setTitle("New Game");
            // newGameStage.setScene(new Scene(newGameRoot));
            // newGameStage.show();
            Main.setRoot("hello-view");
       }
       catch(Exception e)
       {
            e.printStackTrace();
       }
    }
 
    @FXML
    public void scoreboardButtonClicked() {
       // Handle "Scoreboard" button click
    }
}

