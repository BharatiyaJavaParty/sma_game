package bjparty.utility;
import bjparty.utility.Location;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Bus extends StaticTransport{

    public Bus(String transportName, double co2Emissions, double speed, HashMap<Location, Location> stops) {
        super(transportName, co2Emissions, speed, stops);
    }

    public static void makeBusRoad(Bus bus, HashMap<Location, Location> busStops, GridPane grid) {
        for (Location start : busStops.keySet()) {
            Location end = busStops.get(start);
            bus.drawRoad(start, end, grid);
        }

        for (Location stop : busStops.keySet()) {
            bus.fillGridCell(grid, stop.getX(), stop.getY(), Color.BLACK);
        }
    }

    @Override
    public void drawRoad(Location start, Location end, GridPane grid) {
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

    @Override
    public void fillGridCell(GridPane grid, int row, int col, Color color) {
        Node node = getNodeFromGridPane(grid, col, row);
        if (node instanceof Rectangle) {
            ((Rectangle) node).setFill(color);
        }
    }

    @Override
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
