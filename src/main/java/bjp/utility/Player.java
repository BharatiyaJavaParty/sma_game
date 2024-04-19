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
 

    private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));

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

        this.playerLocation.setX(Math.min(Math.max(playerLocation.getX(), 0), CityMapController.COLS-1 ));
        this.playerLocation.setY(Math.min(Math.max(playerLocation.getY(), 0), CityMapController.COLS-1 ));
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
        GameEngine.checkGemCollected(cityMainStack, cityMapGrid);
    }

    public void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        this.playerLocation.setX(station.getX());
        this.playerLocation.setY(station.getY());
        cityMapGrid.add(playerView, this.playerLocation.getX(), this.playerLocation.getY());
    }
    
}