package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{
        one,two,no
    }
    Player currentPlayer=Player.one;

    Player[] playerChoices=new Player[9];

    int [][] winnerRowsColumns={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean gameOver=false;

    private Button btnReset;

    private GridLayout gridLay;
//    private ImageView img1;
//    private ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        playerChoices[0]=Player.no;
//        playerChoices[1]=Player.no;
//        playerChoices[2]=Player.no;
//        playerChoices[3]=Player.no;
//        playerChoices[4]=Player.no;
//        playerChoices[5]=Player.no;
//        playerChoices[6]=Player.no;
//        playerChoices[7]=Player.no;
//        playerChoices[8]=Player.no;
        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.no;
        }

        btnReset=findViewById(R.id.btnReset);
        gridLay=findViewById(R.id.gridLay);
        //img1=(ImageView) ContextCompat.getDrawable(getApplicationContext(), R.drawable.lion);
        //img2=ContextCompat.getDrawable(getApplicationContext(), R.drawable.tiger);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTheGame();

            }
        });
    }
   public void imageViewIsTapped(View imageView){   //THE IMAGE TAPPED IS THE imageView
       //Toast.makeText(getApplicationContext(), gridLay.getChildCount()+"", Toast.LENGTH_SHORT).show();
       ImageView tappedImageView= (ImageView) imageView;  //ImageView is sub class of view
       int tappedImageViewTag=Integer.parseInt(tappedImageView.getTag().toString());

       if(playerChoices[tappedImageViewTag]==Player.no && gameOver==false) {
           tappedImageView.setTranslationX(-2000);


           playerChoices[tappedImageViewTag] = currentPlayer;



           if (currentPlayer == Player.one) {
               tappedImageView.setImageResource(R.drawable.tiger);
               currentPlayer = Player.two;
           } else {
               tappedImageView.setImageResource(R.drawable.lion);
               currentPlayer = Player.one;
           }

           tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
           //Toast.makeText(getApplicationContext(),tappedImageView.getTag()+"",Toast.LENGTH_SHORT).show();

           for (int[] winnerColumns : winnerRowsColumns) {
               if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                       && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                       && playerChoices[winnerColumns[0]] != Player.no) {

                   gameOver=true;
                   btnReset.setVisibility(View.VISIBLE);

                   if (currentPlayer == Player.one) {
                       Toast.makeText(getApplicationContext(), "Player 2 is the winner",
                               Toast.LENGTH_LONG).show();
                   } else if (currentPlayer==Player.two){
                       Toast.makeText(getApplicationContext(),
                               "Player 1 is the winner", Toast.LENGTH_LONG).show();


                   }
               }
           }
       }

       boolean flag=true;
       for(int k=0;k<playerChoices.length;k++){
           if(playerChoices[k]!=Player.no){
               flag=false;
           }


       }
       if(flag==true) {
           Toast.makeText(getApplicationContext(), "draw", Toast.LENGTH_SHORT).show();
           resetTheGame();
       }

//
//
//
//
//       for(int j=0;j<9;j++) {
//           if (gridLay.getChildAt(j) ==img1) {
//               //Toast.makeText(getApplicationContext(),"draw",Toast.LENGTH_SHORT).show();
//               btnReset.setVisibility(View.VISIBLE);
//           }
//       }



   }
   //Reset Game Function
    private void resetTheGame(){

        for (int index=0;index<gridLay.getChildCount();index++){
            ImageView imageView=(ImageView) gridLay.getChildAt(index);
            imageView.setImageDrawable(null);
        }
        currentPlayer=Player.one;

        for(int i=0;i<playerChoices.length;i++){
            playerChoices[i]=Player.no;
        }

//        playerChoices[0]=Player.no;
//        playerChoices[1]=Player.no;
//        playerChoices[2]=Player.no;
//        playerChoices[3]=Player.no;
//        playerChoices[4]=Player.no;
//        playerChoices[5]=Player.no;
//        playerChoices[6]=Player.no;
//        playerChoices[7]=Player.no;
//        playerChoices[8]=Player.no;
        gameOver=false;
        //btnReset.setVisibility(View.INVISIBLE);


    }
}
