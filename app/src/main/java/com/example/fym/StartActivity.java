package com.example.fym;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends Activity {

    private AnimationDrawable playerAnimation;
    private ImageView player;
    boolean sound=false;

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Button startBtn=findViewById(R.id.start_btn);
        final Button soundBtn=findViewById(R.id.sound_btn);
        Button scoreBoardBtn=findViewById(R.id.score_board);
        Button instructBtn=findViewById(R.id.instruct_btn);
        player=findViewById(R.id.im_iv);
        playerAnimation =(AnimationDrawable) player.getDrawable();
        playerAnimation.start();

        mediaPlayer=MediaPlayer.create(this, R.raw.back_music2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

            soundBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound==false){
                        stop();
                        soundBtn.setBackground(getResources().getDrawable(R.drawable.sound_off));
                        sound=true;
                    }
                    else{
                        play();
                        soundBtn.setBackground(getResources().getDrawable(R.drawable.sound_on));
                        sound=false;
                    }
                }
            });

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    playerAnimation.stop();
                    Intent intent=new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });

            scoreBoardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerAnimation.stop();
                    Intent intent=new Intent(StartActivity.this,ScoreBoardActivity.class);
                    startActivity(intent);
                }
            });

            instructBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater= getLayoutInflater();
                    View layout_dialog = inflater.inflate(R.layout.instructions_activity, null);

                    AlertDialog.Builder alert= new AlertDialog.Builder(StartActivity.this);
                    alert.setTitle(getResources().getString(R.string.instruct_ttl));
                    alert.setView(layout_dialog);

                    alert.setCancelable(false);

                    alert.setPositiveButton(getResources().getString(R.string.done_str), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog= alert.create();
                    dialog.show();
                }
            });
        }


        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            setIntent(intent);
        }

        @Override
        protected void onPause() {
            super.onPause();
            playerAnimation.stop();
            stop();
        }



    @Override
    protected void onResume() {
        super.onResume();
        playerAnimation.start();
        mediaPlayer.release();
        play();

    }

    protected void play(){
        mediaPlayer=MediaPlayer.create(this, R.raw.back_music2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }


    protected void stop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
