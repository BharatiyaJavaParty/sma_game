module bjp{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    // requires com.lynden.GMapsFX;  // Ensure this is the correct module name


    opens bjp.controller to javafx.fxml;
    exports bjp;
}
