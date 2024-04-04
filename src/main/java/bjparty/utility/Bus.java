package bjparty.utility;
import bjparty.utility.Location;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Bus {
    public static Double speed = 20.0;

    public static final Location Bus1_Stop1 = new Location("Croke Park", 5, 20);
    public static final Location Bus1_Stop2 = new Location("Mayor Square", 10, 21);
    public static final Location Bus1_Stop3 = new Location("Ballsbridge", 17, 17);
    public static final Location Bus1_Stop4 = new Location("Donnybrook", 25, 20);

    public static final HashMap<Location, Location> Bus1 = new HashMap<Location, Location>() {{
        put(Bus1_Stop1, Bus1_Stop2);
        put(Bus1_Stop2, Bus1_Stop3);
        put(Bus1_Stop3, Bus1_Stop4);
        put(Bus1_Stop4, Bus1_Stop4);
    }};

    public static final Location Bus2_Stop1 = new Location("UCD", 28, 25);
    public static final Location Bus2_Stop2 = new Location("Miltown", 27, 17);
    public static final Location Bus2_Stop3 = new Location("Rathmines", 29, 9);
    public static final Location Bus2_Stop4 = new Location("Rialto", 16, 1);

    public static final HashMap<Location, Location> Bus2 = new HashMap<Location, Location>() {{
        put(Bus2_Stop1, Bus2_Stop2);
        put(Bus2_Stop2, Bus2_Stop3);
        put(Bus2_Stop3, Bus2_Stop4);
        put(Bus2_Stop4, Bus2_Stop4);
    }};

    public static void makeBusRoad(GridPane grid, HashMap<Location, Location> Bus) {
        for (Location start : Bus.keySet()) {
            Location end = Bus.get(start);
            drawRoad(start, end, grid);
        }

        for (Location stop : Bus.keySet()) {
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

        Color roadColor = Color.YELLOW;

        for (int x = minX; x <= maxX; x++) {
            fillGridCell(grid, x, startY, roadColor);
        }

        for (int y = minY; y <= maxY; y++) {
            fillGridCell(grid, endX, y, roadColor);
        }
    }

    private static void fillGridCell(GridPane grid, int row, int col, Color color) {
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
