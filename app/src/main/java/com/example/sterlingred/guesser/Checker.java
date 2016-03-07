package com.example.sterlingred.guesser;

/**
 * Created by sterlingred on 3/4/16.
 * Edited by sjoshua270 on 3/4/16.
 */
class Checker {
    private final int answer;

    public Checker(int answer) {
        this.answer = answer;
    }

    public int compare(int guess) {
        if (guess < answer)
            return -1;
        else if (guess > answer)
            return 1;
        else
            return 0;
    }
}
