package com.example.myconvertsuhu;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView universityTextView, mercubuanaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoImageView = findViewById(R.id.logoImageView);
        universityTextView = findViewById(R.id.universityTextView);
        mercubuanaTextView = findViewById(R.id.mercubuanaTextView);

        // Load animations
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        Animation universityAnim = AnimationUtils.loadAnimation(this, R.anim.university_text_animation);
        Animation mercubuanaAnim = AnimationUtils.loadAnimation(this, R.anim.mercubuana_text_animation);

        // Apply animations
        logoImageView.startAnimation(logoAnim);
        universityTextView.startAnimation(universityAnim);
        mercubuanaTextView.startAnimation(mercubuanaAnim);

        // Move to MainActivity after the splash screen animation ends
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Wait for 3 seconds (or adjust time as per your need)
                    Thread.sleep(4000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
