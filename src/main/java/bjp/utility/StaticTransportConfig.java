package bjp.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

public class StaticTransportConfig {
    private static final Set<Location> LUAS_STOPS_SET = new HashSet<>();
    private static final Set<Location> BUS1_STOPS_SET = new HashSet<>();
    private static final Set<Location> BUS2_STOPS_SET = new HashSet<>();

    //creating hashmaps which contain information about how stops of different transport options like bus, luas etc connect 
    //to each other


    public static final Location Bus1_Stop1 = new Location("Croke Park", 5, 5);
    public static final Location Bus1_Stop2 = new Location("Mayor Square", 14, 7);
    public static final Location Bus1_Stop3 = new Location("Ballsbridge", 20, 5);
    public static final Location Bus1_Stop4 = new Location("Donnybrook", 30, 2);
    public static final Location Bus1_Stop5 = new Location("Stillorgan", 35, 0);

    public static final Location Bus2_Stop1 = new Location("UCD", 34, 8);
    public static final Location Bus2_Stop2 = new Location("Miltown", 33, 15);
    public static final Location Bus2_Stop3 = new Location("Rathmines", 27, 9);
    public static final Location Bus2_Stop4 = new Location("Rialto", 18, 1);
    public static final Location Bus2_Stop5 = new Location("Spire", 14, 2);

    public static final Location Luas_stop1 = new Location("Phibsborough", 3, 3);
    public static final Location Luas_stop2 = new Location("Parnell", 7, 14);
    public static final Location Luas_stop3 = new Location("Abbey Street", 10, 15);
    public static final Location Luas_stop4 = new Location("Trinity", 14, 15);
    public static final Location Luas_stop5 = new Location("Dawson", 21, 16);
    public static final Location Luas_stop6 = new Location("UCD", 25, 18);
    public static final Location Luas_stop7 = new Location("TCD", 34, 19);

// this hashmap contains a location and pair of its previousLocation and nextLocation
//getKey gives previous station 
//getValue gives next station
    public static final HashMap<Location, Pair<Location,Location>> LUAS_STOPS = new HashMap<Location, Pair<Location,Location>>() {{
        put(Luas_stop1,new Pair<Location,Location>(Luas_stop1, Luas_stop2));
        put(Luas_stop2, new Pair<Location,Location>(Luas_stop1, Luas_stop3));
        put(Luas_stop3, new Pair<Location,Location>(Luas_stop2, Luas_stop4));
        put(Luas_stop4, new Pair<Location,Location>(Luas_stop3, Luas_stop5));
        put(Luas_stop5, new Pair<Location,Location>(Luas_stop4, Luas_stop6));
        put(Luas_stop6, new Pair<Location,Location>(Luas_stop5, Luas_stop7));
        put(Luas_stop7, new Pair<Location,Location>(Luas_stop6, Luas_stop7));
    }};

    public static final HashMap<Location, Pair<Location,Location>> BUS1_STOPS = new HashMap<Location, Pair<Location,Location>>(){{
        put(Bus1_Stop1,new Pair<Location,Location>(Bus1_Stop1, Bus1_Stop2));
        put(Bus1_Stop2,new Pair<Location,Location>(Bus1_Stop1, Bus1_Stop3));
        put(Bus1_Stop3,new Pair<Location,Location>(Bus1_Stop2, Bus1_Stop4));
        //Joel Added this code
        put(Bus1_Stop4,new Pair<Location,Location>(Bus1_Stop3, Bus1_Stop5));
        put(Bus1_Stop5,new Pair<Location,Location>(Bus1_Stop4, Bus1_Stop5));
    }};

    public static final HashMap<Location, Pair<Location,Location>> BUS2_STOPS = new HashMap<Location, Pair<Location,Location>>() {{
        put(Bus2_Stop1, new Pair<Location,Location>(Bus2_Stop1, Bus2_Stop2));
        put(Bus2_Stop2, new Pair<Location,Location>(Bus2_Stop1, Bus2_Stop3));
        put(Bus2_Stop3, new Pair<Location,Location>(Bus2_Stop2, Bus2_Stop4));
        put(Bus2_Stop4, new Pair<Location,Location>(Bus2_Stop3, Bus2_Stop5));
        put(Bus2_Stop5, new Pair<Location,Location>(Bus2_Stop3, Bus2_Stop5));
    }}; 

    public static final Luas LUAS = new Luas("Luas", 75.0, 10, LUAS_STOPS);
    public static final Bus BUS1 = new Bus("Bus1", 35.0, 20, BUS1_STOPS);
    public static final Bus BUS2 = new Bus("Bus2", 35.0, 20, BUS2_STOPS);

    static {
        addAllStops(LUAS_STOPS, LUAS_STOPS_SET);
        addAllStops(BUS1_STOPS, BUS1_STOPS_SET);
        addAllStops(BUS2_STOPS, BUS2_STOPS_SET);
    }

    private static void addAllStops(Map<Location, Pair<Location, Location>> stopsMap, Set<Location> stopsSet) {
        stopsSet.addAll(stopsMap.keySet());
        for (Pair<Location, Location> pair : stopsMap.values()) {
            stopsSet.add(pair.getKey());
            stopsSet.add(pair.getValue());
        }
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
