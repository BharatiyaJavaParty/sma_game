package bjp.utility;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class RandomSquare {
    public static void colorRandomSquare(GridPane grid, int rows, int cols) {
        Random rand = new Random();
        // Use the ROWS and COLS directly instead of grid constraints
        int row = rand.nextInt(rows); // rows should be the total number of rows
        int col = rand.nextInt(cols); // cols should be the total number of columns
        for (Node node : grid.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && node instanceof Rectangle) {
                ((Rectangle) node).setFill(Color.RED);
                break; // Stop once the first match is found and colored
            }
        }
    }
}
