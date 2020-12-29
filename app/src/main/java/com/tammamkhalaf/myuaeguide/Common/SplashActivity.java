package com.tammamkhalaf.myuaeguide.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tammamkhalaf.myuaeguide.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    ImageView backgroundIV;
    TextView powered_by_line;

    Animation sideAnim,bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);


        backgroundIV.findViewById(R.id.background_image);
        powered_by_line.findViewById(R.id.powered_by_line);


        sideAnim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.bottom_anim);

        backgroundIV.setAnimation(sideAnim);
        powered_by_line.setAnimation(bottomAnim);

    }

}