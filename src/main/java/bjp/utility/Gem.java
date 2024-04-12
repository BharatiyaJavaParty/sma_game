package bjp.utility;
import bjp.controller.CityMapController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.Random;

public class Gem {
    public static Location gemLocation;
    public static ImageView imageView;
    private static final Image gem = new Image(Gem.class.getResourceAsStream("/img/gem.png"));

    public static void placeGem(StackPane cityMainStack, GridPane cityMapGrid ) {
        Random random = new Random();
        gemLocation.setX(random.nextInt(CityMapController.COLS)) ;
        gemLocation.setY(random.nextInt(CityMapController.ROWS));
        cityMapGrid.getChildren().removeIf(node -> node == imageView);

        imageView = new ImageView(gem);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setSmooth(true);

        cityMapGrid.add(imageView, gemLocation.getX(), gemLocation.getY());
    }
}
