package bjparty.utility;
import bjparty.utility.Location;
import java.util.HashMap;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Luas{
    public static Double speed = 50.0;

    public static final Location stop1 = new Location("Phibsborough", 3, 3);
    public static final Location stop2 = new Location("Parnell", 9, 14);
    public static final Location stop3 = new Location("Abbey Street", 14, 15);
    public static final Location stop4 = new Location("Trinity", 20, 15);
    public static final Location stop5 = new Location("Dawson", 25, 16);
    public static final Location stop6 = new Location("UCD", 27, 24);

    public static final HashMap<Location, Location> luas = new HashMap<Location, Location>() {{
        put(stop1, stop2);
        put(stop2, stop3);
        put(stop3, stop4);
        put(stop4, stop5);
        put(stop5, stop6);
        put(stop6, stop6);
    }};
    
    public static void makeLuasLane(GridPane grid) {
        for (Location start : luas.keySet()) {
            Location end = luas.get(start);
            drawRoad(start, end, grid);
        }

        for (Location stop : luas.keySet()) {
            fillGridCell(grid, stop.getX(), stop.getY(), Color.BLACK);
        }
    }

    private static void drawRoad(Location start, Location end, GridPane grid) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        int minX = Math.min(startX, endX);
        int maxX = Math.max(startX, endX);
        int minY = Math.min(startY, endY);
        int maxY = Math.max(startY, endY);

        Color roadColor = Color.GREEN;

        for (int x = minX; x <= maxX; x++) {
            fillGridCell(grid, x, startY, roadColor);
        }

        for (int y = minY; y <= maxY; y++) {
            fillGridCell(grid, endX, y, roadColor);
        }
    }

    private static void fillGridCell(GridPane grid, int col, int row, Color color) {
        Node node = getNodeFromGridPane(grid, col, row);
        if (node instanceof Rectangle) {
            ((Rectangle) node).setFill(color);
        }
    }

    private static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}