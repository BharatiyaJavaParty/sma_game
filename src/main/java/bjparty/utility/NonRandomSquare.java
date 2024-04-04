package bjparty.utility;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class NonRandomSquare {
    public static void colorSpecificSquare(GridPane grid, int row, int col) {
        for (Node node : grid.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && node instanceof Rectangle) {
                ((Rectangle) node).setFill(Color.BLUE);
                break; // Stop once the first match is found and colored
            }
        }
    }
}
