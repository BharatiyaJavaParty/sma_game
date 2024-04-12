package bjp.controller;

import java.util.ArrayList;

import bjp.controller.PopupController;
import bjp.utility.Bus;
import bjp.utility.Gem;
import bjp.utility.Location;
import bjp.utility.Luas;
import bjp.utility.NonRandomSquare;
import bjp.utility.Player;
import bjp.utility.RandomSquare;
import bjp.utility.StaticTransportConfig;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class CityMapController {
    @FXML
    private GridPane cityMapGrid;

    @FXML
    private StackPane cityMainStack;
    public static final int ROWS = 30;
    public static final int COLS = 30;
    
    @FXML
    private void initialize() {
        generateGrid(ROWS, COLS);
        Bus.makeBusRoad(StaticTransportConfig.BUS1, StaticTransportConfig.BUS1_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS2, StaticTransportConfig.BUS2_STOPS, cityMapGrid);
        Luas.makeLuasLane(StaticTransportConfig.LUAS, StaticTransportConfig.LUAS_STOPS, cityMapGrid);
        Gem.placeGem(cityMainStack, cityMapGrid);
        Player.placePlayer(cityMapGrid);
        PopupController.showPopupMessage(cityMainStack, "Welcome to Gem World");
        // cityMapGrid.setGridLinesVisible(true);

        CityMapController.mainEventHandler(cityMainStack, cityMapGrid);

    }

    private void generateGrid(int rows, int cols) {
        cityMapGrid.getChildren().clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(20, 20);
                rect.setFill(Color.WHITE);
                cityMapGrid.add(rect, col, row);
            }
        }
    }

    public static void mainEventHandler(StackPane cityMainStack, GridPane cityMapGrid){
        final ArrayList<Location> res = new ArrayList<>();
        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Player.movePlayer(cityMainStack, cityMapGrid, 0, -1);
                    break;
                case DOWN:
                    Player.movePlayer(cityMainStack, cityMapGrid, 0, 1);
                    break;
                case LEFT:
                    Player.movePlayer(cityMainStack, cityMapGrid, -1, 0);
                    break;
                case RIGHT:
                    Player.movePlayer(cityMainStack, cityMapGrid, 1, 0);
                    break;
                case ENTER:
                    if (Player.foundTransport){
                        res.clear();
                        res.addAll(Player.checkTransportOptionsAndMoveUpdated(cityMapGrid));
                    }
                    break;
                case N:
                    if (!res.isEmpty() && res.get(0) != null) {
                        Player.movePlayerToStation(cityMapGrid, res.get(0));
                    }
                    break;
                case P:
                    if (res.size() > 1 && res.get(1) != null) {
                        Player.movePlayerToStation(cityMapGrid, res.get(1));
                    }
                    break;
                default:
                    break;
            }
            Player.checkTransportOptions(cityMainStack, cityMapGrid);
            event.consume();
        });
        cityMapGrid.setOnKeyReleased(event->{
            if(Player.getPlayerLocation().getX() == Gem.getGemLocation().getX() && Player.getPlayerLocation().getY() == Gem.getGemLocation().getY()){
                System.err.println("Win");
                System.exit(0);
            }
            event.consume();
        });
    }
}