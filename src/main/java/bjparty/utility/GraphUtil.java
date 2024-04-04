package bjparty.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import bjparty.LocationsConfig;
import java.util.Map;
import java.util.Queue;

public class GraphUtil {
        /*
     * @return Map<String, Location>
     * @desc This function returns a map of source and destination. To get source key is "source"
     * while to get destination is key is "destination"
     */
    public Map<String,Location> getSourceAndDestination(){
        Map<String, Location> res = new HashMap<String, Location>();
        Set<Location> location_list = LocationsConfig.CITY_GRAPH.keySet();
        int graph_size = location_list.size();
        Random random = new Random();
        Location source = ((Location) location_list.toArray()[random.nextInt(graph_size)]);
        Location destination = ((Location) location_list.toArray()[random.nextInt(graph_size)]);
        res.put("source", source);
        res.put("destination", destination);
        return res;
    }


    public void traverseGraph(Location source, Location destination){
        Map<Location, Integer> visited = new HashMap<Location, Integer>();
        LocationsConfig.CITY_GRAPH.forEach(((location, locations) -> {
            visited.put(location, 0);
        }));
        Queue<Location> q = new LinkedList<Location>();
        q.add(source);
        AtomicBoolean flag = new AtomicBoolean(true);
        while(!q.isEmpty() && flag.get()){
            Location temp = q.remove();
            System.out.print(temp.getLocationName()+"+>");
            LocationsConfig.CITY_GRAPH.get(temp).forEach((neighbour)->{
                if(neighbour.equals(destination)) {
                    System.out.println(neighbour.getLocationName());
                    flag.set(false);
                }
                if(visited.get(neighbour)==0){
                    visited.replace(neighbour, 1);
                    q.add(neighbour);
                }
            });
        }
    }

    public ArrayList<Location> getNeighbour(Location location){
        return LocationsConfig.CITY_GRAPH.get(location);
    }

    public Double GetDistance(Location location1, Location location2)
    {
        //this function should calculate coordinate diistance between two locations
        return 0.0;
    }
}
