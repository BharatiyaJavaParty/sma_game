package bjparty.utility;

import java.util.ArrayList;

public class Location {
    private String locationName;
    private Double locationLat;
    private Double locationLng;
    private ArrayList<Transport> transportOptionList;

    public ArrayList<Transport> getTransportOptionList() {
        return transportOptionList;
    }

    public void setTransportOptionList(ArrayList<Transport> transportOptionList) {
        this.transportOptionList = transportOptionList;
    }

    public Location(String locationName, Double locationLat, Double locationLng, ArrayList<Transport> transportOptionList) {
        this.locationName = locationName;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.transportOptionList = transportOptionList;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(Double locationLng) {
        this.locationLng = locationLng;
    }
}
