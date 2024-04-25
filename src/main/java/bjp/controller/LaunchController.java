package bjp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import bjp.Main;

public class LaunchController {
   @FXML
   private Button newGameButton;

   @FXML
   private Button scoreboardButton;

   @FXML
   public void newGameButtonClicked() {
      try {
         Main.setRoot("PlayerNameDialog");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @FXML
   public void scoreboardButtonClicked() {
      try {
         Main.setRoot("scoreboard");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
