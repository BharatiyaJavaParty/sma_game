package bjp.utility;
import java.util.HashMap;

import bjp.utility.Location;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Luas extends StaticTransport{
    
    public Luas(String transportName, double co2Emissions, double speed, HashMap<Location, Location> stops) {
        super(transportName, co2Emissions, speed, stops);
    }

    public static void makeLuasLane(Luas luas, HashMap<Location,Location> luasStops, GridPane grid) {
        for (Location start : luasStops.keySet()) {
            Location end = luasStops.get(start);
            luas.drawRoad(start, end, grid);
        }

        for (Location stop : luasStops.keySet()) {
            luas.fillGridCell(grid, stop.getX(), stop.getY(), Color.BLACK);
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

        Color roadColor = Color.GREEN;

        for (int x = minX; x <= maxX; x++) {
            fillGridCell(grid, x, startY, roadColor);
        }

        for (int y = minY; y <= maxY; y++) {
            fillGridCell(grid, endX, y, roadColor);
        }
    }

    @Override
    public void fillGridCell(GridPane grid, int col, int row, Color color) {
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