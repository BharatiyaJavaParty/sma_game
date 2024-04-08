module bjp{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens bjp.controller to javafx.fxml;
    exports bjp;
}
