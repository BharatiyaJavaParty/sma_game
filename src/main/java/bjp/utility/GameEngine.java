package bjp.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.util.Duration;
import javafx.util.Pair;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import bjp.Main;
import bjp.constants.AppConstants;
import bjp.controller.CityMapController;
import bjp.controller.PopupController;
import bjp.controller.ScoreBoardController;

public class GameEngine {

    public static Player newPlayer = new Player();
    public static int gemCount;
    public static boolean isAnimating = false;

    public static boolean foundTransport = false;
    public static boolean sameStation = true;

    public static boolean atLuas = false;
    public static boolean atRedLuas = false;
    public static boolean atBus1 = false;
    public static boolean atBus2 = false;
    public static boolean atBus3 = false;

    private static final Image NEXT = new Image(Player.class.getResourceAsStream("/img/N.png"));
    private static final Image PREVIOUS = new Image(Player.class.getResourceAsStream("/img/P.png"));
    private static ImageView nextView = new ImageView(NEXT);
    private static ImageView prevView = new ImageView(PREVIOUS);

    public static int maxLevel = 3;
    public static int current_level = 1;
    public static ArrayList<Gem> gems = new ArrayList<Gem>();
    public static final ArrayList<Location> res = new ArrayList<>();
    static {
        gems.add(new Gem("red"));
        gems.add(new Gem("orange"));
        gems.add(new Gem("green"));

        nextView.setFitWidth(CityMapController.WIDTH);
        nextView.setFitHeight(CityMapController.HEIGHT);
        nextView.setSmooth(true);

        prevView.setFitWidth(CityMapController.WIDTH);
        prevView.setFitHeight(CityMapController.HEIGHT);
        prevView.setSmooth(true);
    }

    public static HashMap<Integer, Integer> levels = new HashMap<Integer, Integer>() {
        {
            put(1, 4);
            put(2, 12);
            put(3, 24);
        }
    };

    public static void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid, Label co2label, Label timelabel, Label gemslabel, Label transportlabel) {
        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, -1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    if(!Obstacles.checkObstacles(newPlayer.getPlayerLocation().getX(),newPlayer.getPlayerLocation().getY() - 1))
                    {
                        sameStation = true;
                        newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                        newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    }
                    break;
                case S:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, 1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    if(!Obstacles.checkObstacles(newPlayer.getPlayerLocation().getX(),newPlayer.getPlayerLocation().getY() + 1))
                    {
                        sameStation = true;
                        newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                        newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    }                    break;
                case A:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, -1, 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    if(!Obstacles.checkObstacles(newPlayer.getPlayerLocation().getX() - 1,newPlayer.getPlayerLocation().getY()))
                    {
                        sameStation = true;
                        newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                        newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    }                    break;
                case D:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 1, 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    if(!Obstacles.checkObstacles(newPlayer.getPlayerLocation().getX() + 1,newPlayer.getPlayerLocation().getY()))
                    {
                        sameStation = true;
                        newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                        newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    }                    break;
                case ENTER:
                    if (foundTransport && sameStation) {
                        sameStation = false;
                        res.clear();
                        checkTransportOptionsAndMoveUpdated(cityMainStack, cityMapGrid);
                        showNextAndPrevious(cityMapGrid);
                    }
                    break;
                case N:
                    if (!res.isEmpty() && res.get(0) != null) {
                        sameStation = true;
                        isAnimating = true;
                        cityMapGrid.getChildren().removeIf(node -> node == prevView);
                        cityMapGrid.getChildren().removeIf(node -> node == nextView);
                        newPlayer.movePlayerToStation(cityMapGrid, res.get(0), "N");
                        res.clear();
                    }
                    break;
                case P:
                    if (res.size() > 1 && res.get(1) != null) {
                        sameStation = true;
                        isAnimating = true;
                        cityMapGrid.getChildren().removeIf(node -> node == prevView);
                        cityMapGrid.getChildren().removeIf(node -> node == nextView);
                        newPlayer.movePlayerToStation(cityMapGrid, res.get(1), "P");
                        res.clear();
                    }
                    break;
                default:
                    break;
            }
            if (!isAnimating){
                checkTransportOptions(cityMainStack, cityMapGrid);
            }
            if (sameStation && foundTransport){
                PopupController.showPopupMessage(cityMainStack, "Press ENTER to travel");
            }
            updateGameLables(co2label, timelabel, gemslabel, transportlabel);
            event.consume();
        });
    }

    public static void updateGameLables(Label co2label, Label timelabel, Label gemslabel, Label transportlabel){
        co2label.setText(String.valueOf(GameEngine.newPlayer.getPlayerCo2Spent()));
        timelabel.setText(String.valueOf(GameEngine.newPlayer.getPlayerTime()));
        gemslabel.setText(String.valueOf(GameEngine.gemCount));
        if(atBus1 || atBus2 || atBus3)
        {
            transportlabel.setText("Walking and Bus");
        }
        else if(atLuas)
        {
            transportlabel.setText("Walking and Green Luas");
        }
        else if(GameEngine.atRedLuas)
        {
            transportlabel.setText("Walking and Red luas");
        }
        else
        {
            transportlabel.setText("Walking");
        }
    }

    public static void checkGemCollected(StackPane cityMainStack, GridPane cityMapGrid, Gem gem) throws FileNotFoundException {
        if (gemCount == levels.get(3)) {
            PopupController.showPopupMessage(cityMainStack, "You Win!!");
            ScoreBoardController.saveResults(gemCount);
            PauseTransition pause = new PauseTransition(Duration.millis(1000));
            ScoreBoardController.getResults();
            pause.play();
            try {
                Main.setRoot("scoreboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (gem.getGemLocation().getX() == -1) {
            gem.placeGem(cityMainStack, cityMapGrid);
        } else {
            if (gem.getGemLocation().getX() == newPlayer.getPlayerLocation().getX()
                    && gem.getGemLocation().getY() == newPlayer.getPlayerLocation().getY()) {
                gem.placeGem(cityMainStack, cityMapGrid);
                gemCount = gemCount + 1;
                SoundEffects.playGemCollectedSound();
                newPlayer.bouncePlayer();
                PopupController.showPopupMessage(cityMainStack,
                        "You have collected " + String.valueOf(gemCount) + " Gems!");
            }
            if (gemCount == levels.get(current_level) && current_level < 2) {
                current_level += 1;
                SoundEffects.newLevel();
                PopupController.showPopupMessage(cityMainStack, "Congratulations Level 2!");
            }
            if (gemCount == levels.get(current_level) && current_level < 3) {
                current_level += 1;
                SoundEffects.newLevel();
                PopupController.showPopupMessage(cityMainStack, "Congratulations Level 3!");
            }
        }
    }

    public static void checkTransportOptions(StackPane cityMainStack, GridPane cityMapGrid) {
        foundTransport = false;
        atLuas = false;
        atBus1 = false;
        atBus2 = false;
        atBus3 = false;
        atRedLuas = false;

        if (StaticTransportConfig.isPlayerAtLuasStop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            foundTransport = true;
            atLuas = true;
            newPlayer.bouncePlayer();
            SoundEffects.playTransportSound();
        }

        if (StaticTransportConfig.isPlayerAtRedLuasStop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            foundTransport = true;
            atRedLuas = true;
            newPlayer.bouncePlayer();
            SoundEffects.playTransportSound();
        }

        if (StaticTransportConfig.isPlayerAtBus1Stop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            foundTransport = true;
            atBus1 = true;
            SoundEffects.playTransportSound();
            newPlayer.bouncePlayer();
        }

        if (StaticTransportConfig.isPlayerAtBus2Stop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            foundTransport = true;
            atBus2 = true;
            SoundEffects.playTransportSound();
            newPlayer.bouncePlayer();
        }

        if (StaticTransportConfig.isPlayerAtBus3Stop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            foundTransport = true;
            atBus3 = true;
            SoundEffects.playTransportSound();
            newPlayer.bouncePlayer();
        }
    }

    public static void checkTransportOptionsAndMoveUpdated(StackPane cityMainStack, GridPane cityMapGrid) {
        Location currentStation = null;
        Location nextStation = null;
        Location previousStation = null;
        HashMap<Location, Pair<Location, Location>> STOPS = new HashMap<Location, Pair<Location, Location>>();
        List<Location> KeysAsList = new ArrayList<Location>();

        if (atLuas) {
            KeysAsList = new ArrayList<>(StaticTransportConfig.LUAS1_STOPS.keySet());
            STOPS = new HashMap<>(StaticTransportConfig.LUAS1_STOPS);

            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.LUAS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.LUAS_TIME_INCREMENT);
            newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.LUAS_CO2_REDUCTION);
        } else if (atBus1) {
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
            STOPS = new HashMap<>(StaticTransportConfig.BUS1_STOPS);

            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.BUS_TIME_INCREMENT);
            newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.BUS_CO2_REDUCTION);
        } else if (atBus2) {
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
            STOPS = new HashMap<>(StaticTransportConfig.BUS2_STOPS);

            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.BUS_TIME_INCREMENT);
            newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.BUS_CO2_REDUCTION);
        } else if (atBus3) {
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS3_STOPS.keySet());
            STOPS = new HashMap<>(StaticTransportConfig.BUS3_STOPS);

            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.BUS_TIME_INCREMENT);
            newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.BUS_CO2_REDUCTION);
        } else if (atRedLuas) {
            KeysAsList = new ArrayList<>(StaticTransportConfig.LUAS2_STOPS.keySet());
            STOPS = new HashMap<>(StaticTransportConfig.LUAS2_STOPS);

            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.BUS_TIME_INCREMENT);
            newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.LUAS_CO2_REDUCTION);

        }

        for (int i = 0; i < KeysAsList.size(); i++) {
            Location key = KeysAsList.get(i);
            if (key.getX() == newPlayer.getPlayerLocation().getX() && key.getY() == newPlayer.getPlayerLocation().getY()) {
                currentStation = new Location(key);
                if (i + 1 <= KeysAsList.size()) {
                    nextStation = new Location(STOPS.get(key).getValue());
                }
                if (i >= 0) {
                    previousStation = new Location(STOPS.get(key).getKey());
                }
                break;
            }
        }
        
        String temp = " Press ";
        if (currentStation != null) {
            if (nextStation != null) {
                if (currentStation.getLocationName() != nextStation.getLocationName()){
                    temp = temp + " N - " + nextStation.getLocationName();
                }
                res.add(nextStation);
            }
            if (previousStation != null) {
                if (currentStation.getLocationName() != previousStation.getLocationName()){
                    temp = temp + "      P - " + previousStation.getLocationName();
                }
                res.add(previousStation);
            }
            PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + temp);
        }
    }

    public static void showNextAndPrevious(GridPane cityMapGrid){

        if (!res.get(0).equals(newPlayer.getPlayerLocation())){
            cityMapGrid.getChildren().removeIf(node -> node == nextView);
            cityMapGrid.add(nextView, res.get(0).getX(), res.get(0).getY());
            TranslateTransition transition1 = new TranslateTransition(Duration.millis(200), nextView);
            transition1.setFromY(0);
            transition1.setToY(-13);
            transition1.setCycleCount(TranslateTransition.INDEFINITE);
            transition1.setAutoReverse(true);
            transition1.play();
        }

        if (!res.get(1).equals(newPlayer.getPlayerLocation())){
            cityMapGrid.getChildren().removeIf(node -> node == prevView);
            cityMapGrid.add(prevView, res.get(1).getX(), res.get(1).getY());
            TranslateTransition transition2 = new TranslateTransition(Duration.millis(200), prevView);
            transition2.setFromY(0);
            transition2.setToY(-13);
            transition2.setCycleCount(TranslateTransition.INDEFINITE);
            transition2.setAutoReverse(true);
            transition2.play();
        }

    }

}
