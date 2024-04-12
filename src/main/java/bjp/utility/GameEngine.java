package bjp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameEngine {
    
    public static Player newPlayer = new Player();
    private Map<Integer, Integer> levelMap = new HashMap<>();
    //level 1 - 5
    //level 2 - 

    public void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid){
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
                    if (newPlayer.foundTransport){
                        res.clear();
                        res.addAll(newPlayer.checkTransportOptionsAndMoveUpdated(cityMainStack, cityMapGrid));
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
            newPlayer.checkTransportOptions(cityMainStack, cityMapGrid);
            event.consume();
        });
        cityMapGrid.setOnKeyReleased(event->{
            if(newPlayer.getPlayerLocation().getX() == Gem.getGemLocation().getX() && Player.getPlayerLocation().getY() == Gem.getGemLocation().getY()){
                System.err.println("Win");
                System.exit(0);
            }
            event.consume();
        });
    }



}
