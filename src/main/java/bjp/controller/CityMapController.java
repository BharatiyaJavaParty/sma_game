package bjp.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import bjp.utility.GameEngine;
import bjp.utility.Bus;
import bjp.utility.EnvironmentalPopup;
import bjp.utility.Luas;
import bjp.utility.Obstacles;
import bjp.utility.SoundEffects;
import bjp.utility.StaticTransportConfig;

public class CityMapController {
    public static final int WIDTH = ((int) 720) / 30;
    public static final int HEIGHT = ((int) 720) / 30;

    @FXML
    private GridPane cityMapGrid;

    @FXML
    private StackPane cityMainStack;

    // @FXML
    // private Button muteButton;

    @FXML 
    private Label co2label;

    @FXML
    private Label timelabel;

    @FXML
    private Label gemslabel;

    @FXML
    private Label transportlabel;

    public static final int ROWS = 32;
    public static final int COLS = 57;

    @FXML
    private void initialize() {
        generateGrid(ROWS, COLS);
        transportlabel.setText("Walking");
        Bus.makeBusRoad(StaticTransportConfig.BUS2, StaticTransportConfig.BUS2_STOPS, cityMapGrid, 2);
        Bus.makeBusRoad(StaticTransportConfig.BUS3, StaticTransportConfig.BUS3_STOPS, cityMapGrid, 3);
        Bus.makeBusRoad(StaticTransportConfig.BUS1, StaticTransportConfig.BUS1_STOPS, cityMapGrid, 1);
        Luas.makeLuasLane_green(StaticTransportConfig.LUAS1, StaticTransportConfig.LUAS1_STOPS, cityMapGrid);
        Luas.makeLuasLane_red(StaticTransportConfig.LUAS2, StaticTransportConfig.LUAS2_STOPS, cityMapGrid);

        GameEngine.newPlayer.placePlayer(cityMapGrid);
        Obstacles.placeTrees(cityMapGrid);
        Obstacles.placeHouses(cityMapGrid);
        Obstacles.placeBuildings(cityMapGrid);
        Obstacles.placeBushes(cityMapGrid);
        Obstacles.placeDoubleGTree(cityMapGrid);
        Obstacles.placeHomeTrees(cityMapGrid);
        PopupController.showPopupMessage(cityMainStack, "Welcome to Gem World");

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    Platform.runLater(() -> {
                        EnvironmentalPopup.showEduPopup(cityMainStack, EnvironmentalPopup.selectMsg());
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        //Refreshes player stats on top of screen every 0.5 seconds
        Thread statsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    Platform.runLater(() -> {
                        co2label.setText(String.valueOf(GameEngine.newPlayer.getPlayerCo2Spent()));
                        timelabel.setText(String.valueOf(GameEngine.newPlayer.getPlayerTime()));
                        gemslabel.setText(String.valueOf(GameEngine.gemCount));
                        setTransportLabel();
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        statsThread.start();
        GameEngine.mainEventHandler(cityMainStack, cityMapGrid); 
        // cityMapGrid.setGridLinesVisible(true);

    }

    //not working
    // @FXML
    // public void muteButtonClicked() {
    //     if (SoundEffects.getMediaPlayer().isMute()) {
    //         SoundEffects.getMediaPlayer().setMute(false);
    //     } else {
    //         SoundEffects.getMediaPlayer().setMute(true);
    //     }
    // }

    private void generateGrid(int rows, int cols) {
        Image grassImage = new Image(Obstacles.class.getResourceAsStream("/img/grass.png"));
        ImagePattern grassPattern = new ImagePattern(grassImage);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(WIDTH, HEIGHT);
                rect.setFill(grassPattern);
                cityMapGrid.add(rect, col, row);
            }
        }
    }

    // private void generateGrid(int rows, int cols) {
    //     Image grassImage = new Image(Obstacles.class.getResourceAsStream("/img/grass.png"));
    //     ImagePattern grassPattern = new ImagePattern(grassImage);
    //     for (int row = 0; row < rows; row++) {
    //         for (int col = 0; col < cols; col++) {
    //             // Check if it's the first row or first column
    //             if (row == 0 || col == 0) {
    //                 Text text = new Text();
    //                 if (row == 0 && col > 0) {
    //                     // First row, non-first column: number the columns
    //                     text.setText(String.valueOf(col));
    //                 } else if (col == 0 && row > 0) {
    //                     // First column, non-first row: number the rows
    //                     text.setText(String.valueOf(row));
    //                 }
    //                 cityMapGrid.add(text, col, row);
    //             } else {
    //                 // All other cells get the grass image
    //                 Rectangle rect = new Rectangle(WIDTH, HEIGHT);
    //                 rect.setFill(grassPattern);
    //                 cityMapGrid.add(rect, col, row);
    //             }
    //         }
    //     }
    // }

    private void setTransportLabel()
    {
        if(GameEngine.atBus1 || GameEngine.atBus2 || GameEngine.atBus3)
        {
            transportlabel.setText("Walking and Bus");
        }
        else if(GameEngine.atLuas)
        {
            transportlabel.setText("Walking and Green Luas");
        }
        else if(GameEngine.atRedLuas)
        {
            transportlabel.setText("Walking and Red luas");
        }
        else
        {
            transportlabel.setText("Walking");
        }
    }
}
