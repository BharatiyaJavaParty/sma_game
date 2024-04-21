package bjp.utility;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import bjp.controller.CityMapController;

public class Luas extends StaticTransport {

    // Assuming you have an image at "src/main/resources/bjp/img/roadTexture.jpg"
    private static Image trackHori = new Image(Luas.class.getResourceAsStream("/img/train-hori-green.png"));
    private static Image trackVerti = new Image(Luas.class.getResourceAsStream("/img/train-verti-green.png"));
    private static Image luasStop = new Image(Luas.class.getResourceAsStream("/img/luas-stop.png"));
    private static Image trackJoin = new Image(Luas.class.getResourceAsStream("/img/train-join-green.png"));

    public Luas(String transportName, double co2Emissions, long timeTaken,
            HashMap<Location, Pair<Location, Location>> stops) {
        super(transportName, co2Emissions, timeTaken, stops);
    }

    public static void makeLuasLane_green(Luas luas, HashMap<Location, Pair<Location, Location>> luasStops,
            GridPane grid) {
        for (Location start : luasStops.keySet()) {
            Location end = luasStops.get(start).getValue();
            luas.drawRoad(start, end, grid);
        }

        for (Location stop : luasStops.keySet()) {
            luas.fillGridCellWithImage(grid, stop.getX(), stop.getY(), luasStop);
        }
    }

    public static void makeLuasLane_red(Luas luas, HashMap<Location, Pair<Location, Location>> luasStops, GridPane grid) 
        {
        trackHori = new Image(Luas.class.getResourceAsStream("/img/train-hori-red.png"));
        trackVerti = new Image(Luas.class.getResourceAsStream("/img/train-verti-red.png"));
        trackJoin = new Image(Luas.class.getResourceAsStream("/img/train-join-red.png"));
        for (Location start : luasStops.keySet()) {
            Location end = luasStops.get(start).getValue();
            luas.drawRoad(start, end, grid);
        }

        for (Location stop : luasStops.keySet()) {
            luas.fillGridCellWithImage(grid, stop.getX(), stop.getY(), luasStop);
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

        for (int x = minX + 1; x <= maxX; x++) {
            if (x == maxX) {
                fillGridCellWithImage(grid, x, startY, trackJoin);
            } else {
                fillGridCellWithImage(grid, x, startY, trackHori);
            }
        }

        for (int y = minY + 1; y <= maxY; y++) {
            if (y == maxY) {
                fillGridCellWithImage(grid, endX, y, trackJoin);
            } else {
                fillGridCellWithImage(grid, endX, y, trackVerti);
            }
        }
    }

    @Override
    public void fillGridCellWithImage(GridPane grid, int col, int row, Image roadImage) {
        Node node = getNodeFromGridPane(grid, col, row);
        if (node instanceof ImageView) {
            ((ImageView) node).setImage(roadImage);
        } else {
            ImageView imageView = new ImageView(roadImage);
            imageView.setFitWidth(CityMapController.WIDTH);
            imageView.setFitHeight(CityMapController.HEIGHT);
            imageView.setSmooth(true);
            grid.add(imageView, col, row);
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

    @Override
    public int getManhattanDistance(Location l1, Location l2) {
        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
    }
}

// psyren