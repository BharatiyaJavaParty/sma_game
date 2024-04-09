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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    public static int playerX;
    public static int playerY;
    public static boolean foundTransport = false;
    public static boolean atLuas = false;
    public static boolean atBus1 = false;
    public static boolean atBus2 = false;
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

        playerX = Math.min(Math.max(playerX, 0), CityMapController.COLS );
        playerY = Math.min(Math.max(playerY, 0), CityMapController.ROWS );
        cityMapGrid.add(playerView, playerX, playerY);
    }

    public static void checkTransportOptions(GridPane cityMapGrid) {
        foundTransport = false;
        atLuas = false;
        atBus1 = false;
        atBus2 = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(playerX, playerY)) {
            System.out.println("Player is at a LUAS stop.");
            System.out.println("Want to travel in LUAS?");
            foundTransport = true;
            atLuas = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus1 stop.");
            foundTransport = true;
            atBus1 = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(playerX, playerY)) {
            System.out.println("Player is at a Bus2 stop.");
            foundTransport = true;
            atBus2 = true;
        }
    
        if (!foundTransport) {
            System.out.println("Player is not at any transport stop.");
        }
    }

    public static  ArrayList<Location> checkTransportOptionsAndMove(GridPane cityMapGrid) {
        ArrayList<Location> res = new ArrayList<Location>();
        List<Location> keysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
        final Location[] currentStation = {null};
        final Location[] nextStation = {null};
        final Location[] previousStation = {null};
    
        for (int i = 0; i < keysAsList.size(); i++) {
            Location key = keysAsList.get(i);
            if (key.getX() == playerX && key.getY() == playerY) {
                currentStation[0] = key;
                if (i + 1 < keysAsList.size()) nextStation[0] = StaticTransportConfig.LUAS_STOPS.get(key).getValue();
                if (i > 0) previousStation[0] = StaticTransportConfig.LUAS_STOPS.get(key).getKey();
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

        res.add(nextStation[0]);
        res.add(previousStation[0]);
        return res;
    }

    public static ArrayList<Location> checkTransportOptionsAndMoveUpdated(GridPane cityMapGrid){
        ArrayList<Location> res = new ArrayList<Location>();
        List<Location> luasKeysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
        List<Location> bus1KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
        List<Location> bus2KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
        Location currentStation = null;
        Location nextStation = null;
        Location previousStation = null;

        //Only Luas
        if(atLuas==true && (atBus1==false && atBus2==false)){
            for (int i = 0; i < luasKeysAsList.size(); i++) {
                Location key = luasKeysAsList.get(i);
                if (key.getX() == playerX && key.getY() == playerY) {
                    currentStation = key;
                    if (i + 1 < luasKeysAsList.size()) {
                        nextStation = StaticTransportConfig.LUAS_STOPS.get(key);
                    }
                    if (i > 0) {
                        previousStation = luasKeysAsList.get(i - 1);
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
        
            if (currentStation != null) {
                System.out.println("Current Station: " + currentStation.getLocationName());
                if (nextStation != null) {
                    System.out.println("Press 'N' for Next Station: " + nextStation.getLocationName());
                }
                if (previousStation != null) {
                    System.out.println("Press 'P' for Previous Station: " + previousStation.getLocationName());
                }
            }
    
            res.add(nextStation);
            res.add(previousStation);
            return res;
        }
        //Only Bus1
        else if(atBus1==true && (atLuas==false && atBus2==false)){
            for (int i = 0; i < bus1KeysAsList.size(); i++) {
                Location key = bus1KeysAsList.get(i);
                if (key.getX() == playerX && key.getY() == playerY) {
                    currentStation = key;
                    if (i + 1 < bus1KeysAsList.size()) {
                        nextStation = StaticTransportConfig.BUS1_STOPS.get(key);
                    }
                    if (i > 0) {
                        previousStation = bus1KeysAsList.get(i - 1);
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
        
            if (currentStation != null) {
                System.out.println("Current Bus Stop: " + currentStation.getLocationName());
                if (nextStation != null) {
                    System.out.println("Press 'N' for Next Bus Stop: " + nextStation.getLocationName());
                }
                if (previousStation != null) {
                    System.out.println("Press 'P' for Previous Bus Stop: " + previousStation.getLocationName());
                }
            }
    
            res.add(nextStation);
            res.add(previousStation);
            return res;
        }//Onlu BusStop 2
        else if(atBus2==true && (atLuas==false && atBus1==false)){
            for (int i = 0; i < bus2KeysAsList.size(); i++) {
                Location key = bus2KeysAsList.get(i);
                if (key.getX() == playerX && key.getY() == playerY) {
                    currentStation = key;
                    if (i + 1 < bus2KeysAsList.size()) {
                        nextStation = StaticTransportConfig.BUS2_STOPS.get(key);
                    }
                    if (i > 0) {
                        previousStation = bus2KeysAsList.get(i - 1);
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
        
            if (currentStation != null) {
                System.out.println("Current Bus Stop: " + currentStation.getLocationName());
                if (nextStation != null) {
                    System.out.println("Press 'N' for Next Bus Stop: " + nextStation.getLocationName());
                }
                if (previousStation != null) {
                    System.out.println("Press 'P' for Previous Bus Stop: " + previousStation.getLocationName());
                }
            }
    
            res.add(nextStation);
            res.add(previousStation);
            return res;
        }
        return null;
    }
    

    public static void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        playerX = station.getX();
        playerY = station.getY();
        cityMapGrid.add(playerView, playerX, playerY);
    }
    
}