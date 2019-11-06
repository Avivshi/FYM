package com.example.fym;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Locale;

public class ScoreBoardActivity extends Activity {

    String[] highScoreTable;
    ListView highScoreView;

    SharedPreferences firstPlace;
    SharedPreferences secondPlace;
    SharedPreferences thirdPlace;
    SharedPreferences fourPlace;
    SharedPreferences fivePlace;
    SharedPreferences sixPlace;
    SharedPreferences sevenPlace;
    SharedPreferences eightPlace;
    SharedPreferences ninePlace;
    SharedPreferences tenPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        if(Locale.getDefault().getLanguage().equals("iw"))
        {
            LinearLayout linearLayout=findViewById(R.id.score_layout);
            linearLayout.setBackground(getResources().getDrawable(R.drawable.heb_scorelogo));
        }

        highScoreTable=new String[10];

        firstPlace = getSharedPreferences("FIRST_PLACE", Context.MODE_PRIVATE);
        secondPlace = getSharedPreferences("SEC_PLACE", Context.MODE_PRIVATE);
        thirdPlace = getSharedPreferences("THIRD_PLACE", Context.MODE_PRIVATE);
        fourPlace = getSharedPreferences("FOUR_PLACE", Context.MODE_PRIVATE);
        fivePlace = getSharedPreferences("FIVE_PLACE", Context.MODE_PRIVATE);
        sixPlace = getSharedPreferences("SIX_PLACE", Context.MODE_PRIVATE);
        sevenPlace = getSharedPreferences("SEVEN_PLACE", Context.MODE_PRIVATE);
        eightPlace = getSharedPreferences("EIGHT_PLACE", Context.MODE_PRIVATE);
        ninePlace = getSharedPreferences("NINE_PLACE", Context.MODE_PRIVATE);
        tenPlace = getSharedPreferences("TEN_PLACE", Context.MODE_PRIVATE);

        highScoreTable[0]=firstPlace.getInt("FIRST_SCORE",0)+"";
        highScoreTable[1]=secondPlace.getInt("SEC_SCORE",0)+"";
        highScoreTable[2]=thirdPlace.getInt("THIRD_SCORE",0)+"";
        highScoreTable[3]=fourPlace.getInt("FOUR_SCORE",0)+"";
        highScoreTable[4]=fivePlace.getInt("FIVE_SCORE",0)+"";
        highScoreTable[5]=sixPlace.getInt("SIX_SCORE",0)+"";
        highScoreTable[6]=sevenPlace.getInt("SEVEN_SCORE",0)+"";
        highScoreTable[7]=eightPlace.getInt("EIGHT_SCORE",0)+"";
        highScoreTable[8]=ninePlace.getInt("NINE_SCORE",0)+"";
        highScoreTable[9]=tenPlace.getInt("TEN_SCORE",0)+"";

        highScoreView=findViewById(R.id.listview);
        MySimpleAdapter adapter=new MySimpleAdapter(this,highScoreTable);
        highScoreView.setAdapter(adapter);
    }
}
