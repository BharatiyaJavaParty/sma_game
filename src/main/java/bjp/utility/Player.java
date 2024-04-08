package bjp.utility;


import java.util.Random;

import bjp.controller.CityMapController;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    public static int playerX;
    public static int playerY;
    private static Rectangle playerRect;

    // Initialize the player on the grid
    public static void placePlayer(GridPane cityMapGrid) {
        Random random = new Random();
        playerX = random.nextInt(CityMapController.COLS);
        playerY = random.nextInt(CityMapController.ROWS);
        playerRect = new Rectangle(20, 20);
        playerRect.setFill(Color.BLUE);
        cityMapGrid.add(playerRect, playerX, playerY);
    }

    // Move the player by updating its position
    public static void movePlayer(GridPane cityMapGrid, int deltaX, int deltaY) {
        cityMapGrid.getChildren().remove(playerRect); // Remove the old player position
        playerX += deltaX;
        playerY += deltaY;
        // Ensure the player does not move outside the grid
        playerX = Math.min(Math.max(playerX, 0), CityMapController.COLS - 1);
        playerY = Math.min(Math.max(playerY, 0), CityMapController.ROWS - 1);
        cityMapGrid.add(playerRect, playerX, playerY); // Add the player at the new position
    }
}