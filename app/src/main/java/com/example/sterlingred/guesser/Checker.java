package com.example.sterlingred.guesser;

import java.util.HashMap;

/**
 * Created by sterlingred on 3/4/16.
 * Edited by sjoshua270 on 3/4/16.
 */
class Checker {
    private final int answer;

    public Checker(int answer) {
        this.answer = answer;
    }

    public HashMap<String, Integer> compare(int guess) {
        int difference = answer - guess;
        int absDifference = Math.abs(difference);
        int rating = 0;

        if (absDifference < 80)
            rating = 1;
        if (absDifference < 40)
            rating = 2;
        if (absDifference < 20)
            rating = 3;
        if (absDifference < 10)
            rating = 4;
        if (absDifference < 5)
            rating = 5;

        HashMap<String, Integer> dict = new HashMap<>();
        dict.put("difference", difference);
        dict.put("rating", rating);
        return dict;
    }
}
