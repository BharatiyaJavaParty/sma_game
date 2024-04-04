package bjparty;

import bjparty.utility.Location;
import bjparty.utility.GraphUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        //loads the fmxl file containing the first screen/ main screen of the game 
        //user has 2 options on this screen 
        stage.setTitle("Rush Hour");
        scene = new Scene(loadFXML("launch-view"), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        //player and pokemon loaction random select
        GraphUtil util = new GraphUtil();
        Map<String, Location> res = util.getSourceAndDestination();
        Location source = res.get("source");
        Location destination = res.get("destination");
        boolean flag = true;
        Location location = source;
        while(flag){
            if(location.equals(destination)){
                flag = false;
            }
            ArrayList<Location> l = util.getNeighbour(source);
            for( Location i : l){
            if(i==destination){
                System.out.println("Reached");
                break;
            }
            location = l.get(0);
            System.out.println(i.getLocationName()+"=>");
        }
        launch();
        }
        
    }
}

//variables which should be common to runtime of the game
//gemCount
//time / timer
//score
//level