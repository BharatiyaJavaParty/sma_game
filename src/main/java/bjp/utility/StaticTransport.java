package bjp.utility;

import java.util.HashMap;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.util.Pair;

//abstract class which serves as parent for Bus, Luas, Dart etc
public abstract class StaticTransport {
    private String transportName;
    private double co2Emissions;
    private double speed;
    private HashMap<Location,Pair<Location, Location>> stops;
    
    public StaticTransport(String transportName, double co2Emissions, double speed, HashMap<Location,Pair<Location, Location>> stops) {
        this.transportName = transportName;
        this.co2Emissions = co2Emissions;
        this.speed = speed;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public abstract void drawRoad(Location location1, Location location2, GridPane grid);

    public abstract void fillGridCell(GridPane grid, int col, int row, Color color);

    public abstract Node getNodeFromGridPane(GridPane gridPane, int col, int row);
}
