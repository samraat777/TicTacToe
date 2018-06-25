package com.example.sam.tictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 means 0
    //1 means X
    //2 means unplayed
    ImageView img;
    int activePlayer=0;
    boolean gameIsActive=true;


    int[] gamestate={2,2,2,2,2,2,2,2,2};

    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};




    public void clickMe(View view)
    {
        Log.i("info","Image clicked");
        img=(ImageView) view;
        System.out.println(img.getTag().toString());
        //img.setImageResource(R.drawable.zero);
        int tagCounter=Integer.parseInt(img.getTag().toString());

        if(gamestate[tagCounter]==2&&gameIsActive)
        {
            gamestate[tagCounter]=activePlayer;

            if(activePlayer==0)
            {
                img.setImageResource(R.drawable.zero);
                activePlayer=1;
            }

            else
            {
                img.setImageResource(R.drawable.xxx);
                activePlayer=0;
            }

            for (int[] winin : winningPosition)
            {
                if(gamestate[winin[0]]==gamestate[winin[1]]&&
                        gamestate[winin[1]]==gamestate[winin[2]]&&
                        gamestate[winin[0]]!=2)
                {
                    gameIsActive=false;
                    String winner="0 player";
                    Log.i("info","Someone won");

                    if((gamestate[winin[0]])==1)
                    {
                        winner="X player";
                    }

                    TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner +" has won");

                    LinearLayout layout=(LinearLayout) findViewById(R.id.layout);

                    MediaPlayer media=MediaPlayer.create(this,R.raw.win);
                    media.start();


                    layout.setVisibility(View.VISIBLE);

                }

                else
                {
                    boolean gameIsOver=true;
                    for(int counterState:gamestate)
                    {
                        if(counterState==2)
                            gameIsOver=false;
                    }
                    if(gameIsOver)
                    {

                        TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout=(LinearLayout) findViewById(R.id.layout);

                        layout.setVisibility(View.VISIBLE);

                        MediaPlayer media=MediaPlayer.create(this,R.raw.draw);
                        media.start();
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive=true;
        Log.i("info","Button Pressed");

        LinearLayout layout=(LinearLayout) findViewById(R.id.layout);

        layout.setVisibility(View.INVISIBLE);

         activePlayer=0;

        for(int i=0;i<gamestate.length;i++) {
            gamestate[i] = 2;
        }

        GridLayout grid=(GridLayout) findViewById(R.id.grid);

        for(int i=0;i<grid.getChildCount();i++) {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
