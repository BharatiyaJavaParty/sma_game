//just areference file
package bjparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AvailableTransports {
    
    //This map will store array of available transport modes at each node of the map/ graph
    //location string to be later replaced with location class objects

    public static final Map<String, ArrayList<String>> availableTransport = new HashMap<>(){{
        put("Location1", new ArrayList<>() {{add("BIKE"); add("LUAS"); add("BUS");}});
        put("Location2", new ArrayList<>() {{add("BIKE"); add("BUS");}});
        put("Location3", new ArrayList<>() {{add("BIKE"); add("LUAS");}});
        put("Location4", new ArrayList<>() {{add("BIKE");}});
        put("Location5", new ArrayList<>() {{add("BIKE"); add("LUAS"); add("BUS");}});
    }};
}
