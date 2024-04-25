package bjp.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import bjp.Main;
import bjp.utility.GameEngine;

public class GameOverController {
   @FXML
   private Button retryButton;

   @FXML
   private Button scoreButton;

   @FXML
   private Label message;

   @FXML
   private void initialize() {
      if (GameEngine.WIN){
         message.setText("Congratulations! You won!");
      } else {
         message.setText("You lost! Try again!");
      }
   }

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
         Main.setRoot("scoreboard");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}