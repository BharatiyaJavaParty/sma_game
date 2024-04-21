
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
         e.printStackTrace();
       }
    }
 
    @FXML
    public void scoreboardButtonClicked() {
      try
      {
         Stage scoreStage = new Stage();
         scoreStage.setTitle("SCOREBOARD");
         Scene scoreScene = new Scene(Main.loadFXML("scoreboard"));
         scoreStage.setScene(scoreScene);
         scoreStage.show();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
    }
}
