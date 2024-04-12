package bjp.utility;


import java.util.Random;

import bjp.controller.CityMapController;
import bjp.controller.PopupController;
import bjp.utility.StaticTransportConfig;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    private static String playerName;
    private static Location playerLocation;
    private static int gemCount;
    public static boolean foundTransport = false;
    public static boolean atLuas = false;
    public static boolean atBus1 = false;
    public static boolean atBus2 = false;
    private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));

    public static Location getPlayerLocation()
    {
        return playerLocation;
    }

    public static void setPlayerLocation(Location playerNewLocation)
    {
        playerLocation = playerNewLocation;
    }

    public static String getPlayerName()
    {
        return playerName;
    }

    public static void setPlayerName(String name)
    {
        playerName = name;
    }
    // Initialize the player on the grid
    public static void placePlayer(GridPane cityMapGrid) {
        Random random = new Random();
        // playerLocation.setX(random.nextInt(CityMapController.COLS));
        // playerLocation.setY(random.nextInt(CityMapController.ROWS));

        setPlayerLocation(new Location(null, random.nextInt(CityMapController.COLS), random.nextInt(CityMapController.ROWS)));

        playerView = new ImageView(playerImage);
        playerView.setFitWidth(20);
        playerView.setFitHeight(20);
        playerView.setSmooth(true);

        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    }

    public static void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY) {
        cityMapGrid.getChildren().remove(playerView);
        playerLocation.setX(playerLocation.getX()+deltaX);
        playerLocation.setY(playerLocation.getY()+deltaY);
        // playerY += deltaY;

        playerLocation.setX(Math.min(Math.max(playerLocation.getX(), 0), CityMapController.COLS ));
        playerLocation.setY(Math.min(Math.max(playerLocation.getY(), 0), CityMapController.COLS ));
        // playerY = Math.min(Math.max(playerY, 0), CityMapController.ROWS );
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
        checkGemCollected(cityMainStack, cityMapGrid);
    }

    public static void checkGemCollected(StackPane cityMainStack, GridPane cityMapGrid)
    {
        if (Gem.getGemLocation().getX() == playerLocation.getX() && Gem.getGemLocation().getY() == playerLocation.getY())
        {
            Gem.placeGem(cityMainStack, cityMapGrid);
            gemCount = gemCount+1;
            PopupController.showPopupMessage(cityMainStack, "You have collected " + String.valueOf(gemCount) + " Gems!");
        }
    }

    public static void checkTransportOptions(StackPane cityMainStack, GridPane cityMapGrid) {
        foundTransport = false;
        atLuas = false;
        atBus1 = false;
        atBus2 = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(playerLocation.getX(), playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, playerName + " is at a Luas stop. Want to travel in LUAS?");
            foundTransport = true;
            atLuas = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(playerLocation.getX(), playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, playerName + " is at a Bus1 stop.");
            foundTransport = true;
            atBus1 = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(playerLocation.getX(), playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, playerName + " is at a Bus2 stop.");
            foundTransport = true;
            atBus2 = true;
        }
    
        if (!foundTransport) {
            System.out.println("Player is not at any transport stop.");
            PopupController.showPopupMessage(cityMainStack, playerName + " is not at any transport stop.");
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
            if (key.getX() == playerLocation.getX() && key.getY() == playerLocation.getY()) {
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

    public static ArrayList<Location> checkTransportOptionsAndMoveUpdated(StackPane cityMainStack, GridPane cityMapGrid){
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
                if (key.getX() == playerLocation.getX() && key.getY() == playerLocation.getY()) {
                    currentStation = key;
                    if (i + 1 < luasKeysAsList.size()) {
                        nextStation = StaticTransportConfig.LUAS_STOPS.get(key).getValue();
                    }
                    if (i > 0) {
                        previousStation = StaticTransportConfig.LUAS_STOPS.get(key).getKey();
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
            PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + " stop.");
        
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
                if (key.getX() == playerLocation.getX() && key.getY() == playerLocation.getY()) {
                    currentStation = key;
                    if (i + 1 < bus1KeysAsList.size()) {
                        nextStation = StaticTransportConfig.BUS1_STOPS.get(key).getValue();
                    }
                    if (i > 0) {
                        previousStation = StaticTransportConfig.BUS1_STOPS.get(key).getKey();
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
            PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + " stop.");
        
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
                if (key.getX() == playerLocation.getX() && key.getY() == playerLocation.getY()) {
                    currentStation = key;
                    if (i + 1 < bus2KeysAsList.size()) {
                        nextStation = StaticTransportConfig.BUS2_STOPS.get(key).getValue();
                    }
                    if (i > 0) {
                        previousStation = StaticTransportConfig.BUS2_STOPS.get(key).getKey();
                    }
                    break;
                }
            }
        
            // Access elements via [0] since they are now array elements
            if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
            if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
            PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + " stop.");
        
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
        playerLocation.setX(station.getX());
        playerLocation.setY(station.getY());
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    }
    
}