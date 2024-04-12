package bjp.utility;

import java.util.Objects;

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

    public void setX(int xCoordinate)
    {
        this.xOrdinate = xCoordinate; 
    }

    public void setY(int yCoordinate)
    {
        this.yOrdinate = yCoordinate;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xOrdinate == location.xOrdinate && yOrdinate == location.yOrdinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xOrdinate, yOrdinate);
    }
}
