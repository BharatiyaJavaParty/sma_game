package bjparty.utility;

public class ScoreBoard {
    public void updatePlayerScore(Player player, Transport transport){
        player.setCarbonFootprintBudget(player.getCarbonFootprintBudget()-transport.getCo2Emissions());
    }
}
