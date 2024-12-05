package com.example.ballcrusher;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Rumesh Chthuska
 * Project 2
 * BallCrusher is a JavaFX application where the user enters the number of balls and the time
 * to beat, and then plays a game where they must click on all randomly generated circles (balls)
 * within the specified time limit. The user can choose to have all circles a default size or
 * random sizes.
 * The game displays a "You Win!" or "You Lose!" message based on the user's performance,
 * and the message will blink and change colors between red, blue, green, and yellow.
 * The game includes the following features:
 * - User input for the number of circles and time limit.
 * - Option to choose between default-sized or random-sized circles using a checkbox.
 * - Timer tracking the time taken to click all the circles.
 * - Animated "You Win!" or "You Lose!" message upon completion, with color-changing animation.
 * The game is designed for educational purposes, demonstrating basic JavaFX usage, event handling,
 * and simple animations.
 *
 */

public class BallCrusher extends Application {
    private int numberOfBalls;
    private double goalTime;
    private LocalTime startTime, endTime;
    private Label resultLabel;
    private Label messageLabel;
    private boolean useRandomSize = false; // Determines if circles should have random sizes

    @Override
    public void start(Stage primaryStage) {
        // Set up opening scene
        primaryStage.setTitle("Ball Crusher ");

        VBox openingLayout = new VBox(20); // Spacing between components
        openingLayout.setPadding(new Insets(20)); // Padding around the layout

        // Create labels with larger fonts
        Label ballLabel = new Label("How many balls?");
        ballLabel.setStyle("-fx-font-size: 16px;");
        TextField ballInput = new TextField();
        ballInput.setMaxWidth(200); // Set width for text fields

        Label timeLabel = new Label("Time to beat in seconds:");
        timeLabel.setStyle("-fx-font-size: 16px;");
        TextField timeInput = new TextField();
        timeInput.setMaxWidth(200); // Set width for text fields

        // Create a checkbox for random size option
        CheckBox randomSizeCheckBox = new CheckBox("Use Random Size for Circles");
        randomSizeCheckBox.setOnAction(event -> {
            useRandomSize = randomSizeCheckBox.isSelected(); // Update the flag when checkbox is checked/unchecked
        });

        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;"); // Set error message color to red

        // Create the Begin button and set its width to match the text fields
        Button beginButton = new Button("Begin!");
        beginButton.setMinWidth(200); // Set the button width to match the text fields

        HBox buttonBox = new HBox(beginButton);
        buttonBox.setAlignment(Pos.CENTER); // Center the button horizontally

        // Add all components to the VBox
        openingLayout.getChildren().addAll(ballLabel, ballInput, timeLabel, timeInput, randomSizeCheckBox, errorMessage, buttonBox);
        Scene openingScene = new Scene(openingLayout, 250, 300);

        // Set the "Begin" button functionality
        beginButton.setOnAction(event -> {
            String ballInputText = ballInput.getText();
            String timeInputText = timeInput.getText();
            boolean valid = true;

            // Reset the error message each time Begin is pressed
            errorMessage.setText("");

            // Validation for empty fields
            if (ballInputText.isEmpty() && timeInputText.isEmpty()) {
                errorMessage.setText("Both fields are empty!");
                valid = false;
            } else if (ballInputText.isEmpty()) {
                errorMessage.setText("Must enter number of balls.");
                valid = false;
            } else if (timeInputText.isEmpty()) {
                errorMessage.setText("Must enter number of seconds.");
                valid = false;
            }

            // If not empty, check for negative inputs
            if (valid) {
                try {
                    numberOfBalls = Integer.parseInt(ballInputText);
                    goalTime = Double.parseDouble(timeInputText);

                    if (numberOfBalls <= 0 && goalTime <= 0) {
                        errorMessage.setText("Both values must be positive.");
                        valid = false;
                    } else if (numberOfBalls <= 0) {
                        errorMessage.setText("Number must be positive.");
                        valid = false;
                    } else if (goalTime <= 0) {
                        errorMessage.setText("Time must be positive.");
                        valid = false;
                    }
                } catch (NumberFormatException e) {
                    errorMessage.setText("Please enter valid numbers.");
                    valid = false;
                }
            }

            // Proceed to the game if inputs are valid
            if (valid) {
                primaryStage.setScene(createGameScene(primaryStage));
            }
        });

        primaryStage.setScene(openingScene);
        primaryStage.show();
    }

    private Scene createGameScene(Stage primaryStage) {
        Pane gameLayout = new Pane();
        ArrayList<Circle> circles = new ArrayList<>();
        Random rand = new Random();

        int paneWidth = 600;
        int paneHeight = 600;
        int reservedHeightForText = 60; // Reserve 60 pixels at the bottom for text

        // Calculate the total available area for the circles (exclude text area)
        double totalArea = paneWidth * (paneHeight - reservedHeightForText);
        double averageAreaPerCircle = totalArea / numberOfBalls;

        // Calculate the default radius for all circles
        double defaultRadius = Math.sqrt(averageAreaPerCircle / Math.PI);
        defaultRadius = Math.max(Math.min(defaultRadius, 30), 10); // Ensure radius is between 10 and 30 pixels

        // Create the circles
        for (int i = 0; i < numberOfBalls; i++) {
            double circleRadius;

            // Determine whether to use random size or default size
            if (useRandomSize) {
                circleRadius = rand.nextDouble() * (30 - 10) + 10; // Random radius between 10 and 30
            } else {
                circleRadius = defaultRadius; // Use default size
            }

            Circle circle = new Circle(circleRadius);
            circle.setFill(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            circle.setStroke(Color.BLACK);

            // Random position within the pane, ensuring circles do not touch the reserved text area
            double x = rand.nextDouble() * (paneWidth - 2 * circleRadius) + circleRadius;
            double y = rand.nextDouble() * (paneHeight - reservedHeightForText - 2 * circleRadius) + circleRadius;

            circle.setCenterX(x);
            circle.setCenterY(y);

            circles.add(circle);
            gameLayout.getChildren().add(circle);

            // Add event handler to each circle
            circle.setOnMouseClicked(event -> {
                if (startTime == null) {
                    startTime = LocalTime.now(); // Timer starts when the first circle is clicked
                }
                circle.setVisible(false);
                checkGameOver(gameLayout, circles, primaryStage);
            });
        }

        // Create labels for goal time and actual time, manually positioned
        Label goalTimeLabel = new Label("Goal: " + goalTime + " Seconds");
        goalTimeLabel.setLayoutX(400);
        goalTimeLabel.setLayoutY(paneHeight - reservedHeightForText + 10);

        resultLabel = new Label("Your Time: ________");
        resultLabel.setLayoutX(50);
        resultLabel.setLayoutY(paneHeight - reservedHeightForText + 10);

        messageLabel = new Label();
        messageLabel.setFont(new Font("Arial", 60)); // Set larger font for win/lose message
        messageLabel.setLayoutX(180);
        messageLabel.setLayoutY(250); // Position win/lose message at the center

        gameLayout.getChildren().addAll(goalTimeLabel, resultLabel, messageLabel);

        return new Scene(gameLayout, paneWidth, paneHeight);
    }

    private void checkGameOver(Pane gameLayout, ArrayList<Circle> circles, Stage primaryStage) {
        boolean allCirclesGone = circles.stream().allMatch(circle -> !circle.isVisible());
        if (allCirclesGone) {
            endTime = LocalTime.now();
            double actualTime = (endTime.toNanoOfDay() - startTime.toNanoOfDay()) / 1_000_000_000.0;
            resultLabel.setText(String.format("Time: %.2f seconds", actualTime));
            if (actualTime <= goalTime) {
                messageLabel.setText("You Win!");
            } else {
                messageLabel.setText("You Lose!");
            }

            // Start animation after game is over
            animateMessage();
        }
    }

    // Method to animate the "You Win!" or "You Lose!" message
    private void animateMessage() {
        final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    // Cycle through red, blue, green, and yellow colors
                    messageLabel.setTextFill(colors[(int) (Math.random() * colors.length)]);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Infinite looping animation
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
