package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{
        one,two,no
    }
    Player currentPlayer=Player.one;

    Player[] playerChoices=new Player[9];

    int [][] winnerRowsColumns={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0]=Player.no;
        playerChoices[1]=Player.no;
        playerChoices[2]=Player.no;
        playerChoices[3]=Player.no;
        playerChoices[4]=Player.no;
        playerChoices[5]=Player.no;
        playerChoices[6]=Player.no;
        playerChoices[7]=Player.no;
        playerChoices[8]=Player.no;
    }
   public void imageViewIsTapped(View imageView){   //THE IMAGE TAPPED IS THE imageView
       ImageView tappedImageView= (ImageView) imageView;  //ImageView is sub class of view
       tappedImageView.setTranslationX(-2000);

       int tappedImageViewTag=Integer.parseInt(tappedImageView.getTag().toString());

       playerChoices[tappedImageViewTag]=currentPlayer;

       if(currentPlayer==Player.one){
           tappedImageView.setImageResource(R.drawable.tiger);
           currentPlayer=Player.two;
       }else{
           tappedImageView.setImageResource(R.drawable.lion);
           currentPlayer=Player.one;
       }

       tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
       //Toast.makeText(getApplicationContext(),tappedImageView.getTag()+"",Toast.LENGTH_SHORT).show();

       for(int[] winnerColumns : winnerRowsColumns){
           if(playerChoices[winnerColumns[0]]==playerChoices[winnerColumns[1]]
           && playerChoices[winnerColumns[1]]==playerChoices[winnerColumns[2]]
           &&playerChoices[winnerColumns[0]]!=Player.no){

               if(currentPlayer==Player.one){
                   Toast.makeText(getApplicationContext(),"Player 2 is the winner",
                           Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(getApplicationContext(),
                           "Player 1 is the winner",Toast.LENGTH_LONG).show();


               }
                          }
       }

   }
}
