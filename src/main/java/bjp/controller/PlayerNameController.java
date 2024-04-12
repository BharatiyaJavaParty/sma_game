
package bjp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import bjp.Main;
import bjp.utility.Player;


public class PlayerNameController {

    @FXML
    private TextField textField;

    @FXML
    public void okButtonClicked() {
       try
       {
            Player.setPlayerName(textField.getText());
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
            Main.setRoot("CityMap");
            Player.setPlayerName("Player1");
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
    }
}

