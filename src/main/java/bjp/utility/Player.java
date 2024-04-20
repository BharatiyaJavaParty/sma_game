package bjp.utility;


import java.util.Random;

import bjp.constants.AppConstants;
import bjp.controller.CityMapController;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    //player properties
    //ideally player should have object and not all fucntions should not be called using class name

    private String playerName;
    private Location playerLocation;

    //setting time and co2emission
    private int playerTime;
    private int playerCo2Budget;

    public Player(){
        this.playerTime = 0;
        this.playerCo2Budget = AppConstants.CO2_BUDGET;
    }
 

    private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));

    public int getPlayerTime(){
        return this.playerTime;
    }

    public void setPlayerTime(int time){
        System.out.println("Player Time "+this.playerTime);
        System.out.println("======================================================");
        this.playerTime = time;
    }

    public int getPlayerCo2Budget(){
        return this.playerCo2Budget;
    }

    public void setPlayerCo2Budget(int budget){
        System.out.println("======================================================");
        System.out.println("Player CO2 budget "+this.playerCo2Budget);
        this.playerCo2Budget = budget;
    }

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
        playerView.setFitWidth(CityMapController.WIDTH);
        playerView.setFitHeight(CityMapController.HEIGHT);
        playerView.setSmooth(true);

        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    }

    public void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY) {
        int proposedX = Math.min(Math.max(playerLocation.getX() + deltaX, 0), CityMapController.COLS - 1);
        int proposedY = Math.min(Math.max(playerLocation.getY() + deltaY, 0), CityMapController.ROWS - 1);
    
        // Check for obstacles before moving the player
        if (!Obstacles.checkObstacles(proposedX, proposedY)) {
            cityMapGrid.getChildren().remove(playerView);
            playerLocation.setX(proposedX);
            playerLocation.setY(proposedY);
            cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
            GameEngine.checkGemCollected(cityMainStack, cityMapGrid);
        }
    }

    public void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        this.playerLocation.setX(station.getX());
        this.playerLocation.setY(station.getY());
        cityMapGrid.add(playerView, this.playerLocation.getX(), this.playerLocation.getY());
    }
    
}