package bjp.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

import javafx.util.Duration;
import javafx.util.Pair;
import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import bjp.Main;
import bjp.utility.Location;
import bjp.constants.AppConstants;
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
    public static int maxLevel = 3;
    public static int current_level = 1;
    public static ArrayList<Gem> gems = new ArrayList<Gem>();
    public static final ArrayList<Location> res = new ArrayList<>();
    static {
        gems.add(new Gem("red"));
        gems.add(new Gem("orange"));
        gems.add(new Gem("green"));
    }

    // the keys in levels hashmap represent the level
    // the key is level and the value s after how many gems level gets completed
    public static HashMap<Integer, Integer> levels = new HashMap<Integer, Integer>() {
        {
            put(1, 2);
            put(2, 4);
            put(3, 7); // if added new level change exit condion as well in check gems collected
        }
    };

    public static void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid) {
        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, -1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sameStation = true;
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    break;
                case DOWN:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, 1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sameStation = true;
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    break;
                case LEFT:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, -1, 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sameStation = true;
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    break;
                case RIGHT:
                    try {
                        newPlayer.movePlayer(cityMainStack, cityMapGrid, 1, 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sameStation = true;
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget() - AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime() + AppConstants.WALKING_TIME_INCREMENT);
                    newPlayer.setPlayerCo2Spent(newPlayer.getPlayerCo2Spent() + AppConstants.WALKING_CO2_REDUCTION);
                    break;
                case ENTER:
                    if (foundTransport && sameStation) {
                        sameStation = false;
                        res.clear();
                        checkTransportOptionsAndMoveUpdated(cityMainStack, cityMapGrid);
                    }
                    break;
                case N:
                    if (!res.isEmpty() && res.get(0) != null) {
                        sameStation = true;
                        isAnimating = true;
                        newPlayer.movePlayerToStation(cityMapGrid, res.get(0), "N");
                        res.clear();
                    }
                    break;
                case P:
                    if (res.size() > 1 && res.get(1) != null) {
                        sameStation = true;
                        isAnimating = true;
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
            event.consume();
        });
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

}

class locationComparator implements Comparator<Location> {
    public int compare(Location l1, Location l2) {
        if (l1.getX() > l2.getX()) {
            return 1;
        } else if (l1.getX() < l2.getX()) {
            return -1;
        } else {
            if (l1.getY() > l2.getY()) {
                return 1;
            } else if (l1.getY() < l2.getY()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}