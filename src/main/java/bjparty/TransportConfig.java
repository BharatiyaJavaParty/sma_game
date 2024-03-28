//just areference file

package bjparty;

import bjparty.utility.Location;
import bjparty.utility.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TransportConfig {
    /*
    map storing the values of carbon emission by each transportation mode
    yes luas has more carbon emission than bus
    */
//    public static final Map<String, Integer> CARBON_FOOTPRINT = new HashMap<>(){{
//        put("LUAS",75);
//        put("BUS", 35);
//        put("BIKE", 5);
//    }};
//
//    //travel speeds for each transportation mode
//    public static final Map<String, Integer> TRANSPORT_SPEED = new HashMap<>(){{
//        put("LUAS", 40);
//        put("BUS", 20);
//        put("BIKE", 10);
//    }};

    public static final Transport LUAS = new Transport("Luas", 75, 40);
    public static final Transport BUS = new Transport("Bus", 35, 20);
    public static final Transport BIKE = new Transport("Bike", 5, 10);

}
