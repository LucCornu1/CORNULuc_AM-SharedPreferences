package com.example.cornuluc_persistance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    //Duration of Splash
    private final int DUREE_SPLASH = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);

        ImageView img = (ImageView) findViewById(R.id.splash_Image);
        img.setBackgroundResource(R.drawable.house);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash_screen.this, MainActivity.class);
                splash_screen.this.startActivity(mainIntent);
                splash_screen.this.finish();
            }
        }, DUREE_SPLASH);
    }
}