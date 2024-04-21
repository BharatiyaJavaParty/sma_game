package bjp.utility;
import bjp.controller.CityMapController;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;
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
                this.imageView = new ImageView(orangeGem);
                break;
            case "green":
                this.imageView = new ImageView(greenGem);
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
        int x, y;
        do {
            x = random.nextInt(CityMapController.COLS);
            y = random.nextInt(CityMapController.ROWS);
        } while (Obstacles.checkObstacles(x, y));

        this.gemLocation = new Location("Gem Location", x, y);
        this.setGemLocation(this.gemLocation);
        cityMapGrid.getChildren().removeIf(node -> node == this.imageView);
        cityMapGrid.add(this.imageView, this.gemLocation.getX(), this.gemLocation.getY());
        startBounceAnimation(this.imageView);
    }

    private void startBounceAnimation(ImageView imageView) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), imageView);
        transition.setFromY(0);
        transition.setToY(-20);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }
}
