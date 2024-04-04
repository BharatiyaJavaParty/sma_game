package bjparty.utility;


public class Location {
    private String locationName;
    private int xOrdinate;
    private int yOrdinate;

    public Location(String locationName, int xOrdinate, int yOrdinate) {
        this.locationName = locationName;
        this.xOrdinate = xOrdinate;
        this.yOrdinate = yOrdinate;
    }

    public int getX() {
        return xOrdinate;
    }

    public int getY() {
        return yOrdinate;
    }
}
