package bjp.utility;

import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

import bjp.controller.CityMapController;

public class Gem {
    public static int gemX;
    public static int gemY;
    private static final Image gem = new Image(Gem.class.getResourceAsStream("/img/gem.png"));
    public static void placeGem(GridPane cityMapGrid ) {
        Random random = new Random();
        gemX  = random.nextInt(CityMapController.COLS);
        gemY = random.nextInt(CityMapController.ROWS);

        ImageView imageView = new ImageView(gem);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setSmooth(true);

        cityMapGrid.add(imageView, gemX, gemY);
    }
}
