package bjp.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import bjp.utility.GameEngine;
import bjp.utility.Player;
import bjp.utility.Gem;

import bjp.utility.Bus;
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
    private GridPane miniMapGrid;

    @FXML
    private StackPane cityMainStack;

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
        miniMapGrid.setPadding(new Insets(0, 0, 0, 0));
        setupMiniMap();
        setupMainMap();
    }

    private void setupMainMap() {
        SoundEffects.bgmGame();
        generateGrid(ROWS, COLS, cityMapGrid, true);
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
        GameEngine.mainEventHandler(cityMainStack, cityMapGrid, miniMapGrid, co2label, timelabel, gemslabel, transportlabel);
    }

    private void setupMiniMap() {
        miniMapGrid.setPrefSize(320, 180);
        generateGrid(ROWS, COLS, miniMapGrid, false);
    }

    private static void generateGrid(int rows, int cols, GridPane grid, boolean isMain) {
        Image grassImage = new Image(Obstacles.class.getResourceAsStream("/img/grass.png"));
        ImagePattern grassPattern = new ImagePattern(grassImage);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rect = new Rectangle(isMain ? WIDTH : 6, isMain ? HEIGHT : 6);
                rect.setFill(grassPattern);
                grid.add(rect, col, row);
            }
        }
    }

    public static void updateMiniMap(GridPane cityMapGrid, GridPane miniMapGrid) {
        Platform.runLater(() -> {
            miniMapGrid.getChildren().clear();
            CityMapController.generateGrid(ROWS, COLS, miniMapGrid, false);
            for (Node child : cityMapGrid.getChildren()) {
                Node miniMapChild = cloneForMiniMap(child);
                if (miniMapChild != null) { 
                    int col = GridPane.getColumnIndex(child) != null ? GridPane.getColumnIndex(child) : 0;
                    int row = GridPane.getRowIndex(child) != null ? GridPane.getRowIndex(child) : 0;
                    miniMapGrid.add(miniMapChild, col, row);
                }
            }
        });
    }
    

    private static Node cloneForMiniMap(Node node) {
        if (node instanceof ImageView) {
            ImageView original = (ImageView) node;
            ImageView clone = new ImageView(original.getImage());
            clone.setFitWidth(6);
            clone.setFitHeight(6);
            clone.setPreserveRatio(true);
            clone.setSmooth(true);
    
            if (original.getImage().equals(Player.PLAYER_IMAGE_STILL) || 
                original.getImage().equals(Player.PLAYER_IMAGE_MOVE) || 
                original.getImage().equals(Player.PLAYER_IMAGE_UP1) || 
                original.getImage().equals(Player.PLAYER_IMAGE_UP2) ||
                original.getImage().equals(Player.PLAYER_IMAGE_DOWN1) ||
                original.getImage().equals(Player.PLAYER_IMAGE_DOWN2) ||
                original.getImage().equals(Gem.redGem) || 
                original.getImage().equals(Gem.orangeGem) || 
                original.getImage().equals(Gem.greenGem)) {
                applyBounceAnimation(clone);
            }
    
            return clone;
        }
        return null;
    }

    private static void applyBounceAnimation(ImageView imageView) {
    TranslateTransition bounce = new TranslateTransition(Duration.millis(500), imageView);
    bounce.setByY(-5);
    bounce.setCycleCount(TranslateTransition.INDEFINITE);
    bounce.setAutoReverse(true);
    bounce.play();
}

    
    
    
}
