package com.example.jeopardywordgame;

import java.util.Random;

public class GameModel {
    private String currentWord;
    private String imagePath;
    private int guessesLeft;
    private boolean gameOver;
    private boolean gameWon;
    private Random random;

    private final String[][] wordList = {
            {"apple", "/images/apple.jpg"},
            {"tiger", "/images/tiger.jpg"},
            {"ocean", "/images/ocean.jpg"},
            {"piano", "/images/piano.jpg"},
            {"house", "/images/house.jpg"},
            {"river", "/images/river.jpg"},
            {"cloud", "/images/cloud.jpg"},
            {"heart", "/images/heart.jpg"},
            {"forest", "/images/forest.jpg"},
            {"guitar", "/images/guitar.jpg"}
    };

    public GameModel() {
        this.random = new Random();
        this.guessesLeft = 3;
        this.gameOver = false;
        this.gameWon = false;
        selectRandomWord();
    }

    private void selectRandomWord() {
        int index = random.nextInt(wordList.length);
        this.currentWord = wordList[index][0];
        this.imagePath = wordList[index][1];
    }

    public boolean checkGuess(String guess) {
        if (gameOver) return false;

        boolean correct = currentWord.equalsIgnoreCase(guess.trim());

        if (correct) {
            gameWon = true;
            gameOver = true;
        } else {
            guessesLeft--;
            if (guessesLeft <= 0) {
                gameOver = true;
                gameWon = false;
            }
        }

        return correct;
    }

    public String getBlanks() {
        if (gameOver && !gameWon) {
            return currentWord.toUpperCase();
        }

        StringBuilder blanks = new StringBuilder();
        for (int i = 0; i < currentWord.length(); i++) {
            blanks.append("_ ");
        }
        return blanks.toString().trim();
    }

    public void reset() {
        this.guessesLeft = 3;
        this.gameOver = false;
        this.gameWon = false;
        selectRandomWord();
    }

    public String getCurrentWord() { return currentWord; }
    public String getImagePath() { return imagePath; }
    public int getGuessesLeft() { return guessesLeft; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }
}