package bjparty.controller;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MapController {
    private final MapPoint DUBLIN_CITY = new MapPoint(53.3498,-6.2603 );

    @FXML
    private VBox address;

    public void initialize(){
        MapView mapView = createMapView();
        address.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
    }

    private MapView createMapView(){
        MapView mapView = new MapView();
        mapView.setPrefSize(500, 400);
        mapView.setZoom(13);
        mapView.addLayer(new CustomMapLayer());
        mapView.flyTo(0, DUBLIN_CITY, 0.1);
        return mapView;
    }

    public class CustomMapLayer extends MapLayer{
        private final Node marker;

        public CustomMapLayer() {
            marker = new Circle(5, Color.RED);
            getChildren().add(marker);
        }

        @Override
        protected void layoutLayer(){
            Point2D point = getMapPoint(DUBLIN_CITY.getLatitude(), DUBLIN_CITY.getLongitude());
            marker.setTranslateX(point.getX());
            marker.setTranslateY(point.getY());
        }
    }
}
