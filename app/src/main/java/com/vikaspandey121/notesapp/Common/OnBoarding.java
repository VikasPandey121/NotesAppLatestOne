package com.vikaspandey121.notesapp.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vikaspandey121.notesapp.Dashboard;
import com.vikaspandey121.notesapp.HelperClasses.SliderAdapter;
import com.vikaspandey121.notesapp.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager= findViewById(R.id.slider);
        dotsLayout= findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        //Changes I have made

        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoarding.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        //Changes I have made
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), Dashboard.class)); //Here I should I call signIn rather then moving him to dashboard
        finish();
    }


    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1); //I should do the same in skip also but I shoud point it to 4
    }

    private void addDots(int position){

        dots = new TextView[5]; // 5 is the number of slides we have
        dotsLayout.removeAllViews();
        for (int i=0;   i<dots.length;  i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark)); //color of dots
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;
            if (position ==0){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position==1){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position ==2){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position == 3){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }
            else{
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_animation); //Assiging animation to the get_started button
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
