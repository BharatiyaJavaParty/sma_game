package bjp.utility;


import java.util.Random;

import bjp.constants.AppConstants;
import bjp.controller.CityMapController;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
 

    // private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));
    private static final Image PLAYER_IMAGE_STILL = new Image(Gem.class.getResourceAsStream("/img/player_moments/walk1.png"));
    private static final Image PLAYER_IMAGE_MOVE = new Image(Gem.class.getResourceAsStream("/img/player_moments/walk2.png"));
    private static final Image PLAYER_IMAGE_UP1 = new Image(Player.class.getResourceAsStream("/img/player_moments/walkup1.png"));
    private static final Image PLAYER_IMAGE_UP2 = new Image(Player.class.getResourceAsStream("/img/player_moments/walkup2.png"));
    private static final Image PLAYER_IMAGE_DOWN1 = new Image(Player.class.getResourceAsStream("/img/player_moments/walkdown1.png"));
    private static final Image PLAYER_IMAGE_DOWN2 = new Image(Player.class.getResourceAsStream("/img/player_moments/walkdown2.png"));
// Existing left and right images

    private static ImageView playerView = new ImageView(PLAYER_IMAGE_DOWN1);
    private boolean isMoving = false;
    private int lastDirection = 0; // 0 = up, 1 = down, 2 = left, 3 = right


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

        playerView.setFitWidth(CityMapController.WIDTH);
        playerView.setFitHeight(CityMapController.HEIGHT);
        playerView.setSmooth(true);

        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    }

    public void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY) {
        int proposedX = Math.min(Math.max(playerLocation.getX() + deltaX, 0), CityMapController.COLS - 1);
        int proposedY = Math.min(Math.max(playerLocation.getY() + deltaY, 0), CityMapController.ROWS - 1);
    
        if (!Obstacles.checkObstacles(proposedX, proposedY)) {
            cityMapGrid.getChildren().remove(playerView);
            playerLocation.setX(proposedX);
            playerLocation.setY(proposedY);

            if (deltaY < 0) { 
                playerView.setImage(isMoving ? PLAYER_IMAGE_UP2 : PLAYER_IMAGE_UP1);
                lastDirection = 0;
            } else if (deltaY > 0) {
                playerView.setImage(isMoving ? PLAYER_IMAGE_DOWN2 : PLAYER_IMAGE_DOWN1);
                lastDirection = 1;
            } else if (deltaX < 0) {
                playerView.setScaleX(-1);
                playerView.setImage(isMoving ? PLAYER_IMAGE_MOVE : PLAYER_IMAGE_STILL);
                lastDirection = 2;
            } else if (deltaX > 0) {
                playerView.setScaleX(1);
                playerView.setImage(isMoving ? PLAYER_IMAGE_MOVE : PLAYER_IMAGE_STILL);
                lastDirection = 3;
            }
            GameEngine.checkGemCollected(cityMainStack, cityMapGrid, GameEngine.gems.get(0));
            if(GameEngine.current_level >= 2){
                GameEngine.checkGemCollected(cityMainStack, cityMapGrid, GameEngine.gems.get(1));
            }
            if(GameEngine.current_level >= 3){
                GameEngine.checkGemCollected(cityMainStack, cityMapGrid, GameEngine.gems.get(2));
            }

            isMoving = !isMoving;

            cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
            }
        }
    

    public void movePlayerToStation(GridPane cityMapGrid, Location station) {
        cityMapGrid.getChildren().remove(playerView);
        this.playerLocation.setX(station.getX());
        this.playerLocation.setY(station.getY());
        cityMapGrid.add(playerView, this.playerLocation.getX(), this.playerLocation.getY());
    }

    public void bouncePlayer() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), playerView);
        transition.setFromY(0);
        transition.setToY(-15); // Adjust the value to control the height of the bounce
        transition.setCycleCount(2); // Bounce up and down once
        transition.setAutoReverse(true);
        transition.play();
    }
    
}