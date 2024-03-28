//just areference file

package bjparty;

import java.util.HashMap;
import java.util.Map;


public class TravelConstants {
    /*
    map storing the values of carbon emission by each transportation mode
    yes luas has more carbon emission than bus
    */
    public static final Map<String, Integer> CARBON_FOOTPRINT = new HashMap<>(){{
        put("LUAS",75);
        put("BUS", 35);
        put("BIKE", 5);
    }};

    //travel speeds for each transportation mode
    public static final Map<String, Integer> TRANSPORT_SPEED = new HashMap<>(){{
        put("LUAS", 40);
        put("BUS", 20);
        put("BIKE", 10);
    }};
}
