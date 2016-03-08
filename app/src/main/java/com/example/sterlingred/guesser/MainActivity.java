package com.example.sterlingred.guesser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sterlingred.guesser.objects.HistoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private Checker answerChecker;
    private EditText guessField;
    private HistoryAdapter historyAdapter;
    private Integer maxHistory, remainingGuesses, startingGuesses;
    private List<HistoryItem> history;
    private RecyclerView answerHistView;
    private LinearLayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Assign all variables
         */
        answerChecker = new Checker((int) (Math.random() * 100));
        answerHistView = (RecyclerView) findViewById(R.id.answers);
        guessField = (EditText) findViewById(R.id.guess_field);
        history = new ArrayList<>();
        historyAdapter = new HistoryAdapter(history);
        maxHistory = 10;
        mLayoutManager = new LinearLayoutManager(this);
        startingGuesses = 5;
        remainingGuesses = startingGuesses;
        submitButton = (Button) findViewById(R.id.submit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*
        Set up variables
         */
        mLayoutManager.setStackFromEnd(true);
        answerHistView.setLayoutManager(mLayoutManager);
        answerHistView.setAdapter(historyAdapter);
        add(new HistoryItem(getResources().getString(R.string.instructions)));
        submitButton.setOnClickListener(getSubmitListener());
        if (toolbar != null)
            setSupportActionBar(toolbar);
    }

    /**
     * Returns an OnClickListener which will get the
     * value from the guessField and call checkGuess()
     *
     * @return OnClickListener
     */
    private View.OnClickListener getSubmitListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String holder = guessField.getText().toString();
                if (holder.length() > 0) {
                    int guess = Integer.parseInt(holder);
                    checkGuess(guess);
                } else {
                    add(new HistoryItem(getResources().getString(R.string.not_valid)));
                }
            }
        };
    }

    /**
     * Checks whether the guess is less than,
     * greater than, or equal to the stored
     * answer using answerChecker.compare()
     *
     * @param guess the value that was guessed
     */
    private void checkGuess(int guess) {
        remainingGuesses--;
        String message;
        HashMap<String, Integer> results = answerChecker.compare(guess);
        int difference = results.get("difference");
        int rating = results.get("rating");
        if (difference < 0)
            message = getResources().getString(R.string.lower);
        else if (difference > 0)
            message = getResources().getString(R.string.higher);
        else
            message = getResources().getString(R.string.got_it);
        add(new HistoryItem(message, guess, rating));

        if (difference == 0)
            resetGame();
        else if (remainingGuesses <= 0) {
            add(new HistoryItem(getString(R.string.max_guesses)));
            resetGame();
        }
        // Resets the guessField to an empty value
        guessField.setText("");
    }

    /**
     * Adds HistoryItems to the list displayed
     * in the answerHistory RecyclerView
     *
     * @param item new HistoryItem to display
     */
    public void add(HistoryItem item) {
        history.add(item);
        historyAdapter.notifyItemInserted(history.size() - 1);
        while (history.size() > maxHistory) {
            history.remove(0);
            historyAdapter.notifyItemRemoved(0);
        }
        mLayoutManager.scrollToPosition(history.size() - 1);
    }

    private void resetGame() {
        remainingGuesses = startingGuesses;
        answerChecker = new Checker((int) (Math.random() * 100));
        add(new HistoryItem(getResources().getString(R.string.instructions)));
        guessField.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.restart:
                resetGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
