
package bjp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import bjp.Main;


public class LaunchController {
    @FXML
    private Button newGameButton;
    @FXML
    private Button scoreboardButton;

    @FXML
    public void newGameButtonClicked() {
       try
       {
            Main.setRoot("PlayerNameDialog");
       }
       catch(Exception e)
       {
         System.out.println("madarchod\n\n\n\n\n");
         e.printStackTrace();
       }
    }
 
    @FXML
    public void scoreboardButtonClicked() {
       // Handle "Scoreboard" button click
    }
}
