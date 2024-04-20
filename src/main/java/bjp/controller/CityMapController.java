package bjp.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bjp.controller.PopupController;
import bjp.utility.GameEngine;
import bjp.utility.Bus;
import bjp.utility.EnvironmentalPopup;
import bjp.utility.Gem;
import bjp.utility.Location;
import bjp.utility.Luas;
import bjp.utility.NonRandomSquare;
import bjp.utility.Obstacles;
import bjp.utility.Player;
import bjp.utility.RandomSquare;
import bjp.utility.StaticTransportConfig;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import bjp.Main;

public class CityMapController {
    public static final int WIDTH = ((int) Main.scene.getHeight() - 30)/20;
    public static final int HEIGHT = ((int) Main.scene.getHeight() - 30)/20;
    @FXML
    private GridPane cityMapGrid;

    @FXML
    private StackPane cityMainStack;
    public static final int ROWS = 20;
    public static final int COLS = 36;
    
    @FXML
    private void initialize() {
        generateGrid(ROWS, COLS);
        Bus.makeBusRoad(StaticTransportConfig.BUS1, StaticTransportConfig.BUS1_STOPS, cityMapGrid);
        Bus.makeBusRoad(StaticTransportConfig.BUS2, StaticTransportConfig.BUS2_STOPS, cityMapGrid);
        Luas.makeLuasLane(StaticTransportConfig.LUAS, StaticTransportConfig.LUAS_STOPS, cityMapGrid);
        Gem.placeGem(cityMainStack, cityMapGrid);
        // Player.placePlayer(cityMapGrid);
        GameEngine.newPlayer.placePlayer(cityMapGrid);
        Obstacles.placeTrees(cityMapGrid);
        PopupController.showPopupMessage(cityMainStack, "Welcome to Gem World");
        cityMapGrid.setGridLinesVisible(true);

        // CityMapController.mainEventHandler(cityMainStack, cityMapGrid);
        // ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Random rand = new Random();
        // int delay = rand.nextInt(5) + 1; 
        // executor.schedule(() -> {
        //     Platform.runLater(() -> {
        //         EnvironmentalPopup.showEduPopup(cityMainStack, EnvironmentalPopup.selectMsg());
        //     });
        // }, delay, TimeUnit.SECONDS);

        Thread thread = new Thread(() -> {
            while (true) {  // Infinite loop to keep the thread running
                try {
                    // Sleep for 3 seconds before showing the popup
                    Thread.sleep(10000);  // 3000 milliseconds = 3 seconds
        
                    // Execute the popup display on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        EnvironmentalPopup.showEduPopup(cityMainStack, EnvironmentalPopup.selectMsg());
                        System.out.println("Asynchronous task running");
                    });
        
                } catch (InterruptedException e) {
                    System.err.println("Thread was interrupted");
                    break;  // Optional: stop the thread if interrupted
                }
            }
        });
        thread.start();
        
        
        
        GameEngine.mainEventHandler(cityMainStack, cityMapGrid);

    }

    private void generateGrid(int rows, int cols) {
        cityMapGrid.getChildren().clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(WIDTH, HEIGHT);
                rect.setFill(Color.WHITE);
                cityMapGrid.add(rect, col, row);
            }
        }
    }
}