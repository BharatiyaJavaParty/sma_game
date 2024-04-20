package bjp.utility;
import bjp.controller.CityMapController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.Random;

public class Gem {
    private Location gemLocation;
    private ImageView imageView;
    private static final Image redGem = new Image(Gem.class.getResourceAsStream("/img/red_gem.png"));
    private static final Image orangeGem= new Image(Gem.class.getResourceAsStream("/img/orange_gem.png"));
    private static final Image greenGem = new Image(Gem.class.getResourceAsStream("/img/green_gem.png"));


    public Gem(String color){
        gemLocation = new Location("Gem", -1, -1);
        switch (color) {
            case "red":
                this.imageView = new ImageView(redGem);
                break;
            case "orange":
                this.imageView = new ImageView(orangeGem); // Assuming you have a greenGem image
                break;
            case "green":
                this.imageView = new ImageView(greenGem);  // Assuming you have a blueGem image
                break;
        }
        
        this.imageView.setFitWidth(CityMapController.WIDTH);
        this.imageView.setFitHeight(CityMapController.HEIGHT);
        this.imageView.setSmooth(true);
    }

    public Location getGemLocation()
    {
        return gemLocation;
    }

    public void setGemLocation(Location newLocation)
    {
        this.gemLocation = newLocation;
    }

    public void placeGem(StackPane cityMainStack, GridPane cityMapGrid) {
        Random random = new Random();
        this.gemLocation = new Location("Gem Location",random.nextInt(CityMapController.COLS) , random.nextInt(CityMapController.ROWS));
        this.setGemLocation(this.gemLocation);
        cityMapGrid.getChildren().removeIf(node -> node == this.imageView);
        cityMapGrid.add(this.imageView, this.gemLocation.getX(), this.gemLocation.getY());
    }
}
