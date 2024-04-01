//just areference file
package bjparty;

import bjparty.utility.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocationsConfig {
    
    //This map will store array of available transport modes at each node of the map/ graph
    //location string to be later replaced with location class objects

//    public static final Map<String, ArrayList<String>> availableTransport = new HashMap<>(){{
//        put("Location1", new ArrayList<>() {{add("BIKE"); add("LUAS"); add("BUS");}});
//        put("Location2", new ArrayList<>() {{add("BIKE"); add("BUS");}});
//        put("Location3", new ArrayList<>() {{add("BIKE"); add("LUAS");}});
//        put("Location4", new ArrayList<>() {{add("BIKE");}});
//        put("Location5", new ArrayList<>() {{add("BIKE"); add("LUAS"); add("BUS");}});
//    }};
        public static final Location SMITHFIELD = new Location("Smithfield",
                53.3496,
                -6.2782,
        new ArrayList<>(){{
            add(TransportConfig.LUAS);
            add(TransportConfig.BUS);
            add(TransportConfig.BIKE);
        }});
    public static final Location TALLAGHT = new Location("Tallaght",
                53.2889,
                -6.3572,
            new ArrayList<>(){{
                add(TransportConfig.LUAS);
            }});
    public static final Location POINT = new Location("Point",
                53.2889,
                -6.3572,
            new ArrayList<>(){{
                add(TransportConfig.LUAS);
                add(TransportConfig.BIKE);
            }});
    public static final Location SANDYFORD = new Location("Sandyford",
                53.2790,
                -6.2163,
            new ArrayList<>(){{
                add(TransportConfig.BUS);
                add(TransportConfig.BIKE);
            }}
            );
    public static final Location UCD = new Location("Ucd",
                53.3097,
                -6.2216,
            new ArrayList<>(){{
                add(TransportConfig.BUS);
                add(TransportConfig.BIKE);
            }});

    //this map basically tells which location is connected to what other locations
    public static final Map<Location, ArrayList<Location>> CITY_GRAPH = new HashMap<>(){{
        put(TALLAGHT, new ArrayList<>(){{
            add(SMITHFIELD);
        }});
        put(SMITHFIELD, new ArrayList<>(){{
            add(TALLAGHT);
            add(POINT);
            add(UCD);
            add(SANDYFORD);
        }});
        put(POINT, new ArrayList<>(){{
            add(SMITHFIELD);
            add(UCD);
        }});
        put(UCD, new ArrayList<>(){{
            add(SMITHFIELD);
            add(SANDYFORD);
            add(POINT);
        }});
        put(SANDYFORD, new ArrayList<>(){{
            add(UCD);
            add(SMITHFIELD);
        }});
    }};

}
