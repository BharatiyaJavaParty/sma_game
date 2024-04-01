package bjparty;

import bjparty.utility.Location;
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
        Map<Location, Integer> visited = new HashMap<Location, Integer>();
        LocationsConfig.CITY_GRAPH.forEach(((location, locations) -> {
            visited.put(location, 0);
        }));
        Queue<Location> q = new LinkedList<Location>();
        q.add(LocationsConfig.TALLAGHT);
        AtomicBoolean flag = new AtomicBoolean(true);
        while(!q.isEmpty() && flag.get()){
            Location temp = q.remove();
            System.out.print(temp.getLocationName()+"+>");
            LocationsConfig.CITY_GRAPH.get(temp).forEach((neighbour)->{
                if(neighbour.equals(LocationsConfig.POINT)) {
                    System.out.println(neighbour.getLocationName());
                    flag.set(false);
                }
                if(visited.get(neighbour)==0){
                    visited.replace(neighbour, 1);
                    q.add(neighbour);
                }
            });
        }
        launch();
    }
}

//variables which should be common to runtime of the game
//gemCount
//time / timer
//score
//level