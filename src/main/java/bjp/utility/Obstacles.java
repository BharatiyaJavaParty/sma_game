package bjp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class Obstacles {
    public static ArrayList<Location> Tree_SET = new ArrayList<>();
    private static final Image gem = new Image(Gem.class.getResourceAsStream("/img/tree.png"));
    public static ImageView imageView;
    
    static{
        Tree_SET.add(new Location("Tree", 1, 1));
        Tree_SET.add(new Location("Tree1", 2, 2));
    }

    public static void placeTrees( GridPane cityMapGrid) {
        for (Location tree : Tree_SET) {
            imageView = new ImageView(gem);
            imageView.setFitWidth(35);
            imageView.setFitHeight(35);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, tree.getX(), tree.getY());
        }
    }
}
