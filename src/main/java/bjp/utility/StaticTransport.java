package bjp.utility;

import java.util.HashMap;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Pair;

//abstract class which serves as parent for Bus, Luas, Dart etc
public abstract class StaticTransport {
    private String transportName;
    private double co2Emissions;
    private long timeTakenPerGrid;
    private HashMap<Location,Pair<Location, Location>> stops;
    
    public StaticTransport(String transportName, double co2Emissions, long timePerGrid, HashMap<Location,Pair<Location, Location>> stops) {
        this.transportName = transportName;
        this.co2Emissions = co2Emissions;
        this.timeTakenPerGrid = timePerGrid;
        this.stops = stops;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public long getTimeTakenPerGrid() {
        return timeTakenPerGrid;
    }

    public void setTimeTakenPerGrid(long timeTaken) {
        this.timeTakenPerGrid = timeTaken;
    }

    public abstract void drawRoad(Location location1, Location location2, GridPane grid);

    public abstract void fillGridCell(GridPane grid, int col, int row, Color color);

    public abstract void fillGridCellWithImage(GridPane grid, int col, int row, Image roadImage);

    public abstract Node getNodeFromGridPane(GridPane gridPane, int col, int row);

    public abstract int getManhattanDistance(Location l1, Location l2);
}
