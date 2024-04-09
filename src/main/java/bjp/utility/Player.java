package bjp.utility;


import java.util.Random;

import bjp.controller.CityMapController;
import bjp.utility.StaticTransportConfig;

import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    private static int playerX;
    private static int playerY;
    private static ImageView playerView;
    private static final Image gem = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));

    // Initialize the player on the grid
    public static void placePlayer(GridPane cityMapGrid) {
        Random random = new Random();
        playerX = random.nextInt(CityMapController.COLS);
        playerY = random.nextInt(CityMapController.ROWS);

        playerView = new ImageView(gem);
        playerView.setFitWidth(20);
        playerView.setFitHeight(20);
        playerView.setSmooth(true);

        cityMapGrid.add(playerView, playerX, playerY);
    }

    public static void movePlayer(GridPane cityMapGrid, int deltaX, int deltaY) {
        cityMapGrid.getChildren().remove(playerView);
        playerX += deltaX;
        playerY += deltaY;

        playerX = Math.min(Math.max(playerX, 0), CityMapController.COLS - 1);
        playerY = Math.min(Math.max(playerY, 0), CityMapController.ROWS - 1);
        cityMapGrid.add(playerView, playerX, playerY);
    }

    public static void checkTransportOptions(GridPane cityMapGrid) {
        boolean foundTransport = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(playerX, playerY)) {
            System.out.println("Player is at a LUAS stop.");
            foundTransport = true;
        }
    
        else if (StaticTransportConfig.isPlayerAtBus1Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus1 stop.");
            foundTransport = true;
        }
    
        else if (StaticTransportConfig.isPlayerAtBus2Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus2 stop.");
            foundTransport = true;
        }
    
        if (!foundTransport) {
            System.out.println("Player is not at any transport stop.");
        }
    }
    
}