package bjp.utility;

import java.util.ArrayList;

import bjp.controller.CityMapController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Obstacles {
    public static ArrayList<Location> Tree_SET = new ArrayList<>();
    public static ArrayList<Location> HOUSE_SET = new ArrayList<>();
    private static final Image treeImg = new Image(Obstacles.class.getResourceAsStream("/img/tree3.png"));
    private static final Image houseImg = new Image(Obstacles.class.getResourceAsStream("/img/house1.png"));
    public static ImageView imageView;
    
    static{
        Tree_SET.add(new Location("Tree1", 1, 1));
        Tree_SET.add(new Location("Tree2", 2, 2));
        Tree_SET.add(new Location("Tree2", 1, 2));
        Tree_SET.add(new Location("Tree2", 2, 1));
        Tree_SET.add(new Location("Tree2", 0, 0));
        Tree_SET.add(new Location("Tree2", 0, 1));
        Tree_SET.add(new Location("Tree2", 1, 0));
        Tree_SET.add(new Location("Tree2", 17, 9));

        HOUSE_SET.add(new Location("House1", 10, 10));
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

    public static void placeHouses( GridPane cityMapGrid) {
        for (Location house : HOUSE_SET) {
            imageView = new ImageView(houseImg);
            imageView.setFitWidth(2*CityMapController.WIDTH);
            imageView.setFitHeight(2*CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, house.getX(), house.getY(), 2, 2);
        }
    }

    public static boolean checkObstacles(int newX, int newY) {
        for (Location tree : Obstacles.Tree_SET) {
            if (tree.getX() == newX && tree.getY() == newY) {
                return true; // There is an obstacle at the new position
            }
        }
        
        for (Location house : Obstacles.HOUSE_SET) {
            if ((house.getX() == newX || house.getX() == newX - 1 )&& (house.getY() == newY || house.getY() == newY - 1)) {
                return true; // There is an obstacle at the new position
            }
        }
        return false; // No obstacles, movement is possible
    }
}
