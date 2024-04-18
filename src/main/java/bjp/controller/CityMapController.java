package bjp.controller;

import java.util.ArrayList;

import bjp.controller.PopupController;
import bjp.utility.GameEngine;
import bjp.utility.Bus;
import bjp.utility.Gem;
import bjp.utility.Location;
import bjp.utility.Luas;
import bjp.utility.NonRandomSquare;
import bjp.utility.Obstacles;
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
        GameEngine.newPlayer.placePlayer(cityMapGrid);
        Obstacles.placeTrees(cityMapGrid);
        PopupController.showPopupMessage(cityMainStack, "Welcome to Gem World");
        GameEngine.mainEventHandler(cityMainStack, cityMapGrid);

    }

    private void generateGrid(int rows, int cols) {
        cityMapGrid.getChildren().clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(30, 30);
                rect.setFill(Color.WHITE);
                cityMapGrid.add(rect, col, row);
            }
        }
    }
}