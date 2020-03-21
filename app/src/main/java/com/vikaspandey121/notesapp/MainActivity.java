package com.vikaspandey121.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikaspandey121.notesapp.Common.OnBoarding;

public class MainActivity extends AppCompatActivity {

    //Variables
    private  static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottonAnim;
    ImageView image;
    TextView logo, slogan;

    //SharedPreferences is used to check whether a new user is opening the app or regular one
    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //To Hide the Status and Action bar
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottonAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation );

        //Hooks
        image = findViewById(R.id.splashLogo);
        logo = findViewById(R.id.appName);
        slogan = findViewById(R.id.slogan);

        image.setAnimation(topAnim);
        logo.setAnimation(bottonAnim);
        slogan.setAnimation(bottonAnim);

        new Handler().postDelayed(new Runnable() { //This method make intent hold for 5 sec on this activity by SPLASH_ACTIVITY variable and the do the intent
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit(); //to commit all the changes happened above
                    Intent intent = new Intent(MainActivity.this, OnBoarding.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();

                }


            }
        },SPLASH_SCREEN);
    }
}
