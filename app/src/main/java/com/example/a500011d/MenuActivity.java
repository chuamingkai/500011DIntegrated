package com.example.a500011d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button buttonPost;
    Button buttonHome;
    Button buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonHome = findViewById(R.id.buttonHome);
        buttonPost = findViewById(R.id.buttonPost);
        buttonProfile = findViewById(R.id.buttonProfile);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MenuActivity.this, PostingActivity.class);
                startActivity(intent);

            }
        });
}}