package com.example.jeopardywordgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML private Label wordBlanksLabel;
    @FXML private ImageView wordImageView;
    @FXML private TextField guessTextField;
    @FXML private Button submitButton;
    @FXML private Button restartButton;
    @FXML private Label messageLabel;
    @FXML private Label guessesLeftLabel;

    private GameModel gameModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameModel = new GameModel();
        updateView();
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        submitButton.setOnAction(event -> handleGuessSubmission());
        restartButton.setOnAction(event -> restartGame());
        guessTextField.setOnAction(event -> handleGuessSubmission());
    }

    private void handleGuessSubmission() {
        String guess = guessTextField.getText();

        if (guess == null || guess.trim().isEmpty()) {
            messageLabel.setText("Please enter a guess!");
            return;
        }

        boolean correct = gameModel.checkGuess(guess);

        if (correct) {
            messageLabel.setText("Correct! You win!");
            handleGameOver();
        } else {
            if (gameModel.isGameOver()) {
                messageLabel.setText("Game Over! The word was: " + gameModel.getCurrentWord());
                handleGameOver();
            } else {
                messageLabel.setText("Incorrect! " + gameModel.getGuessesLeft() + " guesses left.");
                guessTextField.clear();
            }
        }

        updateView();
    }

    private void handleGameOver() {
        submitButton.setDisable(true);
        guessTextField.setDisable(true);
        restartButton.setDisable(false);
    }

    private void restartGame() {
        gameModel.reset();
        submitButton.setDisable(false);
        guessTextField.setDisable(false);
        guessTextField.clear();
        messageLabel.setText("Guess the word!");
        restartButton.setDisable(true);
        updateView();
    }

    private void updateView() {
        wordBlanksLabel.setText(gameModel.getBlanks());

        try {
            Image image = new Image(getClass().getResourceAsStream(gameModel.getImagePath()));
            wordImageView.setImage(image);
        } catch (Exception e) {
            messageLabel.setText("Error loading image");
        }

        guessesLeftLabel.setText("Guesses left: " + gameModel.getGuessesLeft());
        restartButton.setDisable(!gameModel.isGameOver());
    }
}