package bjp.utility;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import bjp.controller.CityMapController;

public class Obstacles {
    public static Set<Location> Tree_SET = new HashSet<>();
    public static Set<Location> HOUSE_SET = new HashSet<>();
    public static Set<Location> ALL_OBSTACLES = new HashSet<>();
    private static final Image treeImg = new Image(Obstacles.class.getResourceAsStream("/img/tree.png"));
    private static final Image houseImg = new Image(Obstacles.class.getResourceAsStream("/img/house1.png"));
    public static ImageView imageView;

    static {
        // Adding elements to Tree_SET

        for (int i = 0; i < CityMapController.COLS; i++){
            Tree_SET.add(new Location("Tree", i, 0));
        }

        // Adding element to HOUSE_SET
        HOUSE_SET.add(new Location("House1", 16, 10));

        // Combining both sets into the ALL_OBSTACLES set
        ALL_OBSTACLES.addAll(Tree_SET);
        ALL_OBSTACLES.addAll(HOUSE_SET);
    }

    public static void placeTrees(GridPane cityMapGrid) {
        for (Location tree : Tree_SET) {
            imageView = new ImageView(treeImg);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, tree.getX(), tree.getY());
        }
    }

    public static void placeHouses(GridPane cityMapGrid) {
        for (Location house : HOUSE_SET) {
            imageView = new ImageView(houseImg);
            imageView.setFitWidth(2 * CityMapController.WIDTH);
            imageView.setFitHeight(2 * CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, house.getX(), house.getY(), 2, 2);
        }
    }

    public static boolean checkObstacles(int newX, int newY) {
        for (Location tree : Tree_SET) {
            if (tree.getX() == newX && tree.getY() == newY) {
                return true; // Tree is an obstacle at the new position
            }
        }

        for (Location house : HOUSE_SET) {
            if ((house.getX() == newX || house.getX() == newX - 1)
                    && (house.getY() == newY || house.getY() == newY - 1)) {
                return true; // House is an obstacle at the new position
            }
        }

        return false; // No obstacles found, movement is possible
    }
}
