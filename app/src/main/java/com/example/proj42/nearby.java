package com.example.proj42;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class nearby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        Button browseButton = findViewById(R.id.button2);

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL
                String url = "https://www.google.com/maps/search/nearby+hospitals/@17.7347292,83.2735387,13z/data=!3m1!4b1?entry=ttu";

                // Create an Intent with the URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Start the activity (open the URL)
                startActivity(intent);
            }
        });
    }
}
