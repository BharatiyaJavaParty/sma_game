package bjp.utility;

public abstract class DynamicTransport {
    private String transportName;
    private double co2Emissions;
    private double speed;
    // private Location currentLocation;
    // private int gridsTraversed;

    public DynamicTransport(String transportName, double co2Emissions, double speed) {
        this.transportName = transportName;
        this.co2Emissions = co2Emissions;
        this.speed = speed;
    }; 
    
    public abstract void getCurrentGridNode();

    public abstract int getManhattanDistance(Location l1, Location l2);

}
