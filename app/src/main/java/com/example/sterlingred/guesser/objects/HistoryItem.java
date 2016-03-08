package com.example.sterlingred.guesser.objects;

/**
 * Created by Joshua on 3/4/2016.
 * com.example.sterlingred.guesser
 */
public class HistoryItem {
    private final String message;
    private final int guess;
    private final int rating;

    public HistoryItem(String message, int guess, int rating) {
        this.message = message;
        this.guess = guess;
        this.rating = rating;
    }

    public HistoryItem(String message) {
        this.message = message;
        guess = -1;
        rating = 0;
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

    public int getRating() {
        return rating;
    }
}
