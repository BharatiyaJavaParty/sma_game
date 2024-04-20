package bjp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import bjp.controller.CityMapController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class Obstacles {
    public static ArrayList<Location> Tree_SET = new ArrayList<>();
    private static final Image treeImg = new Image(Obstacles.class.getResourceAsStream("/img/tree.png"));
    public static ImageView imageView;
    
    static{
        Tree_SET.add(new Location("Tree1", 1, 1));
        Tree_SET.add(new Location("Tree2", 2, 2));
    }

    public static void placeTrees( GridPane cityMapGrid) {
        for (Location tree : Tree_SET) {
            imageView = new ImageView(treeImg);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, tree.getX(), tree.getY());
        }
    }
}
