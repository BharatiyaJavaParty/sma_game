package bjp;

import bjparty.utility.Gem;
import bjparty.utility.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/bjparty/CityMap.fxml"));
            primaryStage.setTitle("City Map");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}
