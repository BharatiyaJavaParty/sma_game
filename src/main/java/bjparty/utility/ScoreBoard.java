package bjparty.utility;

public class ScoreBoard {

    public void UpdatePlayerCarbonFootprint(Player player, Transport transport){
        player.setCarbonFootprintBudget(player.getCarbonFootprintBudget()-transport.getCo2Emissions());
    }

    public void StorePlayerScore()
    {
        //stores the player score in a separate file
    }
}
