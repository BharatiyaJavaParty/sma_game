package bjp.utility;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class EnvironmentalPopup {
    private static String popupMessage;
    private static int duration = 10;

    private static final String[] eduPopupStrings = {
            "Whenever possible, choose walking or cycling for short distances. It's a zero-emission mode of transportation that also promotes physical activity.",
            "Public transportation, such as buses, trains, and trams, emits lower emissions per passenger compared to individual car trips. Utilize public transport whenever feasible.",
            "Consider working from home if your job allows it. Telecommuting reduces the need for daily commuting, thereby lowering carbon emissions from transportation.",
            "Plan your trips efficiently to minimize distance and time spent on the road. Combine errands into a single trip to reduce fuel consumption and emissions.",
            "Spread awareness about the environmental impact of transportation and encourage others to adopt eco-friendly transportation habits." };

    public static String selectMsg() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(eduPopupStrings.length);

        String message = eduPopupStrings[randomIndex];
        return message;
    }

    public static void showEduPopup(StackPane cityMainStack, String message) {
        Label messageLabel = new Label("");
        messageLabel.setFont(new Font("Arial", 16));
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setWrapText(true);
        // keeping the pop up style same as player popup
        messageLabel.setStyle(
                "-fx-background-color: lightblue; " +
                        "-fx-background-radius: 10; " +
                        "-fx-border-color: navy; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 10; " +
                        "-fx-padding: 10;" +
                        "-fx-text-fill: black;");

        // Remove previous message labels if they exist
        cityMainStack.getChildren().removeIf(node -> node instanceof Label);

        cityMainStack.getChildren().add(messageLabel);
        StackPane.setAlignment(messageLabel, Pos.BOTTOM_CENTER);

        final int[] charIndex = { 0 };
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(50), event -> {
            if (charIndex[0] < message.length()) {
                messageLabel.setText(messageLabel.getText() + message.charAt(charIndex[0]));
                charIndex[0]++;
            }
        }));
        timeline.setCycleCount(message.length());
        timeline.setOnFinished(event -> {
            // Hide the message after 10 seconds
            PauseTransition delay = new PauseTransition(javafx.util.Duration.seconds(duration));
            delay.setOnFinished(delayEvent -> cityMainStack.getChildren().remove(messageLabel));
            delay.play();
        });
        timeline.play();
    }

}
