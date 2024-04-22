package bjp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import bjp.utility.GameEngine;
import bjp.Main;

public class PlayerNameController {

    @FXML
    private TextField textField;

    @FXML
    public void okButtonClicked() {
        if (isValidInput(textField.getText())) {
            GameEngine.newPlayer.setPlayerName(textField.getText());
            changeScene();
        } else {
            showAlert("Invalid Input", "Please enter a valid name.");
        }
    }

    @FXML
    public void cancelButtonClicked() {
        GameEngine.newPlayer.setPlayerName("Player1");
        changeScene();
    }

    private void changeScene() {
        try {
            Main.setRoot("CityMap");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the next scene.");
        }
    }

    private boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
