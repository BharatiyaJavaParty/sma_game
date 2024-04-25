package bjp.utility;

import java.util.ArrayList;
import java.io.FileNotFoundException;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


import bjp.constants.AppConstants;
import bjp.controller.CityMapController;

public class Player {
    private String playerName;
    private Location playerLocation;

    private int playerTime;
    private int playerCo2Budget;
    private int playerCo2Spent;

    public Player() {
        this.playerTime = 0;
        this.playerCo2Budget = AppConstants.CO2_BUDGET_L1;
        this.playerCo2Spent = 0;
    }

    private static final Image PLAYER_IMAGE_STILL = new Image(
            Gem.class.getResourceAsStream("/img/player_moments/walk1.png"));
    private static final Image PLAYER_IMAGE_MOVE = new Image(
            Gem.class.getResourceAsStream("/img/player_moments/walk2.png"));
    private static final Image PLAYER_IMAGE_UP1 = new Image(
            Player.class.getResourceAsStream("/img/player_moments/walkup1.png"));
    private static final Image PLAYER_IMAGE_UP2 = new Image(
            Player.class.getResourceAsStream("/img/player_moments/walkup2.png"));
    private static final Image PLAYER_IMAGE_DOWN1 = new Image(
            Player.class.getResourceAsStream("/img/player_moments/walkdown1.png"));
    private static final Image PLAYER_IMAGE_DOWN2 = new Image(
            Player.class.getResourceAsStream("/img/player_moments/walkdown2.png"));
    
    private static final Image BUS1_IMAGE = new Image(Player.class.getResourceAsStream("/img/bus1.png"));
    private static final Image BUS2_IMAGE = new Image(Player.class.getResourceAsStream("/img/bus2.png"));
    private static final Image BUS3_IMAGE = new Image(Player.class.getResourceAsStream("/img/bus3.png"));
    private static final Image LUAS_IMAGE = new Image(Player.class.getResourceAsStream("/img/greenLuas.png"));
    private static final Image RED_LUAS_IMAGE = new Image(Player.class.getResourceAsStream("/img/redLuas.png"));

    private static ImageView playerView = new ImageView(PLAYER_IMAGE_DOWN1);
    private boolean isMoving = false;
    private int lastDirection = 0;
    private static final Duration MOVE_DURATION = Duration.millis(200);

    public int getPlayerTime() {
        return this.playerTime;
    }

    public void setPlayerTime(int time) {
        this.playerTime = time;
    }

    public int getPlayerCo2Budget() {
        return this.playerCo2Budget;
    }

    public void setPlayerCo2Budget(int budget) {
        this.playerCo2Budget = budget;
    }

    public Location getPlayerLocation() {
        return this.playerLocation;
    }

    public void setPlayerLocation(Location playerNewLocation) {
        this.playerLocation = playerNewLocation;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public int getPlayerCo2Spent() {
        return playerCo2Spent;
    }

    public void setPlayerCo2Spent(int playerCo2Spent) {
        this.playerCo2Spent = playerCo2Spent;
    }

    public void placePlayer(GridPane cityMapGrid) {
        Location startLocation = new Location("House1", 20, 4);
        this.setPlayerLocation(startLocation);

        playerView.setFitWidth(CityMapController.WIDTH);
        playerView.setFitHeight(CityMapController.HEIGHT);
        playerView.setSmooth(true);

        cityMapGrid.getChildren().remove(playerView);
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());

        SoundEffects.startGame();
        animateMove(cityMapGrid, 2);
    }

    private void animateMove(GridPane cityMapGrid, int steps) {
        Timeline timeline = new Timeline();
        for (int i = 1; i <= steps; i++) {
            int newY = playerLocation.getY() + i;
            KeyFrame keyFrame = new KeyFrame(
                    MOVE_DURATION.multiply(i*3),
                    e -> {
                        cityMapGrid.getChildren().remove(playerView);
                        cityMapGrid.add(playerView, playerLocation.getX(), newY);
                        bouncePlayer();
                    });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> {
            playerLocation = new Location(this.playerName, playerLocation.getX(), playerLocation.getY() + steps);
        });

        timeline.play();
    }

    public void movePlayer(StackPane cityMainStack, GridPane cityMapGrid, int deltaX, int deltaY)
        throws FileNotFoundException {
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
            if (GameEngine.current_level >= 2 && GameEngine.gemCount != GameEngine.levels.get(3)) {
                GameEngine.checkGemCollected(cityMainStack, cityMapGrid, GameEngine.gems.get(1));
            }
            if (GameEngine.current_level >= 3 && GameEngine.gemCount != GameEngine.levels.get(3)) {
                GameEngine.checkGemCollected(cityMainStack, cityMapGrid, GameEngine.gems.get(2));
            }

            isMoving = !isMoving;

            cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
        }
    }

    public void movePlayerToStation(GridPane cityMapGrid, Location station, String direction) {
        ArrayList<Location> path = calculatePath(playerLocation, station, direction);
        animatePath(cityMapGrid, path);
    }

    private ArrayList<Location> calculatePath(Location start, Location end, String direction) {
        ArrayList<Location> path = new ArrayList<>();
        int xStep = Integer.signum(end.getX() - start.getX());
        int yStep = Integer.signum(end.getY() - start.getY());

        if (direction.equals("N")) {
            for (int x = start.getX(); x != end.getX(); x += xStep) {
                path.add(new Location("Intermediate", x, start.getY()));
            }
            path.add(new Location("Intermediate", end.getX(), start.getY()));

            for (int y = start.getY(); y != end.getY(); y += yStep) {
                path.add(new Location("Intermediate", end.getX(), y));
            }
        } else if (direction.equals("P")) {
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
        if (path.isEmpty())
            return;

        double stepDuration = 100;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        if(GameEngine.atBus1){
            playerView.setImage(BUS1_IMAGE);
        }
        else if(GameEngine.atBus2){
            playerView.setImage(BUS2_IMAGE);
        }
        else if(GameEngine.atBus3){
            playerView.setImage(BUS3_IMAGE);
        }
        else if(GameEngine.atLuas){
            playerView.setImage(LUAS_IMAGE);
        }
        else if (GameEngine.atRedLuas){
            playerView.setImage(RED_LUAS_IMAGE);
        }


        cityMapGrid.getChildren().remove(playerView);
        playerLocation = path.get(0);
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

        timeline.setOnFinished(e -> {
            playerLocation = path.get(path.size() - 1);
            playerView.setImage(PLAYER_IMAGE_DOWN1);
            cityMapGrid.getChildren().remove(playerView);
            cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());
            GameEngine.isAnimating = false;
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

}