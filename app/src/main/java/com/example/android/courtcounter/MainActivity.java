package com.example.android.courtcounter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.android.courtcounter.R.style.score;

public class MainActivity extends AppCompatActivity {

    int score1;
    int score2;
    int games1;
    int games2;
    int sets1;
    int sets2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("score1");
            score2 = savedInstanceState.getInt("score2");
            games1 = savedInstanceState.getInt("games1");
            games2 = savedInstanceState.getInt("games2");
            sets1 = savedInstanceState.getInt("sets1");
            sets2 = savedInstanceState.getInt("sets2");
        }

        display();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putInt("score1", score1);
        savedInstanceState.putInt("score2", score2);
        savedInstanceState.putInt("games1", games1);
        savedInstanceState.putInt("games2", games2);
        savedInstanceState.putInt("sets1", sets1);
        savedInstanceState.putInt("sets2", sets2);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void ten1(View view){
        score1 += 10;
        checkScore();
        checkGames();
        checkSets();
        display();
    }


    public void ten2(View view){
        score2 += 10;
        checkScore();
        checkGames();
        checkSets();
        display();
    }

    public void reset(View view){
        score1 = 0;
        score2 = 0;
        games1 = 0;
        games2 = 0;
        sets1 = 0;
        sets2 = 0;
        display();
    }

    public void resetAll(){
        score1 = 0;
        score2 = 0;
        games1 = 0;
        games2 = 0;
        sets1 = 0;
        sets2 = 0;
    }

    public void checkScore(){
        if(score1 == 40) {
            score1 = 0;
            score2 = 0;
            games1++;
        }
        else if(score2 == 40){
            score1 = 0;
            score2 = 0;
            games2++;
        }

    }

    public void checkGames(){
        if(games1 == 6) {
            games1 = 0;
            games2 = 0;
            sets1++;
        }
        else if(games2 == 6){
            games1 = 0;
            games2 = 0;
            sets2++;
        }
    }

    public void checkSets(){
        Context context = getApplicationContext();
        CharSequence text = null;
        int duration = Toast.LENGTH_SHORT;;
        Toast toast;
        int b = 0;
        if(sets1 == 2){
            text = "Player 1 won!";
            b = 1;
        }
        if(sets2 == 2){
            text = "Player 2 won!";
            b = 1;
        }
        if(b == 1){
            toast = Toast.makeText(context, text, duration);
            toast.show();
            resetAll();
        }
    }


    /**
     * Displays the given score for Player2.
     */
    public void displayForP1(int score, int games, int sets) {
        TextView scoreView = (TextView) findViewById(R.id.player_1_score);
        scoreView.setText("Score: " + String.valueOf(score));
        TextView gamesView = (TextView) findViewById(R.id.player_1_games);
        gamesView.setText("Games: " + String.valueOf(games));
        TextView setsView = (TextView) findViewById(R.id.player_1_sets);
        setsView.setText("Sets: " + String.valueOf(sets));
    }

    /**
     * Displays the given score for Player2.
     */
    public void displayForP2(int score, int games, int sets) {
        TextView scoreView = (TextView) findViewById(R.id.player_2_score);
        scoreView.setText("Score: " + String.valueOf(score));
        TextView gamesView = (TextView) findViewById(R.id.player_2_games);
        gamesView.setText("Games: " + String.valueOf(games));
        TextView setsView = (TextView) findViewById(R.id.player_2_sets);
        setsView.setText("Sets: " + String.valueOf(sets));
    }

    /**
     * Updates the score on the screen for both players.
     */
    public void display(){
        displayForP1(score1, games1, sets1);
        displayForP2(score2, games2, sets2);
    }
}
