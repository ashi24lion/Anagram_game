package com.example.ashisingh.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {
    public boolean isPlayerTurn=true;
    public long playerScore =0,computerScore=0,turnScore=0;
    public TextView YourscoreText;
    public TextView computerScoreText,TurnScoreText;
    public ImageView dice_image;
    public Button button_hold,button_roll,button_reset;
    public int dicevalue;

   int[] Image={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         dice_image =(ImageView)findViewById(R.id.imageView) ;

         YourscoreText = (TextView) findViewById(R.id.textView);
         computerScoreText = (TextView) findViewById(R.id.textView2);
         TurnScoreText = (TextView) findViewById(R.id.textView3);
         button_hold=(Button) findViewById(R.id.button2);
         button_reset=(Button) findViewById(R.id.button3);
         button_roll=(Button) findViewById(R.id.button);

    }

    public  void roll(View v){
       dicevalue= new Random().nextInt(6)+1;
        if((dicevalue)==1)
        {
            turnScore=0;
            hold(null);

        }
        else
        {
            turnScore=dicevalue+turnScore;

        }
        updateUi();

    }
    public void hold(View v)
    {
        if(isPlayerTurn==true)
        {
            playerScore+=turnScore;
        }
        else
        {
            computerScore+=turnScore;
        }
        isPlayerTurn=!isPlayerTurn;
        turnScore=0;
        dicevalue=1;
        updateUi();
        if(computerScore>100 || playerScore>100)
        {
            Toast.makeText(MainActivity.this,(computerScore>100?"computer":"player")+"won",
                    Toast.LENGTH_SHORT).show();
            reset(null);
        }

        if(!isPlayerTurn){
            if(turnScore<20)
            {
                roll(null);
                new android.os.Handler().postDelayed(new Runnable() {
                    public void run(){
                        computerTurn();
                    }
                },2000);



            }
        }
    }
    public void reset(View v){
        computerScore=0;
        playerScore=0;
        turnScore=0;
        dicevalue=1;
        isPlayerTurn=true;
        updateUi();
    }

    public void computerTurn()
    {if(!isPlayerTurn){
        if(turnScore<20)
        {
            roll(null);
            new android.os.Handler().postDelayed(new Runnable() {
                public void run(){
                    computerTurn();
                }
            },2000);

        }else hold(null);
    }

    }
    public void updateUi(){
        YourscoreText.setText("your score : "+ playerScore);

        computerScoreText.setText("computer score : "+ computerScore);
        TurnScoreText.setText("current score : "+ turnScore);
         dice_image.setImageResource(Image[dicevalue-1]);

    }
}
