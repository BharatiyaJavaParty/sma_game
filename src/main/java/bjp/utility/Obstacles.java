package bjp.utility;

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

public class Obstacles {2
    private static final Set<Location> Tree_SET = new HashSet<>();
    private static final Image gem = new Image(Gem.class.getResourceAsStream("/img/tree.png"));
    public static ImageView imageView;
    static {
        Tree_SET.add(new Location("Tree", 1, 1));
    }
    public static void placeTrees(StackPane cityMainStack, GridPane cityMapGrid) {
        imageView = new ImageView(gem);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setSmooth(true);
        for (Location tree : Tree_SET) {
            cityMapGrid.add(imageView, tree.getX(), tree.getY());
        }
    }
}
