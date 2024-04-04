package bjparty.controller;

import bjparty.utility.RandomSquare;
import bjparty.utility.NonRandomSquare;
import bjparty.utility.Luas;
import bjparty.utility.Bus;
import bjparty.utility.Gem;
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
        // RandomSquare.colorRandomSquare(cityMapGrid, ROWS, COLS);
        // NonRandomSquare.colorSpecificSquare(cityMapGrid, 5, 20);
        Luas.makeLuasLane(cityMapGrid);
        Bus.makeBusRoad(cityMapGrid, Bus.Bus1);
        Bus.makeBusRoad(cityMapGrid, Bus.Bus2);
        Gem.placeGem(cityMapGrid);
        cityMapGrid.setGridLinesVisible(true); // Show grid lines

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
