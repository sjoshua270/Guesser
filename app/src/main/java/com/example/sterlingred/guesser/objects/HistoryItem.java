package com.example.sterlingred.guesser.objects;

/**
 * Created by Joshua on 3/4/2016.
 * com.example.sterlingred.guesser
 */
public class HistoryItem {
    private final String message;
    private final int guess;

    public HistoryItem(String message, int guess) {
        this.message = message;
        this.guess = guess;
    }

    @Override
    public String toString() {
        return message + " " + guess;
    }

    public String getMessage() {
        return message;
    }

    public int getGuess() {
        return guess;
    }
}
