package com.manmohan.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    // View
    LinearLayout scoreLayout;
    TextView winner;
    Button playAgain;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialising all views
        scoreLayout = findViewById(R.id.score_layout);
        winner = findViewById(R.id.winner);
        playAgain = findViewById(R.id.play_again);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

    }

    public void onTap (View view) {

        // Casting the View into ImageView
        ImageView tappedView = (ImageView) view;

        // Fetching the tag
        int tappedPosition = Integer.parseInt(tappedView.getTag().toString());

        if(gameState[tappedPosition] == 2 && gameIsActive) {

            gameState[tappedPosition] = activePlayer;

            tappedView.setTranslationY(-1000f);

            if(activePlayer == 0) {
                tappedView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                tappedView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            tappedView.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition: winningPositions) {

                if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[0]] != 2)){

                    gameIsActive = false;

                    String winnerText = "Red";
                    if(gameState[winningPosition[0]] == 0) {
                        winnerText = "Yellow";
                    }

                    scoreLayout.setVisibility(View.VISIBLE);
                    winner.setText("Winner is "+winnerText);

                } else {

                    boolean gameOver = true;

                    for (int state: gameState) {
                        if(state == 2) {
                            gameOver = false;
                        }
                    }

                    if(gameOver) {
                        scoreLayout.setVisibility(View.VISIBLE);
                        winner.setText("It's a draw");
                    }

                }

            }

        }

    }

    public void playAgain(View view) {

        gameIsActive = true;

        activePlayer = 0;

        scoreLayout.setVisibility(View.INVISIBLE);

        for (int i=0; i< 9; i++) {
            gameState[i] = 2;
        }

        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);

    }

}
