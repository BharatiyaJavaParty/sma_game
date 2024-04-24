package bjp.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import bjp.Main;

public class GameOverController {
   @FXML
   private Button retryButton;

   @FXML
   private Button scoreButton;

   @FXML
   public void retryButtonClicked() {
      try {
         Main.setRoot("launch-view");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @FXML
   public void scoreButtonClicked() {
      try {
         Stage scoreStage = new Stage();
         scoreStage.setTitle("SCOREBOARD");
         Scene scoreScene = new Scene(Main.loadFXML("scoreboard"));
         scoreStage.setScene(scoreScene);
         scoreStage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}