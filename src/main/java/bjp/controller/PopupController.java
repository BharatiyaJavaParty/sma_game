package bjp.controller;

import java.util.Random;

import bjp.Main;
import bjp.utility.GameEngine;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class PopupController {

    private static final String[] busPopupStrings = {
        "Did you know? Some buses run on leftover cooking oil,\n turning yesterday's fries into today's eco-friendly ride!",
        "Ever heard of buses running on hydrogen? They emit only water vapor, \n making them the superheroes of clean public transit!",
        "London's iconic double-decker buses are not only a symbol of the city\n but also serve as emergency housing during disasters.",
        "In Japan, some buses are equipped with sensors that detect tired drivers.",
        "There are buses out there fueled by compressed natural gas,\n offering a greener alternative to traditional diesel vehicles!"
    };

    private static final String[] luasPopupStrings = {
        "Did you know? The name Luas comes from the Irish word for speed or swiftness.",
        "Did you know? Over 100,000 people use the Luas each day in Dublin.",
        "Trams operate quietly, hence reducing noise pollution.",
        "Tram tracks often feature vegetation alongside them, providing green corridors through urban areas.",
        "Did you know? New Orleans has the oldest tram system in the world starting back at 1835!"
    };

    public static void showPopupMessage(StackPane cityMainStack, String message) {
    Label messageLabel = new Label(message);
    messageLabel.setFont(new Font("Arial", 16));
    messageLabel.setAlignment(Pos.CENTER);
    messageLabel.setWrapText(true);
    messageLabel.setStyle(
        "-fx-background-color: rgba(255, 105, 97, 0.7);" +
        "-fx-background-radius: 15;" +
        "-fx-border-color: rgba(178, 34, 34, 0.7);" +
        "-fx-border-width: 3;" +
        "-fx-border-radius: 14;" +
        "-fx-padding: 10;" +
        "-fx-font-weight: bold;" +
        "-fx-font-size: 14;" +
        "-fx-text-fill: #7c0a02;" +
        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"
    );

    cityMainStack.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().contains("Player"));
    cityMainStack.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().contains("Gems"));

    cityMainStack.getChildren().add(messageLabel);
    StackPane.setAlignment(messageLabel, Pos.TOP_CENTER);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(delayEvent -> cityMainStack.getChildren().remove(messageLabel));
        delay.play();
    }

    public static void ShowPopup(StackPane cityMainStack, String message)
    {
        Polygon polygon = new Polygon();
        // polygon.setStyle(
        //     "-fx-background-color: rgba(255, 105, 97, 0.7);" +
        //     "-fx-background-radius: 15;" +
        //     "-fx-border-color: rgba(178, 34, 34, 0.7);" +
        //     "-fx-border-width: 3;" +
        //     "-fx-border-radius: 14;" +
        //     "-fx-padding: 10;" +
        //     "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"
        // );
        polygon.setOpacity(0.5);
        polygon.setFill(javafx.scene.paint.Color.WHITE);
        polygon.setStroke(javafx.scene.paint.Color.BLACK);
        Text popupTxt = new Text();

        Random rand = new Random();
        int  n = rand.nextInt(5);
        if((GameEngine.atBus1 || GameEngine.atBus2 || GameEngine.atBus3) && message=="")
        {
            popupTxt.setText(busPopupStrings[n]);
            popupTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        }
        else if((GameEngine.atLuas || GameEngine.atRedLuas) && message=="")
        {
            popupTxt.setText(luasPopupStrings[n]);
            popupTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        }
        else if (message!="")
        {
            popupTxt.setText(message);
            popupTxt.setFill(javafx.scene.paint.Color.RED);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), popupTxt);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(5);
            fadeTransition.play();
            popupTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        }

        double textWidth = popupTxt.getLayoutBounds().getWidth();
        double textHeight = popupTxt.getLayoutBounds().getHeight();

        Path path = new Path();
        path.getElements().add(new MoveTo(0, popupTxt.getLayoutBounds().getMaxY()));

        polygon.setLayoutX(- textWidth); 
        polygon.setLayoutY(- textHeight); 

        popupTxt.setLayoutX(0 - textWidth); 
        popupTxt.setLayoutY(0- textHeight);

        if(message == "")
        {
            polygon.getPoints().addAll(new Double[] {
                -20.0, -textHeight,
                textWidth + 20, -textHeight,
                textWidth + 10, 0.0,
                textWidth + 20, textHeight,
                -20.0, textHeight,
                -10.0, 0.0
              });
    
            Group group = new Group();
            group.getChildren().addAll(polygon, popupTxt);
            cityMainStack.getChildren().add(group);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> cityMainStack.getChildren().remove(group));
            pause.play();
        }
        else
        {
            cityMainStack.getChildren().add(popupTxt);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.play();
        }
    }
}