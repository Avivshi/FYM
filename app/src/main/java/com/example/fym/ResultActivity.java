package com.example.fym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

    int[] a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        a=new int [10];

        SharedPreferences firstPlace = getSharedPreferences("FIRST_PLACE", Context.MODE_PRIVATE);
        SharedPreferences  secondPlace = getSharedPreferences("SEC_PLACE", Context.MODE_PRIVATE);
        SharedPreferences thirdPlace = getSharedPreferences("THIRD_PLACE", Context.MODE_PRIVATE);
        SharedPreferences fourPlace = getSharedPreferences("FOUR_PLACE", Context.MODE_PRIVATE);
        SharedPreferences   fivePlace = getSharedPreferences("FIVE_PLACE", Context.MODE_PRIVATE);
        SharedPreferences  sixPlace = getSharedPreferences("SIX_PLACE", Context.MODE_PRIVATE);
        SharedPreferences  sevenPlace = getSharedPreferences("SEVEN_PLACE", Context.MODE_PRIVATE);
        SharedPreferences  eightPlace = getSharedPreferences("EIGHT_PLACE", Context.MODE_PRIVATE);
        SharedPreferences   ninePlace = getSharedPreferences("NINE_PLACE", Context.MODE_PRIVATE);
        SharedPreferences  tenPlace = getSharedPreferences("TEN_PLACE", Context.MODE_PRIVATE);

        a[0]=firstPlace.getInt("FIRST_SCORE",0);
        a[1]=secondPlace.getInt("SEC_SCORE",0);
        a[2]=thirdPlace.getInt("THIRD_SCORE",0);
        a[3]=fourPlace.getInt("FOUR_SCORE",0);
        a[4]=fivePlace.getInt("FIVE_SCORE",0);
        a[5]=sixPlace.getInt("SIX_SCORE",0);
        a[6]=sevenPlace.getInt("SEVEN_SCORE",0);
        a[7]=eightPlace.getInt("EIGHT_SCORE",0);
        a[8]=ninePlace.getInt("NINE_SCORE",0);
        a[9]=tenPlace.getInt("TEN_SCORE",0);

        Button homeBtn=findViewById(R.id.back_home);
        Button againBtn=findViewById(R.id.try_again);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        if (score > a[0]) {
            highScoreLabel.setText(getResources().getString(R.string.high_score_str)+" : " + score);

        } else {
            highScoreLabel.setText(getResources().getString(R.string.high_score_str)+" : " + a[0]);
        }

        if(score>a[9]){

            int tmp;

            if(score>=a[0])
            {
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=a[4];
                a[4]=a[3];
                a[3]=a[2];
                a[2]=a[1];
                a[1]=a[0];
                a[0]=score;
            }

            else if(score>=a[1]){
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=a[4];
                a[4]=a[3];
                a[3]=a[2];
                a[2]=a[1];
                a[1]=score;
            }

            else if(score>=a[2]) {
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=a[4];
                a[4]=a[3];
                a[3]=a[2];
                a[2]=score;
            }

            else if(score>=a[3]) {
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=a[4];
                a[4]=a[3];
                a[3]=score;
            }

            else if(score>=a[4]) {
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=a[4];
                a[4]=score;
            }

            else if(score>=a[5]){
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=a[5];
                a[5]=score;
            }

            else if(score>=a[6]){
                a[9]=a[8];
                a[8]=a[7];
                a[7]=a[6];
                a[6]=score;
            }

            else if(score>=a[7]){
                a[9]=a[8];
                a[8]=a[7];
                a[7]=score;
            }

            else if(score>=a[8]){
                a[9]=a[8];
                a[8]=score;
            } else a[9]=score;

            SharedPreferences.Editor editor1= firstPlace.edit();
            SharedPreferences.Editor editor2= secondPlace.edit();
            SharedPreferences.Editor editor3= thirdPlace.edit();
            SharedPreferences.Editor editor4= fourPlace.edit();
            SharedPreferences.Editor editor5= fivePlace.edit();
            SharedPreferences.Editor editor6= sixPlace.edit();
            SharedPreferences.Editor editor7= sevenPlace.edit();
            SharedPreferences.Editor editor8= eightPlace.edit();
            SharedPreferences.Editor editor9= ninePlace.edit();
            SharedPreferences.Editor editor10= tenPlace.edit();

            editor1.putInt("FIRST_SCORE", a[0]);
            editor1.commit();
            editor2.putInt("SEC_SCORE", a[1]);
            editor2.commit();
            editor3.putInt("THIRD_SCORE", a[2]);
            editor3.commit();
            editor4.putInt("FOUR_SCORE", a[3]);
            editor4.commit();
            editor5.putInt("FIVE_SCORE", a[4]);
            editor5.commit();
            editor6.putInt("SIX_SCORE", a[5]);
            editor6.commit();
            editor7.putInt("SEVEN_SCORE", a[6]);
            editor7.commit();
            editor8.putInt("EIGHT_SCORE", a[7]);
            editor8.commit();
            editor9.putInt("NINE_SCORE", a[8]);
            editor9.commit();
            editor10.putInt("TEN_SCORE", a[9]);
            editor10.commit();
        }

    }


    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
