package bjp.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import bjp.controller.CityMapController;

public class Obstacles {
    public static Set<Location> TREE_SET = new HashSet<>();
    public static Set<Location> BUSH_SET = new HashSet<>();
    public static Set<Location> HOUSE_SET = new HashSet<>();
    public static Set<Location> BUILDING_SET = new HashSet<>();
    public static Set<Location> ALL_OBSTACLES = new HashSet<>();

    private static ArrayList<Image> houseList = new ArrayList<>();
    private static ArrayList<Image> buildingList = new ArrayList<>();
    private static ArrayList<Image> treeList = new ArrayList<>();
    private static ArrayList<Image> bushList = new ArrayList<>();

    //make 2 grid tree set



    public static ImageView imageView;

    static {
        for (int i = 1; i < 8; i++){
            houseList.add(new Image(Obstacles.class.getResourceAsStream("/img/house" + i + ".png")));
        }

        for (int i = 1; i < 7; i++){
            buildingList.add(new Image(Obstacles.class.getResourceAsStream("/img/building" + i + ".png")));
        }
        for (int i = 5; i < 9; i++){
            treeList.add(new Image(Obstacles.class.getResourceAsStream("/img/tree" + i + ".png")));
        }

        for (int i = 1; i < 12; i++){
            bushList.add(new Image(Obstacles.class.getResourceAsStream("/img/bush" + i + ".png")));
        }

        // Adding elements to Tree_SET

        for (int i = 0; i < CityMapController.COLS; i++){
            TREE_SET.add(new Location("Tree", i, 0));
            TREE_SET.add(new Location("Tree", i, CityMapController.ROWS - 1));
        }
        for (int i = 0; i < CityMapController.ROWS; i++){
            TREE_SET.add(new Location("Tree", 0, i));
            TREE_SET.add(new Location("Tree", CityMapController.COLS - 1, i));
        }

        // Adding element to HOUSE_SET
        for (int i = 27; i < 43; i+=3){
            HOUSE_SET.add(new Location("House1", i, 4));

            BUSH_SET.add(new Location("House1", i-1, 3));
            BUSH_SET.add(new Location("House1", i, 3));
            BUSH_SET.add(new Location("House1", i+1, 3));
            BUSH_SET.add(new Location("House1", i-1, 4));
            BUSH_SET.add(new Location("House1", i-1, 5));
            if (i == 42){
                BUSH_SET.add(new Location("House1", i+2, 4));
                BUSH_SET.add(new Location("House1", i+2, 5));
                BUSH_SET.add(new Location("House1", i+2, 3));
            }
        }
        for (int i = 35; i < 45; i+=3){
            HOUSE_SET.add(new Location("House1", i, 9));

            BUSH_SET.add(new Location("House1", i-1, 8));
            BUSH_SET.add(new Location("House1", i, 8));
            BUSH_SET.add(new Location("House1", i+1, 8));
            BUSH_SET.add(new Location("House1", i-1, 9));
            BUSH_SET.add(new Location("House1", i-1, 10));
            if (i == 44){
                BUSH_SET.add(new Location("House1", i+2, 9));
                BUSH_SET.add(new Location("House1", i+2, 10));
                BUSH_SET.add(new Location("House1", i+2, 8));
            }
        }

        for (int i = 35; i < 42; i+=3){
            HOUSE_SET.add(new Location("House1", i, 26));


            BUSH_SET.add(new Location("House1", i-1, 25));
            BUSH_SET.add(new Location("House1", i, 25));
            BUSH_SET.add(new Location("House1", i+1, 25));
            BUSH_SET.add(new Location("House1", i-1, 26));
            BUSH_SET.add(new Location("House1", i-1, 27));
            if (i == 41){
                BUSH_SET.add(new Location("House1", i+2, 26));
                BUSH_SET.add(new Location("House1", i+2, 27));
                BUSH_SET.add(new Location("House1", i+2, 25));
            }
        }
        for (int i = 3; i < 13; i+=3){
            HOUSE_SET.add(new Location("House1", i, 9));
            
            BUSH_SET.add(new Location("House1", i-1, 8));
            BUSH_SET.add(new Location("House1", i, 8));
            BUSH_SET.add(new Location("House1", i+1, 8));
            BUSH_SET.add(new Location("House1", i-1, 9));
            BUSH_SET.add(new Location("House1", i-1, 10));
            if (i == 12){
                BUSH_SET.add(new Location("House1", i+2, 9));
                BUSH_SET.add(new Location("House1", i+2, 10));
                BUSH_SET.add(new Location("House1", i+2, 8));
            }
        }

        for (int i = 4; i < 11; i+=3){
            HOUSE_SET.add(new Location("House1", i, 23));

            BUSH_SET.add(new Location("House1", i-1, 22));
            BUSH_SET.add(new Location("House1", i, 22));
            BUSH_SET.add(new Location("House1", i+1, 22));
            BUSH_SET.add(new Location("House1", i-1, 23));
            BUSH_SET.add(new Location("House1", i-1, 24));
            if (i == 22){
                BUSH_SET.add(new Location("House1", i+2, 23));
                BUSH_SET.add(new Location("House1", i+2, 24));
                BUSH_SET.add(new Location("House1", i+2, 22));
            }
        }
        HOUSE_SET.add(new Location("House1", 35, 24));
        HOUSE_SET.add(new Location("House1", 43, 24));
        HOUSE_SET.add(new Location("House1", 6, 25));
        HOUSE_SET.add(new Location("House1", 9, 25));


        // Adding element to BUILDING_SET
        BUILDING_SET.add(new Location("House1", 37, 18));
        BUILDING_SET.add(new Location("House1", 39, 17));
        BUILDING_SET.add(new Location("House1", 41, 18));
        BUILDING_SET.add(new Location("House1", 43, 18));
        BUILDING_SET.add(new Location("House1", 45, 17));
        BUILDING_SET.add(new Location("House1", 7, 17));
        BUILDING_SET.add(new Location("House1", 9, 18));
        BUILDING_SET.add(new Location("House1", 11, 17));
        BUILDING_SET.add(new Location("House1", 13 , 17));
        


        // Combining both sets into the ALL_OBSTACLES set
        ALL_OBSTACLES.addAll(TREE_SET);
        ALL_OBSTACLES.addAll(HOUSE_SET);
        ALL_OBSTACLES.addAll(BUILDING_SET);
    }

    public static void placeTrees(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location tree : TREE_SET) {
            int index = random.nextInt(treeList.size());
            Image treeImg = treeList.get(index);
            ImageView imageView = new ImageView(treeImg);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, tree.getX(), tree.getY());
        }
    }

    public static void placeBushes(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location bush : BUSH_SET) {
            int index = random.nextInt(bushList.size());
            Image bushImg = bushList.get(index);
            ImageView imageView = new ImageView(bushImg);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, bush.getX(), bush.getY());
        }
    }
    

    public static void placeHouses(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location house : HOUSE_SET) {
            Image houseImg = houseList.get(random.nextInt(houseList.size()));
            ImageView imageView = new ImageView(houseImg);
            imageView.setFitWidth(2 * CityMapController.WIDTH);
            imageView.setFitHeight(2 * CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, house.getX(), house.getY(), 2, 2);
        }
    }
    
    public static void placeBuildings(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location building : BUILDING_SET) {
            Image buildingImg = buildingList.get(random.nextInt(buildingList.size()));  // Get random building image
            ImageView imageView = new ImageView(buildingImg);
            imageView.setFitWidth(2 * CityMapController.WIDTH);
            imageView.setFitHeight(3 * CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, building.getX(), building.getY(), 2, 3);
        }
    }
    

    public static boolean checkObstacles(int newX, int newY) {
        for (Location tree :TREE_SET) {
            if (tree.getX() == newX && tree.getY() == newY) {
                return true; // Tree is an obstacle at the new position
            }
        }

        for (Location bush :BUSH_SET) {
            if (bush.getX() == newX && bush.getY() == newY) {
                return true; // Tree is an obstacle at the new position
            }
        }

        for (Location house : HOUSE_SET) {
            if ((house.getX() == newX || house.getX() == newX - 1)
                    && (house.getY() == newY || house.getY() == newY - 1)) {
                return true; // House is an obstacle at the new position
            }
        }

        for (Location building : BUILDING_SET) {
            if ((building.getX() == newX || building.getX() == newX - 1)
                    && (building.getY() <= newY && building.getY() + 2 >= newY)) { // Checks if newY falls within the vertical span of the building
                return true; // Building is an obstacle at the new position
            }
        }

        return false; // No obstacles found, movement is possible
    }
}
