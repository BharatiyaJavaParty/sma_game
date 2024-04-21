package bjp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import bjp.utility.GameEngine;
import bjp.Main;

public class PlayerNameController {

    public String newPlayerName;

    @FXML
    private TextField textField;

    @FXML
    public void okButtonClicked() {
       try
       {
            GameEngine.newPlayer.setPlayerName(textField.getText());
            Main.setRoot("CityMap");
       }
       catch(Exception e)
       {
            e.printStackTrace();
       }
    }
 
    @FXML
    public void cancelButtonClicked() {
        try
        {
            GameEngine.newPlayer.setPlayerName("Player1");
            Main.setRoot("CityMap");
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
    }
}

