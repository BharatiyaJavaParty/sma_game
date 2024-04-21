module bjp{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires json.simple; 


    opens bjp.controller to javafx.fxml;
    exports bjp;
}
