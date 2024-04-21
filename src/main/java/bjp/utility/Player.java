package bjp.utility;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import bjp.constants.AppConstants;
import bjp.controller.CityMapController;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
    private static final Image VEHICLE_IMAGE = new Image(Player.class.getResourceAsStream("/img/luas-stop.png"));
// Existing left and right images

    private static ImageView playerView = new ImageView(PLAYER_IMAGE_DOWN1);
    private boolean isMoving = false;
    private int lastDirection = 0; // 0 = up, 1 = down, 2 = left, 3 = right
    private static final Duration MOVE_DURATION = Duration.millis(200);


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
        // Setting initial player location to predefined
        Location startLocation = new Location("House1", 16, 10);
        this.setPlayerLocation(startLocation);

        // Configuring player view properties
        playerView.setFitWidth(CityMapController.WIDTH);
        playerView.setFitHeight(CityMapController.HEIGHT);
        playerView.setSmooth(true);

        // Adding player to the grid at the start location
        cityMapGrid.getChildren().remove(playerView); // Ensure the view is not already on the grid
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());

        // Automatically move the player three steps down
        SoundEffects.startGame();;
        animateMove(cityMapGrid, 2);
    }

    private void animateMove(GridPane cityMapGrid, int steps) {
        Timeline timeline = new Timeline();
        for (int i = 1; i <= steps; i++) {
            int newY = playerLocation.getY() + i; 
            KeyFrame keyFrame = new KeyFrame(
                MOVE_DURATION.multiply(i), // Delay each step to animate sequentially
                e -> {
                    cityMapGrid.getChildren().remove(playerView); // Remove old position
                    cityMapGrid.add(playerView, playerLocation.getX(), newY); // Add new position
                });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> {
            // Update player's final location after animation
            playerLocation = new Location(this.playerName, playerLocation.getX(), playerLocation.getY() + steps);
        });

        timeline.play();
    }

    public void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY) throws FileNotFoundException {
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
    

    // public void movePlayerToStation(GridPane cityMapGrid, Location station) {
    //     cityMapGrid.getChildren().remove(playerView);
    //     this.playerLocation.setX(station.getX());
    //     this.playerLocation.setY(station.getY());
    //     cityMapGrid.add(playerView, this.playerLocation.getX(), this.playerLocation.getY());
    // }

    public void movePlayerToStation(GridPane cityMapGrid, Location station, String direction) {
        ArrayList<Location> path = calculatePath(playerLocation, station, direction);
        animatePath(cityMapGrid, path);
    }
    
    private ArrayList<Location> calculatePath(Location start, Location end, String direction) {
        ArrayList<Location> path = new ArrayList<>();
        int xStep = Integer.signum(end.getX() - start.getX());
        int yStep = Integer.signum(end.getY() - start.getY());
    
        if (direction.equals("N")) {
            // Horizontal then vertical movement
            for (int x = start.getX(); x != end.getX(); x += xStep) {
                path.add(new Location("Intermediate", x, start.getY()));
            }
            path.add(new Location("Intermediate", end.getX(), start.getY()));
    
            for (int y = start.getY(); y != end.getY(); y += yStep) {
                path.add(new Location("Intermediate", end.getX(), y));
            }
        } else if (direction.equals("P")) {
            // Vertical then horizontal movement
            for (int y = start.getY(); y != end.getY(); y += yStep) {
                path.add(new Location("Intermediate", start.getX(), y));
            }
            path.add(new Location("Intermediate", start.getX(), end.getY()));
    
            for (int x = start.getX(); x != end.getX(); x += xStep) {
                path.add(new Location("Intermediate", x, end.getY()));
            }
        }
        path.add(end);
    
        return path;
    }
    
    private void animatePath(GridPane cityMapGrid, ArrayList<Location> path) {
        if (path.isEmpty()) return;
    
        double stepDuration = 100; // Each grid cell animation takes 100 milliseconds
        Duration totalDuration = Duration.millis(path.size() * stepDuration);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
    
        // Change to vehicle image at the start of the animation
        playerView.setImage(VEHICLE_IMAGE);
        cityMapGrid.getChildren().remove(playerView);
        playerLocation = path.get(0); // Start at the first location
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
    
        for (int i = 0; i < path.size(); i++) {
            Location loc = path.get(i);
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(i * stepDuration),
                e -> {
                    cityMapGrid.getChildren().remove(playerView);
                    cityMapGrid.add(playerView, loc.getX(), loc.getY());
                });
            timeline.getKeyFrames().add(keyFrame);
        }
    
        // Change back to original player image when animation is finished
        timeline.setOnFinished(e -> {
            System.out.println("Finished moving to station");
            System.out.println(playerLocation.getX());
            playerLocation = path.get(path.size() - 1); // Update final position
            playerView.setImage(PLAYER_IMAGE_DOWN1); // Assume PLAYER_IMAGE_DOWN1 is the default player image
            cityMapGrid.getChildren().remove(playerView);
            cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
        });
    
        timeline.play();
    }
    

    public void bouncePlayer() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), playerView);
        transition.setFromY(0);
        transition.setToY(-15);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
    }

    public void saveResults(int gems){
        try{
            File file = new File("Score.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            String str = "\n"+playerName+" "+Integer.toString(playerCo2Budget)+" "+Integer.toString(playerTime)+" "+Integer.toString(gems);

            FileWriter fw = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        }
        catch(IOException e){
            System.out.println("An error occured while creating the file");
            e.printStackTrace();
        }
    }

    /**
     * This function returns a 2D arrayList with first column being playerName;
     * second column neing playerC02Budget at the time of wining the game
     * third column is playerTime
     * @return res
     * @throws FileNotFoundException
     */

    public static ArrayList<ArrayList<String>> getResults() throws FileNotFoundException{
        ArrayList res = new ArrayList<>();
        File file = new File("Score.txt");
        if(!file.exists()){
            System.out.println("Score.txt file not found");
        }
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            ArrayList<String> temp = new ArrayList<String>();
            String[] str = sc.nextLine().split(" ");
            for(String st:str){
                System.out.print(st);
                temp.add(st);
            }
            res.add(temp);
        }
        return res;
    }
    
}