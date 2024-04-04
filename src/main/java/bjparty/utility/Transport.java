package bjparty.utility;

import java.util.ArrayList;
import java.util.Map;

public abstract class Transport
{
    private String transportName;
    private Integer co2Emissions;
    private Integer transportSpeed;


    public Transport(String transportName, Integer co2Emissions, Integer transportSpeed) {
        this.transportName = transportName;
        this.co2Emissions = co2Emissions;
        this.transportSpeed = transportSpeed;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public Integer getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(Integer co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public Integer getTransportSpeed() {
        return transportSpeed;
    }

    public void setTransportSpeed(Integer transportSpeed) {
        this.transportSpeed = transportSpeed;
    }
}
