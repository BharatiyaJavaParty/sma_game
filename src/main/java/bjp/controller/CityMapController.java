package bjp.controller;

import bjp.utility.GameEngine;
import bjp.utility.Bus;
import bjp.utility.EnvironmentalPopup;
import bjp.utility.Gem;
import bjp.utility.Luas;
import bjp.utility.Obstacles;
import bjp.utility.StaticTransportConfig;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.time.Duration;
import java.util.ArrayList;

import bjp.Main;

public class CityMapController {
    public static final int WIDTH = ((int) Main.scene.getHeight() - 50)/20;
    public static final int HEIGHT = ((int) Main.scene.getHeight() - 50)/20;
    @FXML
    private GridPane cityMapGrid;

    @FXML
    private StackPane cityMainStack;
    public static final int ROWS = (int) Main.scene.getHeight()/ (int) HEIGHT;
    public static final int COLS = (int) Main.scene.getWidth()/ (int) WIDTH;
    
    @FXML
    private void initialize() {
        generateGrid(ROWS, COLS);
        Bus.makeBusRoad(StaticTransportConfig.BUS1, StaticTransportConfig.BUS1_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS2, StaticTransportConfig.BUS2_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS3, StaticTransportConfig.BUS3_STOPS, cityMapGrid);
        Luas.makeLuasLane_green(StaticTransportConfig.LUAS1, StaticTransportConfig.LUAS1_STOPS, cityMapGrid);
        Luas.makeLuasLane_red(StaticTransportConfig.LUAS2, StaticTransportConfig.LUAS2_STOPS, cityMapGrid);    

        GameEngine.newPlayer.placePlayer(cityMapGrid);
        Obstacles.placeTrees(cityMapGrid);
        Obstacles.placeHouses(cityMapGrid);
    
        PopupController.showPopupMessage(cityMainStack, "Welcome to Gem World");

        Thread thread = new Thread(() -> {
            while (true) {
                try
                {
                    // Sleep for 10 seconds before showing the popup
                    Thread.sleep(10000); 
                    // Execute the popup display on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        EnvironmentalPopup.showEduPopup(cityMainStack, EnvironmentalPopup.selectMsg());
                    });
                } 
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        GameEngine.mainEventHandler(cityMainStack, cityMapGrid);
       
        // cityMapGrid.setGridLinesVisible(true);
    }
    
    private void generateGrid(int rows, int cols) {
        Image grassImage = new Image(Obstacles.class.getResourceAsStream("/img/grass.png")); // Replace with the correct path
        ImagePattern grassPattern = new ImagePattern(grassImage);
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        Rectangle rect = new Rectangle(WIDTH, HEIGHT);
                        rect.setFill(grassPattern);
                        cityMapGrid.add(rect, col, row);
                    }
                }
            }
}


