package bjp.utility;


import java.util.Random;

import bjp.controller.CityMapController;
import bjp.utility.StaticTransportConfig;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public static int playerX;
    public static int playerY;
    public static boolean foundTransport = false;
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
        foundTransport = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(playerX, playerY)) {
            System.out.println("Player is at a LUAS stop.");
            System.out.println("Want to travel in LUAS?");
            foundTransport = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus1 stop.");
            foundTransport = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus2 stop.");
            foundTransport = true;
        }
    
        if (!foundTransport) {
            System.out.println("Player is not at any transport stop.");
        }
    }

    public static void checkTransportOptionsAndMove(GridPane cityMapGrid) {
        List<Location> keysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
        final Location[] currentStation = {null};
        final Location[] nextStation = {null};
        final Location[] previousStation = {null};
    
        for (int i = 0; i < keysAsList.size(); i++) {
            Location key = keysAsList.get(i);
            if (key.getX() == playerX && key.getY() == playerY) {
                currentStation[0] = key;
                if (i + 1 < keysAsList.size()) nextStation[0] = StaticTransportConfig.LUAS_STOPS.get(key);
                if (i > 0) previousStation[0] = keysAsList.get(i - 1);
                break;
            }
        }
    
        // Access elements via [0] since they are now array elements
        if (nextStation[0] != null) System.out.println("1 : " + nextStation[0].getLocationName());
        if (previousStation[0] != null) System.out.println("2 : " + previousStation[0].getLocationName());
    
        if (currentStation[0] != null) {
            System.out.println("Current Station: " + currentStation[0].getLocationName());
            if (nextStation[0] != null) {
                System.out.println("Press 'N' for Next Station: " + nextStation[0].getLocationName());
            }
            if (previousStation[0] != null) {
                System.out.println("Press 'P' for Previous Station: " + previousStation[0].getLocationName());
            }
        }
    
        cityMapGrid.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.N && nextStation[0] != null) {
                movePlayerToStation(cityMapGrid, nextStation[0]);
            } else if (keyEvent.getCode() == KeyCode.P && previousStation[0] != null) {
                movePlayerToStation(cityMapGrid, previousStation[0]);
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.out.println("Travel cancelled.");
            } else {
                System.out.println("Invalid choice.");
            }
            keyEvent.consume();
        });
        cityMapGrid.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.N && nextStation[0] != null) {
                movePlayerToStation(cityMapGrid, nextStation[0]);
            } else if (keyEvent.getCode() == KeyCode.P && previousStation[0] != null) {
                movePlayerToStation(cityMapGrid, previousStation[0]);
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.out.println("Travel cancelled.");
            } else {
                System.out.println("Invalid choice.");
            }
            keyEvent.consume();
        });
        CityMapController.mainEventHandler(cityMapGrid);
    }
    

    public static void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        playerX = station.getX();
        playerY = station.getY();
        cityMapGrid.add(playerView, playerX, playerY);
    }
    
}