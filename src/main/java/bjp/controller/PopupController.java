package bjp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class PopupController {

    public static void show_popup_message(StackPane cityMainStack, String message) {
        Label messageLabel = new Label("");
        messageLabel.setFont(new Font("Arial", 16)); // Example font and size
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setWrapText(true);
        messageLabel.setStyle(
                "-fx-background-color: lightblue; " +
                        "-fx-background-radius: 10; " +  // Rounded corners
                        "-fx-border-color: navy; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 10; " +
                        "-fx-padding: 10;" +  // Padding inside the label
                        "-fx-text-fill: black;");  // Text color

        // Remove previous message labels if they exist
        cityMainStack.getChildren().removeIf(node -> node instanceof Label);

        // Add the message label to the StackPane, overlaying the GridPane
        cityMainStack.getChildren().add(messageLabel);
        StackPane.setAlignment(messageLabel, Pos.TOP_CENTER); // Align the label at the top center of the StackPane

        final int[] charIndex = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            if (charIndex[0] < message.length()) {
                messageLabel.setText(messageLabel.getText() + message.charAt(charIndex[0]));
                charIndex[0]++;
            }
        }));
        timeline.setCycleCount(message.length());
        timeline.setOnFinished(event -> {
            // Hide the message after 2 seconds
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(delayEvent -> cityMainStack.getChildren().remove(messageLabel));
            delay.play();
        });
        timeline.play();
    }
}
