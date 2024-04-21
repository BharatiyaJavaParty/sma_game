package bjp.utility;

import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import bjp.constants.AppConstants;
import bjp.controller.PopupController;
import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import bjp.utility.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameEngine {
    
    public static Player newPlayer = new Player();
    private static int gemCount; 

    public static boolean foundTransport = false;
    public static boolean atLuas = false;
    public static boolean atBus1 = false;
    public static boolean atBus2 = false;
    public static int maxLevel = 3;
    public static int current_level = 1;
    public static ArrayList<Gem> gems = new ArrayList<Gem>();
    static {
        gems.add(new Gem("red"));
        gems.add(new Gem("orange"));
        gems.add(new Gem("green"));
    }
    
    //the keys in levels hashmap represent the level
    //the key is level and the value s after how many gems level gets completed
    private static HashMap<Integer, Integer> levels = new HashMap<Integer, Integer>(){{
        put(1,2);
        put(2, 4);
        put(3, 6);  // if added new level change exit condion as well in check gems collected
    }};


    public static void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid){
        final ArrayList<Location> res = new ArrayList<>();
        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, -1);
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.WALKING_TIME_INCREMENT);
                    break;
                case DOWN:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, 1);
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.WALKING_TIME_INCREMENT);
                    break;
                case LEFT:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, -1, 0);
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.WALKING_TIME_INCREMENT);
                    break;
                case RIGHT:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 1, 0);
                    newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.WALKING_CO2_REDUCTION);
                    newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.WALKING_TIME_INCREMENT);
                    break;
                case ENTER:
                    if (foundTransport){
                        res.clear();
                        res.addAll(checkTransportOptionsAndMoveUpdated(cityMainStack, cityMapGrid));
                    }
                    break;
                case N:
                    if (!res.isEmpty() && res.get(0) != null) {
                        newPlayer.movePlayerToStation(cityMapGrid, res.get(0));
                        res.clear();
                    }
                    break;
                case P:
                    if (res.size() > 1 && res.get(1) != null) {
                        newPlayer.movePlayerToStation(cityMapGrid, res.get(1));
                        res.clear();
                    }
                    break;
                default:
                    break;
            }
            checkTransportOptions(cityMainStack, cityMapGrid);
            event.consume();
        });
    }

    public static void checkGemCollected(StackPane cityMainStack, GridPane cityMapGrid, Gem gem)
    {
        if (gemCount == levels.get(3)) {
            PopupController.showPopupMessage(cityMainStack, "You Win!!");
            PauseTransition pause = new PauseTransition(Duration.millis(1000));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        };

        if (gem.getGemLocation().getX() == -1){
            gem.placeGem(cityMainStack, cityMapGrid);
        }
        else{
            if (gem.getGemLocation().getX() == newPlayer.getPlayerLocation().getX() && gem.getGemLocation().getY() == newPlayer.getPlayerLocation().getY())
            {
                gem.placeGem(cityMainStack, cityMapGrid);
                gemCount = gemCount+1;
                SoundEffects.playGemCollectedSound();
                newPlayer.bouncePlayer();
                PopupController.showPopupMessage(cityMainStack, "You have collected " + String.valueOf(gemCount) + " Gems!");
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
    
        if (StaticTransportConfig.isPlayerAtLuasStop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            // PopupController.showPopupMessage(cityMainStack, newPlayer.getPlayerName() + " is at Luas");
            foundTransport = true;
            atLuas = true;
            newPlayer.bouncePlayer();
            SoundEffects.playTransportSound();
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            // PopupController.showPopupMessage(cityMainStack, newPlayer.getPlayerName() + " is at a Bus");
            foundTransport = true;
            atBus1 = true;
            SoundEffects.playTransportSound();
            newPlayer.bouncePlayer();
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(newPlayer.getPlayerLocation().getX(), newPlayer.getPlayerLocation().getY())) {
            // PopupController.showPopupMessage(cityMainStack, newPlayer.getPlayerName() + " is at a Bus");
            foundTransport = true;
            atBus2 = true;
            SoundEffects.playTransportSound();
            newPlayer.bouncePlayer();
        }
    }

    public static ArrayList<Location> checkTransportOptionsAndMoveUpdated(StackPane cityMainStack, GridPane cityMapGrid){
        ArrayList<Location> res = new ArrayList<Location>();
        Location currentStation = null;
        Location nextStation = null;
        Location previousStation = null;
        HashMap<Location, Pair<Location,Location>> STOPS = new HashMap<Location, Pair<Location,Location>>();
        List<Location> KeysAsList = new ArrayList<Location>();
    

        if(atLuas==true && (atBus1==false && atBus2==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.LUAS1_STOPS.keySet());
            STOPS = StaticTransportConfig.LUAS1_STOPS;

            //CO2 and Time reduction logic
            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.LUAS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.LUAS_TIME_INCREMENT);
        }
        else if(atBus1==true && (atLuas==false && atBus2==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS1_STOPS;

            //CO2 and Time reduction logic
            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.BUS_TIME_INCREMENT);
        }
        else if(atBus2==true && (atLuas==false && atBus1==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS2_STOPS;

            //CO2 and Time reduction logic
            newPlayer.setPlayerCo2Budget(newPlayer.getPlayerCo2Budget()-AppConstants.BUS_CO2_REDUCTION);
            newPlayer.setPlayerTime(newPlayer.getPlayerTime()+AppConstants.BUS_TIME_INCREMENT);
        }
        for (int i = 0; i < KeysAsList.size(); i++) {
            Location key = KeysAsList.get(i);
            if (key.getX() == newPlayer.getPlayerLocation().getX() && key.getY() == newPlayer.getPlayerLocation().getY()) {
                currentStation = key;
                if (i + 1 <= KeysAsList.size()) {
                    nextStation = STOPS.get(key).getValue();
                }
                if (i >= 0) {
                    previousStation = STOPS.get(key).getKey();
                }
                break;
            }
        }
    
        // if (nextStation != null) System.out.println("1 : " + nextStation.getLocationName());
        // if (previousStation != null) System.out.println("2 : " + previousStation.getLocationName());
        String temp = " ";
        if (currentStation != null) {
            System.out.println("Current Station: " + currentStation.getLocationName());
            if (nextStation != null) {
                temp = " N - " + nextStation.getLocationName();
                System.out.println("Press 'N' for Next Station: " + nextStation.getLocationName());
            }
            if (previousStation != null) {
                temp = temp + "; P - " + previousStation.getLocationName();
                System.out.println("Press 'P' for Previous Station: " + previousStation.getLocationName());
            }
        }
        PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + temp);

        res.add(nextStation);
        res.add(previousStation);
        return res;
    }

}
