package bjp.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StaticTransportConfig {
    private static final Set<Location> LUAS_STOPS_SET = new HashSet<>();
    private static final Set<Location> BUS1_STOPS_SET = new HashSet<>();
    private static final Set<Location> BUS2_STOPS_SET = new HashSet<>();

    //creating hashmaps which contain information about how stops of different transport options like bus, luas etc connect 
    //to each other

    public static final HashMap<Location, Location> LUAS_STOPS = new HashMap<Location, Location>() {{
        put(new Location("Phibsborough", 3, 3), new Location("Parnell", 9, 14));
        put(new Location("Parnell", 9, 14), new Location("Abbey Street", 14, 15));
        put(new Location("Abbey Street", 14, 15), new Location("Trinity", 20, 16));
        put(new Location("Trinity", 20, 16), new Location("Dawson", 25, 17));
        put(new Location("Dawson", 25, 17), new Location("UCD", 27, 24));
        put(new Location("UCD", 27, 24), new Location("UCD", 27, 24));
    }};

    public static final HashMap<Location, Location> BUS1_STOPS = new HashMap<Location, Location>(){{
        put(new Location("Croke Park", 5, 20),new Location("Mayor Square", 10, 21));
        put(new Location("Mayor Square", 10, 21),new Location("Ballsbridge", 17, 17));
        put(new Location("Ballsbridge", 17, 17),new Location("Donnybrook", 25, 20));
        put(new Location("Donnybrook", 25, 20),new Location("Donnybrook", 25, 20));
    }};

    public static final HashMap<Location, Location> BUS2_STOPS = new HashMap<Location, Location>() {{
        put( new Location("UCD", 28, 25), new Location("Miltown", 27, 17));
        put(new Location("Miltown", 27, 17), new Location("Rathmines", 29, 9));
        put(new Location("Rathmines", 29, 9), new Location("Rialto", 16, 1));
        put(new Location("Rialto", 16, 1), new Location("Rialto", 16, 1));
    }}; 

    public static final Luas LUAS = new Luas("Luas", 75.0, 50.0, LUAS_STOPS);
    public static final Bus BUS1 = new Bus("Bus1", 35.0, 20.0, BUS1_STOPS);
    public static final Bus BUS2 = new Bus("Bus2", 35.0, 20.0, BUS2_STOPS);

    static {
        addAllStops(LUAS_STOPS, LUAS_STOPS_SET);
        addAllStops(BUS1_STOPS, BUS1_STOPS_SET);
        addAllStops(BUS2_STOPS, BUS2_STOPS_SET);
    }

    private static void addAllStops(Map<Location, Location> stopsMap, Set<Location> stopsSet) {
        stopsSet.addAll(stopsMap.keySet());
        stopsSet.addAll(stopsMap.values());
    }
    
    public static boolean isPlayerAtLuasStop(int playerX, int playerY) {
        return LUAS_STOPS_SET.contains(new Location("Temporary", playerX, playerY));
    }

    public static boolean isPlayerAtBus1Stop(int playerX, int playerY) {
        return BUS1_STOPS_SET.contains(new Location("Temporary", playerX, playerY));
    }

    public static boolean isPlayerAtBus2Stop(int playerX, int playerY) {
        return BUS2_STOPS_SET.contains(new Location("Temporary", playerX, playerY));
    }
}
