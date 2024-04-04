//just areference file

package bjparty;

import bjparty.utility.Bike;
import bjparty.utility.Bus;
import bjparty.utility.Location;
import bjparty.utility.Luas;
import bjparty.utility.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TransportConfig {
    /*
    variables storing the class objects of each transportation mode
    yes luas has more carbon emission than bus
    */
    public static final Transport LUAS = new Luas();
    public static final Transport BUS = new Bus();
    public static final Transport BIKE = new Bike();

}
