package bjp.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import bjp.constants.Level1;
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

        for (int i = 0; i < CityMapController.COLS; i++) {
            TREE_SET.add(new Location("Tree", i, 0));
            TREE_SET.add(new Location("Tree", i, CityMapController.ROWS - 1));
        }
        for (int i = 0; i < CityMapController.ROWS; i++) {
            TREE_SET.add(new Location("Tree", 0, i));
            TREE_SET.add(new Location("Tree", CityMapController.COLS - 1, i));
        }

        for (int[] coordinates : Level1.trees) {
            if (coordinates.length == 2) {
                TREE_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }

        for (int[] coordinates : Level1.doubleTrees) {
            if (coordinates.length == 2) {
                DOUBLE_TREE_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }

        for (int[] coordinates : Level1.homeTrees) {
            if (coordinates.length == 2) {
                HOME_TREE_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }

        for (int[] coordinates : Level1.houses) {
            if (coordinates.length == 2) {
                HOUSE_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }
        for (int[] coordinates : Level1.bushes) {
            if (coordinates.length == 2) {
                BUSH_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }
        for (int[] coordinates : Level1.buildings) {
            if (coordinates.length == 2) {
                BUILDING_SET.add(new Location("Tree", coordinates[0], coordinates[1]));
            }
        }
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
            Image buildingImg = buildingList.get(random.nextInt(buildingList.size()));
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
                return true;
            }
        }

        for (Location bush : BUSH_SET) {
            if (bush.getX() == newX && bush.getY() == newY) {
                System.out.println("bush");
                return true;
            }
        }

        for (Location house : HOUSE_SET) {
            if ((house.getX() == newX || house.getX() == newX - 1)
                    && (house.getY() == newY || house.getY() == newY - 1)) {
                System.out.println("house");
                return true;
            }
        }

        for (Location house : HOME_TREE_SET) {
            if (house.getX() == newX && house.getY() == newY) {
                System.out.println("house tree");
                return true;
            }
        }

        for (Location tree : DOUBLE_TREE_SET) {
            if ((tree.getX() == newX)
                    && (tree.getY() <= newY && tree.getY() + 1 >= newY)) {
                System.out.println("house tree");
                return true;
            }
        }

        for (Location building : BUILDING_SET) {
            if ((building.getX() == newX || building.getX() == newX - 1)
                    && (building.getY() <= newY && building.getY() + 2 >= newY)) {
                System.out.println("building");
                return true;
            }

            if (newX >= Entrance.getX() && newX < Entrance.getX() + 3 && newY >= Entrance.getY() && newY < Entrance.getY() + 3){
                return true;
            }
        }

        return false;
    }
}
