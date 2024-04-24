module bjp {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.base;

    // Third-party modules
    requires json.simple; // Note: Ensure json-simple is used as a module, or this needs adjustment for automatic module name.
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    // Export your primary package which contains your Main and other user-interface related classes.
    exports bjp;

    // Opens the controller package to javafx.fxml, which is required for the FXML loader to access controllers.
    opens bjp.controller to javafx.fxml;

    // If your utility classes are used by reflection or by FXML, consider opening them too.
    opens bjp.utility to javafx.fxml;
}
