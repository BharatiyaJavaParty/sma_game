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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

public class Player {
    //player properties
    //ideally player should have object and not all fucntions should not be called using class name
    private String playerName;
    private Location playerLocation;
    private int gemCount; 
    private double co2Budget;


    public  boolean foundTransport = false;
    public  boolean atLuas = false;
    public  boolean atBus1 = false;
    public  boolean atBus2 = false;
    private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));
    public static HashMap<Location, Pair<Location,Location>> STOPS;
    public static List<Location> KeysAsList;

    //constructor
    // public Player(String name, Location loc, int gemCount, double co2, boolean foundTransport, boolean atLuas, boolean atBus1, boolean atBus2)
    // {
    //     this.playerName = name;
    //     this.playerLocation = loc;
    //     this.gemCount = gemCount;
    //     this.co2Budget = co2;
    //     this. foundTransport = foundTransport;
    //     this.atLuas = atLuas;
    //     this.atBus1 = atBus1;
    //     this.atBus2 = atBus2;
    // }

    //constructor

    public Location getPlayerLocation()
    {
        return this.playerLocation;
    }

    public void setPlayerLocation(Location playerNewLocation)
    {
        this.playerLocation = playerNewLocation;
    }

    public String getPlayerName()
    {
        return this.playerName;
    }

    public void setPlayerName(String name)
    {
        this.playerName = name;
    }
    // Initialize the player on the grid
    public void placePlayer(GridPane cityMapGrid) {
        Random random = new Random();

        this.setPlayerLocation(new Location(null, random.nextInt(CityMapController.COLS), random.nextInt(CityMapController.ROWS)));

        playerView = new ImageView(playerImage);
        playerView.setFitWidth(20);
        playerView.setFitHeight(20);
        playerView.setSmooth(true);

        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    }

    public void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY) {
        cityMapGrid.getChildren().remove(playerView);
        this.playerLocation.setX(playerLocation.getX()+deltaX);
        this.playerLocation.setY(playerLocation.getY()+deltaY);
        // playerY += deltaY;

        this.playerLocation.setX(Math.min(Math.max(playerLocation.getX(), 0), CityMapController.COLS ));
        this.playerLocation.setY(Math.min(Math.max(playerLocation.getY(), 0), CityMapController.COLS ));
        // playerY = Math.min(Math.max(playerY, 0), CityMapController.ROWS );
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
        checkGemCollected(cityMainStack, cityMapGrid);
    }

    public void checkGemCollected(StackPane cityMainStack, GridPane cityMapGrid)
    {
        if (Gem.getGemLocation().getX() == this.playerLocation.getX() && Gem.getGemLocation().getY() == this.playerLocation.getY())
        {
            Gem.placeGem(cityMainStack, cityMapGrid);
            this.gemCount = this.gemCount+1;
            PopupController.showPopupMessage(cityMainStack, "You have collected " + String.valueOf(this.gemCount) + " Gems!");
        }
    }

    public  void checkTransportOptions(StackPane cityMainStack, GridPane cityMapGrid) {
        this.foundTransport = false;
        this.atLuas = false;
        this.atBus1 = false;
        this.atBus2 = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(this.playerLocation.getX(), this.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, this.playerName + " is at a Luas stop. Want to travel in LUAS?");
            this.foundTransport = true;
            this.atLuas = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(this.playerLocation.getX(), this.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, this.playerName + " is at a Bus1 stop.");
            this.foundTransport = true;
            this.atBus1 = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(this.playerLocation.getX(), this.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, this.playerName + " is at a Bus2 stop.");
            this.foundTransport = true;
            this.atBus2 = true;
        }
    
        if (!this.foundTransport) {
            System.out.println("Player is not at any transport stop.");
            PopupController.showPopupMessage(cityMainStack, this.playerName + " is not at any transport stop.");
        }
    }


    public ArrayList<Location> checkTransportOptionsAndMoveUpdated(StackPane cityMainStack, GridPane cityMapGrid){
        ArrayList<Location> res = new ArrayList<Location>();
        List<Location> luasKeysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
        List<Location> bus1KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
        List<Location> bus2KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
        Location currentStation = null;
        Location nextStation = null;
        Location previousStation = null;

        if(this.atLuas==true && (this.atBus1==false && this.atBus2==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
            STOPS = StaticTransportConfig.LUAS_STOPS;
        }
        else if(this.atBus1==true && (this.atLuas==false && this.atBus2==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS1_STOPS;
        }
        else if(this.atBus2==true && (this.atLuas==false && this.atBus1==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS2_STOPS;
        }
        for (int i = 0; i < KeysAsList.size(); i++) {
            Location key = KeysAsList.get(i);
            if (key.getX() == this.playerLocation.getX() && key.getY() == this.playerLocation.getY()) {
                currentStation = key;
                if (i + 1 < luasKeysAsList.size()) {
                    nextStation = STOPS.get(key).getValue();
                }
                if (i > 0) {
                    previousStation = STOPS.get(key).getKey();
                }
                break;
            }
        }
    
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
    

    public void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        this.playerLocation.setX(station.getX());
        this.playerLocation.setY(station.getY());
        cityMapGrid.add(playerView, this.playerLocation.getX(), this.playerLocation.getY());
    }
    
}