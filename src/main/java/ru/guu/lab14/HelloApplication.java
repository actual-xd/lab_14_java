package ru.guu.lab14;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Button button = new Button("Нажми меня");
        Circle circle = new Circle(50, Color.CORAL);

        circle.getStyleClass().add("circle");
        button.getStyleClass().add("button");

        button.setOnAction(e -> {
            animateCircle(circle);
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(circle, button);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Стилизация и анимации в JavaFX");
        stage.setScene(scene);
        stage.show();

    }

    private void animateCircle(Circle circle) {
        double radius = 1;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(5), circle);
        translate.setAutoReverse(true);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                translate.setByX(circleProcessX(radius));
                translate.setByY(circleProcessY(radius));

            }
        };


        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), circle);
        scale.setToX(1.5);
        scale.setToY(1.5);
        scale.setCycleCount(1);
        scale.setAutoReverse(true);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), circle);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);

        translate.setOnFinished(event -> resetCircle(circle));
        scale.setOnFinished(event -> resetCircle(circle));
        fade.setOnFinished(event -> resetCircle(circle));

        translate.play();
        scale.play();
        fade.play();
        timer.start();
    }

    private double circleProcessX(double r) {
        int digit = 0;
        digit++;
        return r * Math.cos(digit);
    }

    private double circleProcessY(double r) {
        int digit = 0;
        digit++;
        return r * Math.sin(digit);
    }

    private void resetCircle(Circle circle) {
        circle.setTranslateX(0);
        circle.setTranslateY(0);
        circle.setScaleX(1);
        circle.setScaleY(1);
        circle.setOpacity(1);
    }

    public static void main(String[] args) {
        launch();
    }
}