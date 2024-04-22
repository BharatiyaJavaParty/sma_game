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
    public static Set<Location> DOUBLE_TREE_SET = new HashSet<>();
    public static Set<Location> HOME_TREE_SET = new HashSet<>();
    public static Set<Location> BUSH_SET = new HashSet<>();
    public static Set<Location> HOUSE_SET = new HashSet<>();
    public static Set<Location> BUILDING_SET = new HashSet<>();
    public static Set<Location> ALL_OBSTACLES = new HashSet<>();
    public static final Image MAIN_ENTRANCE = new Image(Obstacles.class.getResourceAsStream("/img/mainhouse.png"));
    public static Location Entrance = new Location("Entrance", 19, 3);

    private static ArrayList<Image> houseList = new ArrayList<>();
    private static ArrayList<Image> houseTreeList = new ArrayList<>();
    private static ArrayList<Image> buildingList = new ArrayList<>();
    private static ArrayList<Image> treeList = new ArrayList<>();
    private static ArrayList<Image> doubleTreeList = new ArrayList<>();
    private static ArrayList<Image> bushList = new ArrayList<>();

    // make 2 grid tree set

    public static ImageView imageView;

    static {
        for (int i = 1; i < 8; i++) {
            houseList.add(new Image(Obstacles.class.getResourceAsStream("/img/house" + i + ".png")));
        }

        for (int i = 1; i < 7; i++) {
            buildingList.add(new Image(Obstacles.class.getResourceAsStream("/img/building" + i + ".png")));
        }
        for (int i = 5; i < 9; i++) {
            treeList.add(new Image(Obstacles.class.getResourceAsStream("/img/tree" + i + ".png")));
        }

        for (int i = 1; i < 12; i++) {
            bushList.add(new Image(Obstacles.class.getResourceAsStream("/img/bush" + i + ".png")));
        }

        for (int i = 1; i < 8; i++) {
            houseTreeList.add(new Image(Obstacles.class.getResourceAsStream("/img/housetree" + i + ".png")));
        }

        for (int i = 1; i < 7; i++) {
            doubleTreeList.add(new Image(Obstacles.class.getResourceAsStream("/img/doubletree" + i + ".png")));
        }

        // Adding elements to Tree_SET

        for (int i = 0; i < CityMapController.COLS; i++) {
            TREE_SET.add(new Location("Tree", i, 0));
            TREE_SET.add(new Location("Tree", i, CityMapController.ROWS - 1));
        }
        for (int i = 0; i < CityMapController.ROWS; i++) {
            TREE_SET.add(new Location("Tree", 0, i));
            TREE_SET.add(new Location("Tree", CityMapController.COLS - 1, i));
        }

        // Adding element to HOUSE_SET
        for (int i = 27; i < 43; i += 3) {
            HOUSE_SET.add(new Location("House1", i, 4));

            HOME_TREE_SET.add(new Location("House1", i - 1, 3));
            HOME_TREE_SET.add(new Location("House1", i, 3));
            HOME_TREE_SET.add(new Location("House1", i + 1, 3));
            HOME_TREE_SET.add(new Location("House1", i - 1, 4));
            BUSH_SET.add(new Location("House1", i - 1, 5));
            if (i == 42) {
                HOME_TREE_SET.add(new Location("House1", i + 2, 4));
                BUSH_SET.add(new Location("House1", i + 2, 5));
                HOME_TREE_SET.add(new Location("House1", i + 2, 3));
            }
        }
        for (int i = 35; i < 45; i += 3) {
            HOUSE_SET.add(new Location("House1", i, 9));

            HOME_TREE_SET.add(new Location("House1", i - 1, 8));
            HOME_TREE_SET.add(new Location("House1", i, 8));
            HOME_TREE_SET.add(new Location("House1", i + 1, 8));
            HOME_TREE_SET.add(new Location("House1", i - 1, 9));
            BUSH_SET.add(new Location("House1", i - 1, 10));
            if (i == 44) {
                HOME_TREE_SET.add(new Location("House1", i + 2, 9));
                BUSH_SET.add(new Location("House1", i + 2, 10));
                HOME_TREE_SET.add(new Location("House1", i + 2, 8));
            }
        }

        for (int i = 35; i < 42; i += 3) {
            HOUSE_SET.add(new Location("House1", i, 26));

            HOME_TREE_SET.add(new Location("House1", i - 1, 25));
            HOME_TREE_SET.add(new Location("House1", i, 25));
            HOME_TREE_SET.add(new Location("House1", i + 1, 25));
            HOME_TREE_SET.add(new Location("House1", i - 1, 26));
            BUSH_SET.add(new Location("House1", i - 1, 27));
            if (i == 41) {
                HOME_TREE_SET.add(new Location("House1", i + 2, 26));
                BUSH_SET.add(new Location("House1", i + 2, 27));
                HOME_TREE_SET.add(new Location("House1", i + 2, 25));
            }
        }
        for (int i = 3; i < 13; i += 3) {
            HOUSE_SET.add(new Location("House1", i, 9));

            HOME_TREE_SET.add(new Location("House1", i - 1, 8));
            HOME_TREE_SET.add(new Location("House1", i, 8));
            HOME_TREE_SET.add(new Location("House1", i + 1, 8));
            HOME_TREE_SET.add(new Location("House1", i - 1, 9));
            BUSH_SET.add(new Location("House1", i - 1, 10));
            if (i == 12) {
                HOME_TREE_SET.add(new Location("House1", i + 2, 9));
                BUSH_SET.add(new Location("House1", i + 2, 10));
                HOME_TREE_SET.add(new Location("House1", i + 2, 8));
            }
        }

        for (int i = 4; i < 11; i += 3) {
            HOUSE_SET.add(new Location("House1", i, 23));

            HOME_TREE_SET.add(new Location("House1", i - 1, 22));
            HOME_TREE_SET.add(new Location("House1", i, 22));
            HOME_TREE_SET.add(new Location("House1", i + 1, 22));
            HOME_TREE_SET.add(new Location("House1", i - 1, 23));
            BUSH_SET.add(new Location("House1", i - 1, 24));
            if (i == 22) {
                HOME_TREE_SET.add(new Location("House1", i + 2, 23));
                BUSH_SET.add(new Location("House1", i + 2, 24));
                HOME_TREE_SET.add(new Location("House1", i + 2, 22));
            }
        }
        HOUSE_SET.add(new Location("House1", 35, 23));
        HOUSE_SET.add(new Location("House1", 42, 23));
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
        BUILDING_SET.add(new Location("House1", 13, 17));

        // Forest Top Right
        TREE_SET.add(new Location("Tree", 51, 2));
        TREE_SET.add(new Location("Tree", 51, 3));
        TREE_SET.add(new Location("Tree", 51, 4));
        TREE_SET.add(new Location("Tree", 52, 2));
        TREE_SET.add(new Location("Tree", 52, 3));
        TREE_SET.add(new Location("Tree", 52, 4));
        TREE_SET.add(new Location("Tree", 53, 3));
        TREE_SET.add(new Location("Tree", 55, 3));
        TREE_SET.add(new Location("Tree", 54, 4));
        TREE_SET.add(new Location("Tree", 55, 2));
        TREE_SET.add(new Location("Tree", 54, 3));
        TREE_SET.add(new Location("Tree", 54, 2));
        TREE_SET.add(new Location("Tree", 55, 2));
        TREE_SET.add(new Location("Tree", 54, 4));

        DOUBLE_TREE_SET.add(new Location("Tree", 51, 3));
        DOUBLE_TREE_SET.add(new Location("Tree", 52, 3));
        DOUBLE_TREE_SET.add(new Location("Tree", 53, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 52, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 53, 3));
        DOUBLE_TREE_SET.add(new Location("Tree", 53, 4));
        DOUBLE_TREE_SET.add(new Location("Tree", 54, 3));
        DOUBLE_TREE_SET.add(new Location("Tree", 54, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 55, 4));
        DOUBLE_TREE_SET.add(new Location("Tree", 55, 6));

        // Forest Top Left
        TREE_SET.add(new Location("Tree", 2, 2));
        TREE_SET.add(new Location("Tree", 1, 2));
        TREE_SET.add(new Location("Tree", 4, 4));
        TREE_SET.add(new Location("Tree", 2, 2));
        TREE_SET.add(new Location("Tree", 1, 5));
        TREE_SET.add(new Location("Tree", 1, 4));

        DOUBLE_TREE_SET.add(new Location("Tree", 2, 3));
        DOUBLE_TREE_SET.add(new Location("Tree", 3, 4));
        DOUBLE_TREE_SET.add(new Location("Tree", 1, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 2, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 1, 2));
        DOUBLE_TREE_SET.add(new Location("Tree", 2, 1));

        // Forest Bottom Right
        TREE_SET.add(new Location("Tree", 47, 23));
        TREE_SET.add(new Location("Tree", 47, 24));
        TREE_SET.add(new Location("Tree", 47, 25));
        TREE_SET.add(new Location("Tree", 47, 26));
        TREE_SET.add(new Location("Tree", 48, 23));
        TREE_SET.add(new Location("Tree", 48, 24));
        TREE_SET.add(new Location("Tree", 48, 25));
        TREE_SET.add(new Location("Tree", 48, 27));
        TREE_SET.add(new Location("Tree", 49, 27));
        TREE_SET.add(new Location("Tree", 49, 24));
        TREE_SET.add(new Location("Tree", 49, 24));
        TREE_SET.add(new Location("Tree", 49, 25));
        TREE_SET.add(new Location("Tree", 49, 26));
        TREE_SET.add(new Location("Tree", 50, 27));
        TREE_SET.add(new Location("Tree", 50, 23));
        TREE_SET.add(new Location("Tree", 51, 23));

        DOUBLE_TREE_SET.add(new Location("Tree", 46, 25));
        DOUBLE_TREE_SET.add(new Location("Tree", 47, 25));
        DOUBLE_TREE_SET.add(new Location("Tree", 46, 24));
        DOUBLE_TREE_SET.add(new Location("Tree", 48, 25));
        DOUBLE_TREE_SET.add(new Location("Tree", 48, 26));
        DOUBLE_TREE_SET.add(new Location("Tree", 49, 26));
        DOUBLE_TREE_SET.add(new Location("Tree", 50, 24));
        DOUBLE_TREE_SET.add(new Location("Tree", 49, 26));

        // Forest Bottom Left
        TREE_SET.add(new Location("Tree", 3, 29));
        TREE_SET.add(new Location("Tree", 1, 27));
        TREE_SET.add(new Location("Tree", 2, 28));
        TREE_SET.add(new Location("Tree", 3, 27));
        TREE_SET.add(new Location("Tree", 3, 28));
        TREE_SET.add(new Location("Tree", 4, 29));
        TREE_SET.add(new Location("Tree", 2, 29));
        TREE_SET.add(new Location("Tree", 2, 30));

        DOUBLE_TREE_SET.add(new Location("Tree", 4, 29));
        DOUBLE_TREE_SET.add(new Location("Tree", 1, 28));
        DOUBLE_TREE_SET.add(new Location("Tree", 1, 29));
        DOUBLE_TREE_SET.add(new Location("Tree", 3, 29));
    
        // Forest
        TREE_SET.add(new Location("Tree", 50, 11));
        TREE_SET.add(new Location("Tree", 52, 11));
        TREE_SET.add(new Location("Tree", 51, 12));
        TREE_SET.add(new Location("Tree", 53, 12));
        TREE_SET.add(new Location("Tree", 53, 13));
        TREE_SET.add(new Location("Tree", 50, 14));
        TREE_SET.add(new Location("Tree", 52, 14));
        TREE_SET.add(new Location("Tree", 49, 15));
        TREE_SET.add(new Location("Tree", 50, 15));
        TREE_SET.add(new Location("Tree", 51, 15));
        TREE_SET.add(new Location("Tree", 52, 15));
        TREE_SET.add(new Location("Tree", 54, 15));
        TREE_SET.add(new Location("Tree", 49, 16));
        TREE_SET.add(new Location("Tree", 52, 16));
        TREE_SET.add(new Location("Tree", 53, 16));
        TREE_SET.add(new Location("Tree", 52, 17));
        TREE_SET.add(new Location("Tree", 50, 18));
        TREE_SET.add(new Location("Tree", 49, 11));

        DOUBLE_TREE_SET.add(new Location("Tree", 49, 11));
        DOUBLE_TREE_SET.add(new Location("Tree", 51, 10));
        DOUBLE_TREE_SET.add(new Location("Tree", 50, 12));
        DOUBLE_TREE_SET.add(new Location("Tree", 50, 16));
        DOUBLE_TREE_SET.add(new Location("Tree", 52, 12));
        DOUBLE_TREE_SET.add(new Location("Tree", 49, 13));
        DOUBLE_TREE_SET.add(new Location("Tree", 49, 17));
        DOUBLE_TREE_SET.add(new Location("Tree", 52, 18));
        DOUBLE_TREE_SET.add(new Location("Tree", 53, 14));
        DOUBLE_TREE_SET.add(new Location("Tree", 53, 17));
        DOUBLE_TREE_SET.add(new Location("Tree", 54, 13));
        DOUBLE_TREE_SET.add(new Location("Tree", 54, 16));
        DOUBLE_TREE_SET.add(new Location("Tree", 51, 13));
        DOUBLE_TREE_SET.add(new Location("Tree", 51, 16));

        TREE_SET.add(new Location("Tree", 49, 11));
        TREE_SET.add(new Location("Tree", 51, 10));
        TREE_SET.add(new Location("Tree", 50, 12));
        TREE_SET.add(new Location("Tree", 50, 16));
        TREE_SET.add(new Location("Tree", 52, 12));
        TREE_SET.add(new Location("Tree", 49, 13));
        TREE_SET.add(new Location("Tree", 49, 17));
        TREE_SET.add(new Location("Tree", 52, 18));
        TREE_SET.add(new Location("Tree", 53, 14));
        TREE_SET.add(new Location("Tree", 53, 17));
        TREE_SET.add(new Location("Tree", 54, 13));
        TREE_SET.add(new Location("Tree", 54, 16));
        TREE_SET.add(new Location("Tree", 51, 13));
        TREE_SET.add(new Location("Tree", 51, 16));

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

    public static void placeHomeTrees(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location tree : HOME_TREE_SET) {
            int index = random.nextInt(houseTreeList.size());
            Image treeImg = houseTreeList.get(index);
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
        imageView = new ImageView(MAIN_ENTRANCE);
        imageView.setFitWidth(3*CityMapController.WIDTH);
        imageView.setFitHeight(3*CityMapController.HEIGHT);
        imageView.setSmooth(true);
        cityMapGrid.add(imageView, Entrance.getX(), Entrance.getY(), 3, 3);
    }

    public static void placeDoubleGTree(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location tree : DOUBLE_TREE_SET) {
            Image treeImg = doubleTreeList.get(random.nextInt(doubleTreeList.size()));
            ImageView imageView = new ImageView(treeImg);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(2 * CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, tree.getX(), tree.getY(), 1, 3);
        }
    }

    public static void placeBuildings(GridPane cityMapGrid) {
        Random random = new Random();
        for (Location building : BUILDING_SET) {
            Image buildingImg = buildingList.get(random.nextInt(buildingList.size())); // Get random building image
            ImageView imageView = new ImageView(buildingImg);
            imageView.setFitWidth(2 * CityMapController.WIDTH);
            imageView.setFitHeight(3 * CityMapController.HEIGHT);
            imageView.setSmooth(true);
            cityMapGrid.add(imageView, building.getX(), building.getY(), 2, 3);
        }
    }

    public static boolean checkObstacles(int newX, int newY) {
        for (Location tree : TREE_SET) {
            if (tree.getX() == newX && tree.getY() == newY) {
                System.out.println("tree");
                return true; // Tree is an obstacle at the new position
            }
        }

        for (Location bush : BUSH_SET) {
            if (bush.getX() == newX && bush.getY() == newY) {
                System.out.println("bush");
                return true; // Tree is an obstacle at the new position
            }
        }

        for (Location house : HOUSE_SET) {
            if ((house.getX() == newX || house.getX() == newX - 1)
                    && (house.getY() == newY || house.getY() == newY - 1)) {
                System.out.println("house");
                return true; // House is an obstacle at the new position
            }
        }

        for (Location house : HOME_TREE_SET) {
            if (house.getX() == newX && house.getY() == newY) {
                System.out.println("house tree");
                return true; // House is an obstacle at the new position
            }
        }

        for (Location tree : DOUBLE_TREE_SET) {
            if ((tree.getX() == newX)
                    && (tree.getY() <= newY && tree.getY() + 1 >= newY)) {
                System.out.println("house tree");
                return true; // House is an obstacle at the new position
            }
        }

        for (Location building : BUILDING_SET) {
            if ((building.getX() == newX || building.getX() == newX - 1)
                    && (building.getY() <= newY && building.getY() + 2 >= newY)) {
                System.out.println("building"); // Checks if newY falls within the vertical span of the building
                return true; // Building is an obstacle at the new position
            }
        }

        if((Entrance.getX() <= newX || Entrance.getX() + 2 >= newX)
        && (Entrance.getY() <= newY && Entrance.getY() + 2 >= newY)){
            return true;
        }

        return false; // No obstacles found, movement is possible
    }
}
