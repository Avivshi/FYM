package com.example.fym;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends
        AppCompatActivity{
        private TextView scoreLabel;
        private TextView startLabel;
        private ImageView player;
        private ImageView silverCoin;
        private ImageView goldCoin;
        private ImageView enemy;
        private ImageView enemy2;

        //Button
        private Button pauseBtn;

        //Animation
        private ValueAnimator animator;
        private AnimationDrawable playerAnimation;
        private AnimationDrawable enemyAnimation;
        private AnimationDrawable enemy2Animation;
        private AnimationDrawable goldAnimation;
        private AnimationDrawable silverAnimation;

        // Size
        private int frameHeight;
        private int playerSize;
        private int screenWidth;
        private int screenHeight;

        //Points
        private int goldPoints=30;
        private int silverPoints=15;

        // Position
        private int playerY;
        private int silverCoinX;
        private int silverCoinY;
        private int goldenCoinX;
        private int goldenCoinY;
        private int enemyX;
        private int enemyY;
        private int enemy2X;
        private int enemy2Y;
        // Speed
        private int playerSpeed;
        private int silverCoinSpeed;
        private int goldenCoinSpeed;
        private int enemySpeed;
        private int enemy2Speed;

    // Score
        private int score = 0;
        private int targetScore =200;

        // Initialize Class
        private Handler handler = new Handler();
        private Timer timer = new Timer();
        private SoundPlayer sound;


        // Status Check
        private boolean action_flg = false;
        private boolean start_flg = false;
        private boolean pause_flg = false;
        private boolean first_time=true;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
            final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

            animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(10000L);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    final float progress = (float) animation.getAnimatedValue();
                    final float width = backgroundOne.getWidth();
                    final float translationX = width * progress;
                    backgroundOne.setTranslationX(translationX);
                    backgroundTwo.setTranslationX(translationX - width);
                }
            });

            sound = new SoundPlayer(this);

            scoreLabel = (TextView) findViewById(R.id.scoreLabel);
            startLabel = (TextView) findViewById(R.id.startLabel);

            player = (ImageView) findViewById(R.id.player_id);
            playerAnimation =(AnimationDrawable) player.getDrawable();
            silverCoin = (ImageView) findViewById(R.id.silver_coin);
            silverAnimation=(AnimationDrawable) silverCoin.getDrawable();
            goldCoin = (ImageView) findViewById(R.id.gold_coin);
            goldAnimation=(AnimationDrawable) goldCoin.getDrawable();
            enemy = (ImageView) findViewById(R.id.enemy_id);
            enemyAnimation =(AnimationDrawable)enemy.getDrawable();
            enemy2 = findViewById(R.id.enemy2_id);
            enemy2Animation=(AnimationDrawable)enemy2.getDrawable();

            pauseBtn=findViewById(R.id.pause_btn);
            pauseBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(start_flg)
                    {
                    if(pause_flg==false){

                        pause_flg=true;

                        //Stop the timer
                        timer.cancel();
                        timer=null;

                        pauseBtn.setBackgroundResource(R.drawable.resume_btn);
                    }

                    else {
                        pause_flg=false;

                        pauseBtn.setBackgroundResource(R.drawable.pause_btn);

                        //Create and start the timer
                        timer=new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        changePos();
                                    }
                                });
                            }
                        }, 0, 20);
                    }
                }
            }});



            // Get screen size.
            WindowManager wm = getWindowManager();
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            screenWidth = size.x;
            screenHeight = size.y;

            playerSpeed = Math.round(screenHeight / 120);
            silverCoinSpeed = Math.round(screenWidth / 160);
            goldenCoinSpeed = Math.round(screenWidth /136);
            enemySpeed = Math.round(screenWidth /132);

            // Move to out of screen.
            silverCoin.setX(-85);
            silverCoin.setY(-85);
            goldCoin.setX(-85);
            goldCoin.setY(-85);
            enemy.setX(-85);
            enemy.setY(-85);
            enemy2.setX(-90);
            enemy2.setY(-90);

            scoreLabel.setText(getResources().getString(R.string.score)+": 0");


        }


        public void changePos() {

            hitCheck();

            silverCoinX -= silverCoinSpeed;
            if (silverCoinX < 0) {
                silverCoinX = screenWidth + 20;
                silverCoinY = (int) Math.floor(Math.random() * (frameHeight - silverCoin.getHeight()));
            }
            silverCoin.setX(silverCoinX);
            silverCoin.setY(silverCoinY);


            enemyX -= enemySpeed;
            if (enemyX < 0) {
                enemyX = screenWidth + 10;
                enemyY = (int) Math.floor(Math.random() * (frameHeight - enemy.getHeight()));
            }
            enemy.setX(enemyX);
            enemy.setY(enemyY);

            if(score>350){
                enemy2X -= enemy2Speed;
                if (enemy2X < 0) {
                    enemy2X = screenWidth + 10;
                    enemy2Y = (int) Math.floor(Math.random() * (frameHeight - enemy2.getHeight()));
                }
                enemy2.setX(enemy2X);
                enemy2.setY(enemy2Y);

            }

            goldenCoinX -= goldenCoinSpeed;
            if (goldenCoinX < 0) {
                goldenCoinX = screenWidth + 5000;
                goldenCoinY = (int) Math.floor(Math.random() * (frameHeight - goldCoin.getHeight()));
            }
            goldCoin.setX(goldenCoinX);
            goldCoin.setY(goldenCoinY);


            // Move player
            if (action_flg == true) {
                // Touching
                playerY -= playerSpeed;
                //player.setImageResource(R.drawable.flying_blue2);

            } else {
                // Releasing
                playerY += playerSpeed;
            }

            // Check player position.
            if (playerY < 0)
                playerY = 0;

            if (playerY > frameHeight - playerSize)
                playerY = frameHeight - playerSize;

            player.setY(playerY);


            scoreLabel.setText(getResources().getString(R.string.score)+": " + score);

        }


        public void hitCheck() {


            if(score>350){
                if(first_time==true){
                    first_time=false;
                    enemy2Speed=enemySpeed+2;
                    enemy2Animation.start();
                }
                // silver
                int silverCenterX = silverCoinX + silverCoin.getWidth() / 2;
                int silverCenterY = silverCoinY + silverCoin.getHeight() / 2;

                if (0 <= silverCenterX && silverCenterX <= playerSize &&
                        playerY <= silverCenterY && silverCenterY <= playerY + playerSize) {

                    score += silverPoints;
                    silverCoinX = -10;
                    sound.playHitSound();

                }

                // gold
                int goldCenterX = goldenCoinX + goldCoin.getWidth() / 2;
                int goldCenterY = goldenCoinY + goldCoin.getHeight() / 2;

                if (0 <= goldCenterX && goldCenterX <= playerSize &&
                        playerY <= goldCenterY && goldCenterY <= playerY + playerSize) {

                    score += goldPoints;
                    goldenCoinX = -10;
                    sound.playHitSound();

                }

                // enemy1
                int enemyCenterX = enemyX + enemy.getWidth() / 2;
                int enemyCenterY = enemyY + enemy.getHeight() / 2;

                if (0 <= enemyCenterX && enemyCenterX <= playerSize &&
                        playerY <= enemyCenterY && enemyCenterY <= playerY + playerSize) {

                    // Stop Timer
                    timer.cancel();
                    timer = null;

                    animator.end();
                    playerAnimation.stop();
                    enemyAnimation.stop();
                    enemy2Animation.stop();
                    goldAnimation.stop();
                    silverAnimation.stop();

                    sound.playOverSound();

                    // Show Result
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);

                } // enemy2
                int enemy2CenterX = enemy2X + enemy2.getWidth() / 2;
                int enemy2CenterY = enemy2Y + enemy2.getHeight() / 2;

                if (0 <= enemy2CenterX && enemy2CenterX <= playerSize &&
                        playerY <= enemy2CenterY && enemy2CenterY <= playerY + playerSize) {

                    // Stop Timer
                    timer.cancel();
                    timer = null;

                    animator.end();
                    playerAnimation.stop();
                    enemyAnimation.stop();
                    enemy2Animation.stop();
                    goldAnimation.stop();
                    silverAnimation.stop();

                    sound.playOverSound();

                    // Show Result
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);

                }else if (score == targetScore) {
                    targetScore *= 1.5;
                    playerSpeed += 2;
                    silverCoinSpeed += 2;
                    goldenCoinSpeed += 2;
                    enemySpeed += 2;
                    enemy2Speed+=2;
                }
            }

        else {    // silver
                int silverCenterX = silverCoinX + silverCoin.getWidth() / 2;
                int silverCenterY = silverCoinY + silverCoin.getHeight() / 2;

                if (0 <= silverCenterX && silverCenterX <= playerSize &&
                        playerY <= silverCenterY && silverCenterY <= playerY + playerSize) {

                    score += silverPoints;
                    silverCoinX = -10;
                    sound.playHitSound();

                }

                // gold
                int goldCenterX = goldenCoinX + goldCoin.getWidth() / 2;
                int goldCenterY = goldenCoinY + goldCoin.getHeight() / 2;

                if (0 <= goldCenterX && goldCenterX <= playerSize &&
                        playerY <= goldCenterY && goldCenterY <= playerY + playerSize) {

                    score += goldPoints;
                    goldenCoinX = -10;
                    sound.playHitSound();

                }

                // enemy
                int enemyCenterX = enemyX + enemy.getWidth() / 2;
                int enemyCenterY = enemyY + enemy.getHeight() / 2;

                if (0 <= enemyCenterX && enemyCenterX <= playerSize &&
                        playerY <= enemyCenterY && enemyCenterY <= playerY + playerSize) {

                    // Stop Timer
                    timer.cancel();
                    timer = null;

                    animator.end();
                    playerAnimation.stop();
                    enemyAnimation.stop();
                    goldAnimation.stop();
                    silverAnimation.stop();

                    sound.playOverSound();

                    // Show Result
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);

                } else if (score == targetScore) {
                    targetScore *= 1.5;
                    playerSpeed += 2;
                    silverCoinSpeed += 2;
                    goldenCoinSpeed += 2;
                    enemySpeed += 2;
                }
            }
        }


        public boolean onTouchEvent(MotionEvent me) {

            if (start_flg == false) {

                start_flg = true;
                animator.start();
                playerAnimation.start();
                enemyAnimation.start();
                goldAnimation.start();
                silverAnimation.start();


                FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
                frameHeight = frame.getHeight();

                playerY = (int) player.getY();

                playerSize = player.getHeight();

                startLabel.setVisibility(View.GONE);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                changePos();
                            }
                        });
                    }
                }, 0, 20);


            } else {
                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    action_flg = true;

                } else if (me.getAction() == MotionEvent.ACTION_UP) {
                    action_flg = false;
                }
            }



            return true;
        }


    @Override
    protected void onPause() {
        super.onPause();

        if (pause_flg == false) {

            try {
                pause_flg = true;

                //Stop the timer
                timer.cancel();
                timer = null;

                pauseBtn.setBackgroundResource(R.drawable.resume_btn);
            } catch (Exception e) {

            }
        }
    }
}
