package com.example.proj42;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Set a delay of 2 seconds (2000 milliseconds) before navigating to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity
                Intent intent = new Intent(SplashActivity.this, com.example.proj42.logspage.class);
                startActivity(intent);
                finish(); // Finish the SplashActivity to prevent going back to it
            }
        }, 2000);
    }
}
