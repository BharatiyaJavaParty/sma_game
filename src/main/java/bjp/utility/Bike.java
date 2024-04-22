package bjp.utility;

public class Bike extends DynamicTransport {

    public Bike(String transportName, double co2Emissions, double speed) {
        super(transportName, speed, speed);
    }

    @Override
    public void getCurrentGridNode() {
        // TODO: get player location to check whether this node lies on the luas or bus
        // stops or not
    }

    @Override
    public int getManhattanDistance(Location l1, Location l2) {
        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
    }

}
