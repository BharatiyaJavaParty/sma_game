package bjp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

import bjp.controller.PopupController;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameEngine {
    
    public static Player newPlayer = new Player();
    private Map<Integer, Integer> levelMap = new HashMap<>();
    private static int gemCount; 
    private static double co2Budget;

    public static boolean foundTransport = false;
    public static boolean atLuas = false;
    public static boolean atBus1 = false;
    public static boolean atBus2 = false;
    //level 1 - 5
    //level 2 - 

    public static void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid){
        final ArrayList<Location> res = new ArrayList<>();
        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, -1);
                    break;
                case DOWN:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 0, 1);
                    break;
                case LEFT:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, -1, 0);
                    break;
                case RIGHT:
                    newPlayer.movePlayer(cityMainStack, cityMapGrid, 1, 0);
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
        cityMapGrid.setOnKeyReleased(event->{
            if(newPlayer.getPlayerLocation().getX() == Gem.getGemLocation().getX() && GameEngine.newPlayer.getPlayerLocation().getY() == Gem.getGemLocation().getY()){
                System.err.println("Win");
                System.exit(0);
            }
            event.consume();
        });
    }

    public static void checkGemCollected(StackPane cityMainStack, GridPane cityMapGrid)
    {
        if (Gem.getGemLocation().getX() == newPlayer.playerLocation.getX() && Gem.getGemLocation().getY() == newPlayer.playerLocation.getY())
        {
            Gem.placeGem(cityMainStack, cityMapGrid);
            gemCount = gemCount+1;
            PopupController.showPopupMessage(cityMainStack, "You have collected " + String.valueOf(gemCount) + " Gems!");
        }
    }

    public static void checkTransportOptions(StackPane cityMainStack, GridPane cityMapGrid) {
        foundTransport = false;
        atLuas = false;
        atBus1 = false;
        atBus2 = false;
    
        if (StaticTransportConfig.isPlayerAtLuasStop(newPlayer.playerLocation.getX(), newPlayer.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, newPlayer.playerName + "is at Luas");
            foundTransport = true;
            atLuas = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus1Stop(newPlayer.playerLocation.getX(), newPlayer.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, newPlayer.playerName + " is at a Bus");
            foundTransport = true;
            atBus1 = true;
        }
    
        if (StaticTransportConfig.isPlayerAtBus2Stop(newPlayer.playerLocation.getX(), newPlayer.playerLocation.getY())) {
            PopupController.showPopupMessage(cityMainStack, newPlayer.playerName + " is at a Bus");
            foundTransport = true;
            atBus2 = true;
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
            KeysAsList = new ArrayList<>(StaticTransportConfig.LUAS_STOPS.keySet());
            STOPS = StaticTransportConfig.LUAS_STOPS;
        }
        else if(atBus1==true && (atLuas==false && atBus2==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS1_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS1_STOPS;
        }
        else if(atBus2==true && (atLuas==false && atBus1==false)){
            KeysAsList = new ArrayList<>(StaticTransportConfig.BUS2_STOPS.keySet());
            STOPS = StaticTransportConfig.BUS2_STOPS;
        }
        for (int i = 0; i < KeysAsList.size(); i++) {
            Location key = KeysAsList.get(i);
            if (key.getX() == newPlayer.playerLocation.getX() && key.getY() == newPlayer.playerLocation.getY()) {
                currentStation = key;
                if (i + 1 < KeysAsList.size()) {
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
    
        if (currentStation != null) {
            System.out.println("Current Station: " + currentStation.getLocationName());
            if (nextStation != null) {
                System.out.println("Press 'N' for Next Station: " + nextStation.getLocationName());
            }
            if (previousStation != null) {
                System.out.println("Press 'P' for Previous Station: " + previousStation.getLocationName());
            }
        }
        PopupController.showPopupMessage(cityMainStack, "Player is at " + currentStation.getLocationName() + " stop.");

        res.add(nextStation);
        res.add(previousStation);
        return res;
    }

}
