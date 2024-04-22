package bjp.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import bjp.constants.AppConstants;
import bjp.controller.CityMapController;

public class Player {
    // player properties
    // ideally player should have object and not all fucntions should not be called
    // using class name

    private String playerName;
    private Location playerLocation;

    // setting time and co2emission
    private int playerTime;
    private int playerCo2Budget;

    public Player() {
        this.playerTime = 0;
        this.playerCo2Budget = AppConstants.CO2_BUDGET;
    }

    // private static ImageView playerView;
    private static final Image playerImage = new Image(Gem.class.getResourceAsStream("/img/gamer.png"));
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
    private static final Image VEHICLE_IMAGE = new Image(Player.class.getResourceAsStream("/img/luas-stop.png"));
    // Existing left and right images

    private static ImageView playerView = new ImageView(PLAYER_IMAGE_DOWN1);
    private boolean isMoving = false;
    private int lastDirection = 0; // 0 = up, 1 = down, 2 = left, 3 = right
    private static final Duration MOVE_DURATION = Duration.millis(200);

    public int getPlayerTime() {
        return this.playerTime;
    }

    public void setPlayerTime(int time) {
        System.out.println("Player Time " + this.playerTime);
        System.out.println("======================================================");
        this.playerTime = time;
    }

    public int getPlayerCo2Budget() {
        return this.playerCo2Budget;
    }

    public void setPlayerCo2Budget(int budget) {
        System.out.println("======================================================");
        System.out.println("Player CO2 budget " + this.playerCo2Budget);
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

    public void placePlayer(GridPane cityMapGrid) {
        Location startLocation = new Location("House1", 16, 10);
        this.setPlayerLocation(startLocation);

        playerView.setFitWidth(CityMapController.WIDTH);
        playerView.setFitHeight(CityMapController.HEIGHT);
        playerView.setSmooth(true);

        cityMapGrid.getChildren().remove(playerView);
        cityMapGrid.add(playerView, playerLocation.getX(), playerLocation.getY());

        SoundEffects.startGame();
        ;
        animateMove(cityMapGrid, 2);
    }

    private void animateMove(GridPane cityMapGrid, int steps) {
        Timeline timeline = new Timeline();
        for (int i = 1; i <= steps; i++) {
            int newY = playerLocation.getY() + i;
            KeyFrame keyFrame = new KeyFrame(
                    MOVE_DURATION.multiply(i),
                    e -> {
                        cityMapGrid.getChildren().remove(playerView);
                        cityMapGrid.add(playerView, playerLocation.getX(), newY);
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
        Duration totalDuration = Duration.millis(path.size() * stepDuration);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        playerView.setImage(VEHICLE_IMAGE);
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
            System.out.println("Finished moving to station");
            System.out.println(playerLocation.getX());
            playerLocation = path.get(path.size() - 1);
            playerView.setImage(PLAYER_IMAGE_DOWN1);
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

    public void saveResults(int gems) {
        try {
            File file = new File("Score.json");
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode arrayNode;

            if (file.exists() && file.length() != 0) {
                arrayNode = (ArrayNode) mapper.readTree(file);
            } else {
                file.createNewFile();
                arrayNode = mapper.createArrayNode();
            }

            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("playerName", playerName);
            jsonObject.put("playerCO2Budget", playerCo2Budget);
            jsonObject.put("playerTime", playerTime);
            jsonObject.put("gems", gems);
            arrayNode.add(jsonObject);

            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(jsonString);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> getResults() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        File file = new File("Score.json");
        if (!file.exists()) {
            System.out.println("Score.json file not found");
            return res; // Early return to avoid further processing
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(file);
            for (JsonNode rootNode : arrayNode) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(rootNode.get("playerName").asText());
                temp.add(String.valueOf(rootNode.get("playerCO2Budget").asInt()));
                temp.add(String.valueOf(rootNode.get("playerTime").asInt()));
                temp.add(String.valueOf(rootNode.get("gems").asInt()));
                res.add(temp);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON file");
            e.printStackTrace();
        }
        return res;
    }

}