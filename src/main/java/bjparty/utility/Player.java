package bjparty.utility;


public class Player {
    //we are not maintaining score and level as they will be variables which will be maintained as common variables for the game
    private String playerName;
    private int carbonFootprintBudget;
    private Location currentLocation;

    public Player(String name, int carbonBudget, Location spawningLocation)
    {
        this.playerName = name;
        this.carbonFootprintBudget = carbonBudget;
        this.currentLocation = spawningLocation;
    }

    //getters and setters
    public String getPlayerName() {
        return playerName;
    }

    public int getCarbonFootprintBudget() {
        return carbonFootprintBudget;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCarbonFootprintBudget(int carbonFootprintBudget) {
        this.carbonFootprintBudget = carbonFootprintBudget;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
