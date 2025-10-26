package com.example.jeopardywordgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application...");

        // Debug: Check if we can find the FXML file
        URL fxmlUrl = getClass().getResource("/game.fxml");
        if (fxmlUrl == null) {
            System.err.println("ERROR: Cannot find game.fxml!");
            System.err.println("Classpath: " + System.getProperty("java.class.path"));
            throw new RuntimeException("Cannot find game.fxml in resources folder");
        }
        System.out.println("Found FXML at: " + fxmlUrl);

        // Load the FXML
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        System.out.println("FXML loaded successfully!");

        primaryStage.setTitle("Jeopardy Word Guessing Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Launching JavaFX application...");
        launch(args);
    }
}