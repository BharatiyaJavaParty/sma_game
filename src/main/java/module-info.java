module bjparty {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens bjparty.controller to javafx.fxml;
    exports bjparty;
}
