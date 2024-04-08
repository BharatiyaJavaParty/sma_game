package bjparty.controller;

import bjparty.utility.RandomSquare;
import bjparty.utility.StaticTransportConfig;
import bjparty.utility.NonRandomSquare;
import bjparty.utility.Luas;
import bjparty.utility.Bus;
import bjparty.utility.Gem;
import bjparty.utility.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CityMapController {
    @FXML
    private GridPane cityMapGrid;

    public static final int ROWS = 30;
    public static final int COLS = 30;
    
    @FXML
    private void initialize() {
        generateGrid(ROWS, COLS);
        Luas.makeLuasLane(StaticTransportConfig.LUAS, StaticTransportConfig.LUAS_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS1, StaticTransportConfig.BUS1_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS2, StaticTransportConfig.BUS2_STOPS, cityMapGrid);
        Gem.placeGem(cityMapGrid);
        Player.placePlayer(cityMapGrid);
        cityMapGrid.setGridLinesVisible(true);

        cityMapGrid.setFocusTraversable(true);
        cityMapGrid.requestFocus();
    
        cityMapGrid.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Player.movePlayer(cityMapGrid, 0, -1);
                    break;
                case DOWN:
                    Player.movePlayer(cityMapGrid, 0, 1);
                    break;
                case LEFT:
                    Player.movePlayer(cityMapGrid, -1, 0);
                    break;
                case RIGHT:
                    Player.movePlayer(cityMapGrid, 1, 0);
                    break;
                default:
                    break;
            }
            event.consume();
        });
    }

    private void generateGrid(int rows, int cols) {
        cityMapGrid.getChildren().clear(); // Clear existing content if any
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(20, 20);
                rect.setFill(Color.WHITE); // Default color
                cityMapGrid.add(rect, col, row);
            }
        }
    }
}
